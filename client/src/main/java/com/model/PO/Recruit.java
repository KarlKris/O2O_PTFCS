package com.model.PO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 16:36
 **/
@Data
public class Recruit implements Serializable {

    private String id;
    private Time time;
    private String title;
    private Address address;
    private List<Course> courseList;
    private Double money;
    private String modeOfPayment;
    private int number;
    private boolean status;
}
