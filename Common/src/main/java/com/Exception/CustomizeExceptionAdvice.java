package com.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe:自定义异常处理
 * @author:liyuanwen
 * @date: 2019/5/9 9:57
 **/
@ControllerAdvice
public class CustomizeExceptionAdvice {

    /**
     * 拦截捕捉自定义异常 CustomizeException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CustomizeException.class)
    public Map customizeExceptionHandler(CustomizeException ex) {
        System.out.println("处理异常。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        Map map = new HashMap();
        map.put("status", false);
        map.put("message", ex.getMessage());
        return map;
    }
}
