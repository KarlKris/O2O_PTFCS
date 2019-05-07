package com.controller;

import com.model.VO.StudentModel;
import com.service.StudentMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liyuanwen
 * @date: 2019/5/6 16:23
 **/
@Controller
public class StudentMsgController extends BaseController{

    @Autowired
    private StudentMsgService studentMsgService;

    @RequestMapping(path = "/getMsg.do",method = RequestMethod.POST)
    @ResponseBody
    public Map getStudentMsg(String id){
        Map map = new HashMap();
        StudentModel sm =  studentMsgService.getStudentMsg(id);
        if (sm.getId().equals("")){
            map.put("status",false);
            return map;
        }
        map.put("status",true);
        map.put("data",sm);
        return map;
    }

    @RequestMapping(path = "/updateMsg.do",method = RequestMethod.POST)
    @ResponseBody
    public Map updateStudentMsg(StudentModel studentModel){
        int res = studentMsgService.updateStudentMsg(studentModel);
        if (res>0){
            return ajaxReturn(true,"success!");
        }
        return ajaxReturn(false,"update failed!");
    }

}
