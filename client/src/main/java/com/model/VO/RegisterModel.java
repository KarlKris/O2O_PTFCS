package com.model.VO;

import lombok.Data;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 9:58
 **/
@Data
public class RegisterModel {

    private String phone;
    private String phoneVerificationCode;
    /**
     * 1--大学生
     * 2--家长
     * 3--客服
     * 4--超管
     **/
    private int role;

}
