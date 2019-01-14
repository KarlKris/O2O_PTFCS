package com.model.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 16:27
 **/
@Data
public class City implements Serializable {

    private Integer id;
    private String cityName;
    private String cityArea;
}
