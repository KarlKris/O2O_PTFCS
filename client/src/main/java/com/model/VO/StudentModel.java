package com.model.VO;

import lombok.Data;

/**
 * @author:liyuanwen
 * @date: 2019/5/6 16:27
 **/
@Data
public class StudentModel {
    private String id;
    private String chinese;
    private String math;
    private String english;
    /**
     * 0-false-理科
     * 1-true-文科
     **/
    private boolean classify;
    private String comprehensive_liberal_or_science;
    private String comLiberal;
    private String comScience;
    private String major;
    private String university;
}
