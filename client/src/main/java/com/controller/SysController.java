package com.controller;

import com.model.LoginModel;
import com.model.RegisterModel;
import com.util.verificationCode.Captcha;
import com.util.verificationCode.GifCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 9:51
 **/
@Controller
public class SysController extends BaseController {

    @RequestMapping(path = {"/","index"})
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(path = "/register.do", method = RequestMethod.POST)
    public Map register(RegisterModel pm) {
        System.out.println("Register.do");
        return null;
    }

    @RequestMapping(path = "/login.do", method = RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
    public Map login(LoginModel lm, HttpServletRequest request, HttpServletResponse response) {
        //取出验证码
        String vcode = (String) request.getSession().getAttribute("_code");
        System.out.println("用户输入： " + lm.getVerificationCode() + "    正确验证码： " + vcode);
        if (!vcode.equals(lm.getVerificationCode())) {
            return ajaxReturn(false, "VerificationCode error");
        }

        //第一步：创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(lm.getPhone(),lm.getPsw());

        /**
         * 勾选记住密码则保存cookie
         * 否则不保存
         */
        if (lm.getRem()) {
            System.out.println("RememberMe");
            token.setRememberMe(true);
        }

        //第二步：获取Subject对象，该对象封装了一系列的操作
        Subject subject = SecurityUtils.getSubject();
        //第三步：执行认证
        try {
            if(!subject.isAuthenticated()){
                subject.login(token);
                return ajaxReturn(true, "登录成功！");
            }
        } catch (UnknownAccountException | IncorrectCredentialsException e1) {
            System.out.println("用户名或密码错误");
            e1.printStackTrace();
            return ajaxReturn(false, "用户名或密码错误！");
        }  catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "用户已登录！");
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
        List<String> list=new ArrayList();
        list.add("广州");
        list.add("深圳");
        list.add("珠海");
        list.add("中山");
        return list;
    }


}
