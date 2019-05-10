package com.Exception;

/**
 * @describe:自定义异常类
 * @author:liyuanwen
 * @date: 2019/5/9 9:43
 **/
public class CustomizeException extends RuntimeException{

    private String message;

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
