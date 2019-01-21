package com.service;

import com.mapper.UserMapper;
import com.model.PO.User;
import com.model.VO.RegisterModel;
import com.util.RandomUserName.RandomUName;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 19:46
 **/
@Service
public class UserService implements BaseService{

    @Autowired
    UserMapper userMapper;

    /**查询用户表不需要返回大量信息**/
    @Override
    public Object findSome() {
        return null;
    }

    /**
     * 查询用户信息表
     **/
    @Override
    public Object selectOne(String id) {
        return userMapper.getUserMsg(id);
    }

    @Override
    public Object findOne(String phone) {
        return userMapper.selectOne(phone);
    }

    @Override
    public Object findSome(String object) {
        return null;
    }

    @Override
    public Boolean addOne(Object object) {
        String phone=(String) object;
        User user=new User();
        user.setName(RandomUName.getStringRandom(8));
        user.setPhone(phone);
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setPsw(new Md5Hash(phone,user.getName()).toString());
        return userMapper.addOne(user);
    }
}
