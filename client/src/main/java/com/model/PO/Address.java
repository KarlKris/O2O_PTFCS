package com.model.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 16:28
 **/
@Data
public class Address implements Serializable {

    private String id;
    private User user;
    private City city;
    private String addressDetail;
}
