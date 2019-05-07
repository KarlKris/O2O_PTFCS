package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:liyuanwen
 * @date: 2019/5/5 20:18
 **/
@Controller
public class SysController {

    @RequestMapping(path = "/index")
    public String index() {
        return  "index";
    }

    @RequestMapping(path = {"/","/login"})
    public String login() {
        System.out.println("-------------------------------------------------------------------------" +
                "----------------------------------------------------------------------------");
        return  "login";
    }
}
