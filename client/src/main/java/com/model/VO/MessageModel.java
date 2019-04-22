package com.model.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author:liyuanwen
 * @date: 2019/1/21 12:19
 **/
@Data
public class MessageModel implements Serializable {

    private String payId;
    private boolean role;
    private String chinese;
    private String math;
    private String english;
    private boolean arts_or_science;
    private String comprehensive_liberal_or_science;
    private String major;
    private String university;
    private String cityName;
    private String cityArea;
    private String addressDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageModel that = (MessageModel) o;
        return role == that.role &&
                arts_or_science == that.arts_or_science &&
                Objects.equals(payId, that.payId) &&
                Objects.equals(chinese, that.chinese) &&
                Objects.equals(math, that.math) &&
                Objects.equals(english, that.english) &&
                Objects.equals(comprehensive_liberal_or_science, that.comprehensive_liberal_or_science) &&
                Objects.equals(major, that.major) &&
                Objects.equals(university, that.university) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(cityArea, that.cityArea) &&
                Objects.equals(addressDetail, that.addressDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payId, role, chinese, math, english, arts_or_science, comprehensive_liberal_or_science, major, university, cityName, cityArea, addressDetail);
    }
}
