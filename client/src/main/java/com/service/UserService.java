package com.service;

import com.mapper.UserMapper;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 19:46
 **/
@Service
public class UserService implements BaseService{

    @Autowired
    UserMapper userMapper;

    @Override
    public Object selectOne(String id) {
        return null;
    }

    @Override
    public Object findOne(String phone) {
        return userMapper.selectOne(phone);
    }
}
