package com.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 9:43
 **/
@Data
public class User implements Serializable {

    private String id;
    private String phone;
    private String psw;
    private String name;
}
