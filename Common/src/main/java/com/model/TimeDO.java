package com.model;

import java.util.Date;

public class TimeDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column time.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column time.end_time
     *
     * @mbg.generated
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column time.class_hour
     *
     * @mbg.generated
     */
    private Integer classHour;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column time.start_time
     *
     * @mbg.generated
     */
    private Date startTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column time.id
     *
     * @return the value of time.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column time.id
     *
     * @param id the value for time.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column time.end_time
     *
     * @return the value of time.end_time
     *
     * @mbg.generated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column time.end_time
     *
     * @param endTime the value for time.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column time.class_hour
     *
     * @return the value of time.class_hour
     *
     * @mbg.generated
     */
    public Integer getClassHour() {
        return classHour;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column time.class_hour
     *
     * @param classHour the value for time.class_hour
     *
     * @mbg.generated
     */
    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column time.start_time
     *
     * @return the value of time.start_time
     *
     * @mbg.generated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column time.start_time
     *
     * @param startTime the value for time.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}