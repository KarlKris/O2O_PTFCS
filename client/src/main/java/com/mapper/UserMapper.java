package com.mapper;

import com.model.PO.User;
import org.apache.ibatis.annotations.Select;

/**
 *  用户映射类
 **/
public interface UserMapper {

    @Select("select*from User where phone=#{phone} ")
    User selectOne(String phone);

}
