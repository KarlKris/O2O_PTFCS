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
public class SysService implements BaseService {

    @Autowired
    CityMapper cityMapper;


    @Override
    public Object selectOne(String id) {
        return null;
    }

    @Override
    public Object findOne(String phone) {

        return null;
    }

    @Override
    public Object findSome() {
        return cityMapper.findSome();
    }

    @Override
    public Object findSome(String object) {
        return cityMapper.findAny(object);
    }

    @Override
    public Boolean addOne(Object object) {
        return null;
    }

    @Override
    public Object addOneToMsg(Object object) {
        return null;
    }
}
