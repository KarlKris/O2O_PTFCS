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
    private int role;
    private String cityName;
    private String cityArea;
    private String addressDetail;

}
