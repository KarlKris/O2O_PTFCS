package com.dao.auth;

import com.Exception.CustomizeException;
import com.mapper.AuthDOMapper;
import com.model.AuthDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author:liyuanwen
 * @date: 2019/5/9 9:17
 **/
@Repository
public class AuthDao {

    @Autowired
    private AuthDOMapper authDOMapper;

    /**
     * 插入新的认证信息
     **/
    public int insert(AuthDO authDO){
        int res = authDOMapper.insert(authDO);
        if (res<0){
            throw new CustomizeException("插入身份证信息失败!");
        }
        return res;
    }
}
