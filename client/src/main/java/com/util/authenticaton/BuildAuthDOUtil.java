package com.util.authenticaton;

import com.alibaba.fastjson.JSONObject;
import com.model.AuthDO;

/**
 * @describe:
 * @author:liyuanwen
 * @date: 2019/5/9 12:52
 **/
public class BuildAuthDOUtil {

    public static void buildInFace(JSONObject jsonObject,AuthDO authDO){
        authDO.setAddress(jsonObject.getString("address"));
        authDO.setNationality(jsonObject.getString("nationality"));
        authDO.setNum(jsonObject.getString("num"));
        authDO.setSex(jsonObject.getString("sex"));
        authDO.setName(jsonObject.getString("name"));
        authDO.setBirth(jsonObject.getString("birth"));
    }

    public static void buildInBack(JSONObject jsonObject,AuthDO authDO){
        authDO.setStartDate(jsonObject.getString("start_date"));
        authDO.setEndDate(jsonObject.getString("end_date"));
        authDO.setIssue(jsonObject.getString("issue"));
    }

    public static void build(JSONObject jsonObject,AuthDO authDO,String side){
        if (side.equals("face")){
            buildInFace(jsonObject,authDO);
        }else {
            buildInBack(jsonObject,authDO);
        }
    }
}
