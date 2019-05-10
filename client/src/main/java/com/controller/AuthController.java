package com.controller;

import com.Exception.CustomizeException;
import com.model.VO.AuthModel;
import com.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liyuanwen
 * @date: 2019/5/9 8:51
 **/
@Controller
public class AuthController extends BaseController{

    @Autowired
    private AuthService authService;

    /**
     * 执行认证
     **/
    @RequestMapping(path = "/auth.do",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map authHandler(AuthModel authModel) throws CustomizeException {
        authService.insert(authModel);
        return ajaxReturn(true,"认证成功");
    }

}
