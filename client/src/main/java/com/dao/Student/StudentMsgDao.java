package com.dao.Student;

import com.mapper.StudentMapper;
import com.mapper.StudentMsgDOMapper;
import com.model.StudentMsgDO;
import com.model.StudentMsgDOExample;
import com.model.VO.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author:liyuanwen
 * @date: 2019/5/6 16:25
 **/
@Repository
public class StudentMsgDao {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentMsgDOMapper studentMsgDOMapper;

    /**
     *  根据用户id
     *  获得学生信息（如果有的话）
     **/
    public StudentModel getStudentMsg(String id){
        StudentModel studentModel = studentMapper.getMsg(id);
        String score = studentModel.getComprehensive_liberal_or_science();
        //true为文科
        if (studentModel.isClassify()){
            studentModel.setComLiberal(score);
        }else {
            studentModel.setComScience(score);
        }
        return studentModel;
    }

    /**
     *  更新学生信息
     **/
    public int updateMag(StudentMsgDO studentMsgDO){
        StudentMsgDOExample example = new StudentMsgDOExample();
        example.createCriteria().andIdEqualTo(studentMsgDO.getId());
        return studentMsgDOMapper.updateByExample(studentMsgDO,example);
    }

    /**
     * 插入新的学生信息
     **/
    public int insert(StudentMsgDO studentMsgDO){
        return studentMsgDOMapper.insert(studentMsgDO);
    }

}
