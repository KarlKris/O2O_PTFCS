package com.dao.user;

import com.model.PO.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Repository;

/**
 * @author:liyuanwen
 * @date: 2019/5/4 16:55
 **/
@Repository
public class UserBus {

    //获得当前用户
    public  User getUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

}
