package com.service;

import com.dao.Student.StudentMsgDao;
import com.dao.user.UserMsgDao;
import com.model.StudentMsgDO;
import com.model.UserMsgDO;
import com.model.VO.StudentModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author:liyuanwen
 * @date: 2019/5/6 16:24
 **/
@Service
public class StudentMsgService {

    @Autowired
    private StudentMsgDao studentMsgDao;
    @Autowired
    private UserMsgDao userMsgDao;

    /**
     * 根据用户id
     * 获得学生信息
     **/
    public StudentModel getStudentMsg(String userId){
        StudentModel studentModel = Optional.ofNullable(studentMsgDao.getStudentMsg(userId)).orElseGet(()->new StudentModel());
        return studentModel;
    }

    /**
     * 更新学生信息
     **/
    public int updateStudentMsg(StudentModel studentModel){
        String id = studentModel.getId();
        StudentModel temp = getStudentMsg(id);
        StudentMsgDO studentMsgDO = new StudentMsgDO();
        BeanUtils.copyProperties(studentModel,studentMsgDO);
        if (temp.getId()==null || temp.getId().equals("")){
            return studentMsgDao.insert(studentMsgDO);
        }
        return studentMsgDao.updateMag(studentMsgDO);
    }
}
