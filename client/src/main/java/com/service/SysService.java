package com.service;

import com.dao.Pay.PayDao;
import com.dao.course.CourseDao;
import com.mapper.CityMapper;
import com.model.CourseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:liyuanwen
 * @date: 2019/1/10 8:39
 **/
@Service
public class SysService {

    @Autowired
    CityMapper cityMapper;
    @Autowired
    CourseDao courseDao;
    @Autowired
    PayDao payDao;

    public Object findSome() {
        return cityMapper.findSome();
    }

    public Object findSome(String object) {
        return cityMapper.findAny(object);
    }

    public List<CourseDO> getAllCources(){
        return courseDao.getAll();
    }
}
