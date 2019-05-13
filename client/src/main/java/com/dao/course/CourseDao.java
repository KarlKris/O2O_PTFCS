package com.dao.course;

import com.mapper.CourseDOMapper;
import com.model.CourseDO;
import com.model.CourseDOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @describe:
 * @author:liyuanwen
 * @date: 2019/5/11 22:45
 **/
@Repository
public class CourseDao {

    @Autowired
    private CourseDOMapper courseDOMapper;

    /**
     *  获取所有课目
     **/
    public List<CourseDO> getAll(){
        CourseDOExample example = new CourseDOExample();
        example.createCriteria();

        return courseDOMapper.selectByExample(example);
    }
}
