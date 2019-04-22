package com.controller;

import com.model.PO.*;
import com.model.VO.LoginModel;
import com.model.VO.RegisterModel;
import com.service.BaseService;
import com.util.phoneVerificationCode.SendSMS;
import com.util.redis.CacheUtil;
import com.util.verificationCode.Captcha;
import com.util.verificationCode.GifCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 9:51
 **/
@Controller
public class SysController extends BaseController {

    @Autowired
    @Qualifier("sysService")
    BaseService sysService;

    @RequestMapping(path = {"/","index"})
    public String index() {
        return  "index";
    }

    @RequestMapping(path = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }



    @RequestMapping(path = "/getUserName.do")
    @ResponseBody
    public Map getUsername(){
        System.out.println("正在查询用户是否登陆。。。");
        //获得当前登录用户，如无则返回null
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user == null) {
            return ajaxReturn(false, "");
        }
        return ajaxReturn(true, user.getName());
    }

    @RequestMapping(path="/logout.do", produces="application/json;charset=utf-8")
    @ResponseBody
    public Map logout() {
        //注销当前用户
        SecurityUtils.getSubject().logout();
        return ajaxReturn(true, "");
    }

    @RequestMapping(path = "/getGIFCode.do")
    public void getGIFCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(true);
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 33, 4);
            //输出
            captcha.out(response.getOutputStream());
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
        } catch (Exception e) {
            System.out.println("获取验证码异常：" + e.getMessage());
        }

    }

    @RequestMapping(path = "/getCity.do")
    @ResponseBody
    public List getCity(){
        System.out.println("正在获取城市信息。。。。");
        List list=CacheUtil.getCache().getList("市级");
        if(list.isEmpty()){
            System.out.println("从数据库中查询市级数据。。。。");
            list=(List) sysService.findSome();
            CacheUtil.getCache().setList("市级",list);
        }
        return list;
    }

    @RequestMapping(path = "/getMsgFromCity.do")
    @ResponseBody
    public Map<String,List> getMsgFromCity(String cityName){
        System.out.println("正在获取招聘信息。。。。"+cityName);
        Map<String,List> map=new HashMap<>();

        List<String> cityAreaList=new ArrayList<>();
        List<Recruit> recruitList=new ArrayList<>();

        cityAreaList=CacheUtil.getCache().getList(cityName+":Recruit");
        System.out.println("打印从缓冲的取出来的内容:  "+cityAreaList);
        if(cityAreaList.isEmpty()){
            System.out.println("从数据库中查询市区数据。。。。");
            cityAreaList=(List) sysService.findSome(cityName);
            CacheUtil.getCache().setList(cityName+":Recruit",cityAreaList);
        }

        map.put("cityArea",cityAreaList);
        map.put("recruit",recruitList);
        return map;
    }

    @RequestMapping(path = "/getPersonMsgFromCity.do")
    @ResponseBody
    public List getPersonMsgFromCity(String cityName){
        System.out.println("正在获取下级城市信息。。。。"+cityName);
        List list=new ArrayList();
        list=CacheUtil.getCache().getList(cityName+":Subordinate");
        if(list.isEmpty()){
            System.out.println("从数据库中查询市区数据。。。。");
            list=(List) sysService.findSome(cityName);
            CacheUtil.getCache().setList(cityName+":Subordinate",list);
        }
        return list;
    }




}
