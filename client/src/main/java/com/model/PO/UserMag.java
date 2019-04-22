package com.model.PO;

import lombok.Data;

/**
 * @author:liyuanwen
 * @date: 2019/3/4 11:16
 **/
@Data
public class UserMag {

    private String id;
    private User uid;
    private Address aid;
    private String payId;
    private boolean role;
}
