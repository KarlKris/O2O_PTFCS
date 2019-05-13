package com.dao.user;

import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @describe:
 * @author:liyuanwen
 * @date: 2019/5/11 15:55
 **/
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    /**
     *  修改用户名
     **/
    public int modifyUserName(String id,String name){
        return userMapper.modifyUserName(id,name);
    }

}
