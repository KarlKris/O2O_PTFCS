package com.model.PO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:liyuanwen
 * @date: 2019/1/11 11:08
 **/
@Data
public class CourseTime implements Serializable {

    private String id;
    private int week;
    private String time;
    private Date startTime;
    private Date endTime;

}
