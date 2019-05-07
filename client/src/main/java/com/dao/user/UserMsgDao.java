package com.dao.user;

import com.mapper.UserMsgDOMapper;
import com.model.PO.User;
import com.model.UserMsgDO;
import com.model.UserMsgDOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author:liyuanwen
 * @date: 2019/5/5 16:55
 **/
@Repository
public class UserMsgDao {

    @Autowired
    private UserMsgDOMapper userMsgDOMapper;

    /**
     * 注册成功时，插入用户信息
     **/
    public int insert(User user,int role){
        UserMsgDO userMsgDO = new UserMsgDO();
        userMsgDO.setId(UUID.randomUUID().toString());
        userMsgDO.setUid(user.getId());
        userMsgDO.setPayid(user.getPhone());
        userMsgDO.setRole(role);
        return userMsgDOMapper.insert(userMsgDO);
    }

    /**
     *  根据用户id返回用户信息
     **/
    public UserMsgDO getUserMsgDOByUserId(String userId){
        UserMsgDOExample example = new UserMsgDOExample();
        example.createCriteria().andUidEqualTo(userId);
        return userMsgDOMapper.selectByExample(example).get(0);
    }

    /**
     * 根据用户id
     * 更新支付宝账号
     **/
    public int updatePayNumByUserId(UserMsgDO userMsgDO){
        UserMsgDOExample example = new UserMsgDOExample();
        example.createCriteria().andUidEqualTo(userMsgDO.getUid());
        return userMsgDOMapper.updateByExample(userMsgDO,example);
    }
}
