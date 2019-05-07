package com.service;

import com.mapper.CityMapper;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 8:39
 **/
@Service
public class SysService {

    @Autowired
    CityMapper cityMapper;


    public Object selectOne(String id) {
        return null;
    }

    public Object findOne(String phone) {

        return null;
    }

    public Object findSome() {
        return cityMapper.findSome();
    }

    public Object findSome(String object) {
        return cityMapper.findAny(object);
    }

    public Boolean addOne(Object object) {
        return null;
    }

    public Object addOneToMsg(Object object) {
        return null;
    }
}
