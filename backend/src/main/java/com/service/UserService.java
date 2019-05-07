package com.service;

import com.mapper.UserMapper;
import com.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:liyuanwen
 * @date: 2019/5/5 19:50
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByPhone(String phone){
        return userMapper.selectOne(phone);
    }
}
