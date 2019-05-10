package com.service;

import com.Exception.CustomizeException;
import com.dao.auth.AuthDao;
import com.model.AuthDO;
import com.model.VO.AuthModel;
import com.util.authenticaton.AliYunAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.util.authenticaton.AliYunAuthentication.auchenticatiion;

/**
 * @author:liyuanwen
 * @date: 2019/5/9 8:53
 **/
@Service
public class AuthService {

    @Autowired
    private AuthDao authDao;

    /**
     *  插入身份信息
     **/
    public int insert(AuthModel authModel) throws CustomizeException{
        String front = authModel.getFrontBase64();
        String recerse = authModel.getReverseBase64();
        if ((front==null || front.equals("")) ||(recerse==null || recerse.equals("")) ){
            throw  new CustomizeException("请放入正确的两张照片!");
        }
        AuthDO authDO = new AuthDO();
        //调用阿里云接口
        Map map = auchenticatiion(front,"face",authDO);
        if (!(boolean)map.get("status")){
            throw new CustomizeException((String) map.get("msg"));
        }
        map = auchenticatiion(recerse,"back",authDO);
        if (!(boolean)map.get("status")){
            throw new CustomizeException((String) map.get("msg"));
        }
        //存储信息
        authDO.setId(authModel.getId());
        return  authDao.insert(authDO);
    }
}
