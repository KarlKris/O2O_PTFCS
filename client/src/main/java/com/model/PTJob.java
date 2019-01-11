package com.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 16:36
 **/
@Data
public class PTJob {

    private String id;
    private Date time;
    private String title;
    private Address address;
    private List<Course> course;
    private Double money;
}
