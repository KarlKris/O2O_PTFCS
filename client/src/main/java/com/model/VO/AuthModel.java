package com.model.VO;

import lombok.Data;
import lombok.NonNull;

/**
 * @describe:
 * @author:liyuanwen
 * @date: 2019/5/9 12:13
 **/
@Data
public class AuthModel {

    private String id;
    private String frontBase64;
    private String reverseBase64;
}
