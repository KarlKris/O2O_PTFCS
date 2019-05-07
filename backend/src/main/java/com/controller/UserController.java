package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author:liyuanwen
 * @date: 2019/5/5 22:34
 **/
@Controller
public class UserController extends BaseController{

    @RequestMapping(path = "/login.do",method = RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
    public Map login(String username,String password){
        return ajaxReturn(true,"success");
    }
}
