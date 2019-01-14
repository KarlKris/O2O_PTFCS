package com.model.VO;

import lombok.Data;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 16:12
 **/
@Data
public class LoginModel {

    private String phone;
    private String verificationCode;
    private String psw;
    private Boolean rem;
}
