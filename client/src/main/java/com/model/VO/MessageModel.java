package com.model.VO;

import lombok.Data;

/**
 * @author:liyuanwen
 * @date: 2019/1/21 12:19
 **/
@Data
public class MessageModel {

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
}
