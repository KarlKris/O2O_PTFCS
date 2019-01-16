package com.controller;

import com.model.PO.User;
import com.model.VO.LoginModel;
import com.model.VO.RegisterModel;
import com.service.BaseService;
import com.util.phoneVerificationCode.SendSMS;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author:liyuanwen
 * @date: 2019/1/16 9:05
 **/
@Controller
public class UserController extends BaseController {


    @Resource(name = "userService")
    BaseService userService;


    @RequestMapping(path = "/register.do", method = RequestMethod.POST)
    @ResponseBody
    public Map  register(RegisterModel pm, HttpServletRequest request) {
        //取出验证码
        String code = (String) request.getSession().getAttribute("_phoneVerificationCode");
        if(pm.getPhoneVerificationCode().equals(code)){
            boolean flag=userService.addOne(pm.getPhone());
            if (flag){
                return ajaxReturn(true,"success");
            }
        }
        return ajaxReturn(false,"fail");
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

    @ResponseBody
    @RequestMapping(path = "/getPhoneVerificationCode.do")
    public Map getPhoneVerificationCode(String phone,HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("发送手机短信验证码。。。。"+"    "+phone);
        if((User)userService.findOne(phone)==null){
            HttpSession session = request.getSession(true);
            String code= SendSMS.randomVCode();
            System.out.println(code+"..............");
            //SendSMS.sendRegisterMsg(phone,code,"130486");
            session.setAttribute("_phoneVerificationCode",code);
            return ajaxReturn(true,"发送成功");
        }else{
            return ajaxReturn(false,"该手机号码已注册");
        }
    }
}
