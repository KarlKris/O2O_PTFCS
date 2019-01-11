package com.model;

import lombok.Data;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 16:28
 **/
@Data
public class Address {

    private String id;
    private User user;
    private City city;
    private String addressDetail;
}
