package com.model.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 16:41
 **/
@Data
public class Course implements Serializable {

    private int id;
    private String name;
}
