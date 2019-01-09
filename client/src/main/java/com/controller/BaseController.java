package com.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李远文
 * 控制器基类
 * 就一个方法用以响应ajax
 **/
public class BaseController {

    /**
     *  用Map作为响应ajax返回类型
     **/
    public Map ajaxReturn(boolean st, String msg) {
        Map map = new HashMap();
        map.put("status", st);
        map.put("message", msg);
        return map;
    }
}
