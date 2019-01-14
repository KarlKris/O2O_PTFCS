package com.model.PO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author:liyuanwen
 * @date: 2019/1/11 11:07
 **/
@Data
public class Time implements Serializable {

    private String id;
    private User user;
    private Date startTime;
    private List<CourseTime> courseTimeList;
    private int cycle;


}
