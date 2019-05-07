package com.service;

import com.dao.address.AddressDao;
import com.dao.user.UserBus;
import com.dao.user.UserMsgDao;
import com.mapper.AddressMapper;
import com.mapper.CityMapper;
import com.mapper.UserMapper;
import com.model.AddressDO;
import com.model.PO.Address;
import com.model.PO.City;
import com.model.PO.User;
import com.model.UserMsgDO;
import com.model.VO.MessageModel;
import com.util.RandomUserName.RandomUName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author:liyuanwen
 * @date: 2019/1/7 19:46
 **/
@Service
public class UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    AddressDao addressDao;
    @Autowired
    UserMsgDao userMsgDao;
    @Autowired
    UserBus userBus;

    /**查询用户表不需要返回大量信息**/
    public Object findSome() {
        return null;
    }

    /**
     * 查询用户信息表
     **/
    public MessageModel selectOne(String id) {
        return userMapper.getUserMsg(id);
    }

    public Object findOne(String phone) {
        return userMapper.selectOne(phone);
    }

    public Object findSome(String object) {
        return cityMapper.findAny(object);
    }

    public Map addOne(Object object) {
        Map<String,Object> map = new HashMap();
        String phone=(String) object;
        User user=new User();
        user.setName(RandomUName.getStringRandom(8));
        user.setPhone(phone);
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setPsw(new Md5Hash(phone,user.getName()).toString());
        map.put("user",user);
        map.put("status",String.valueOf(userMapper.addOne(user)));
        return map;
    }

    public int addOneToMsg(User user,int role) {
        return userMsgDao.insert(user,role);
    }

    //更新用户基本信息
    public int updateUserMsg(MessageModel mm){
        //当前用户
        User user = userBus.getUser();
        //更新地址
        //1--获得城市id
        City city = cityMapper.getCity(mm.getCityName(),mm.getCityArea());
        //2--获得地址
        AddressDO addressDO = addressDao.getAddressByUserId(user.getId());
        //3--修改地址信息
        if (!addressDO.getCid().equals(city.getId())){
            addressDO.setCid(city.getId());
            //3.1--更新数据库
            int i = addressDao.updateAddressByAddressDO(addressDO);
            if (i<0){
                //3.2--更新失败，抛出异常
            }
        }
        //更新支付宝账号
        //1--获得用户支付宝账号
        UserMsgDO userMsgDO = userMsgDao.getUserMsgDOByUserId(user.getId());
        String payId = userMsgDO.getPayid();
        //2--更新支付宝账号信息
        if (!payId.equals(mm.getPayId())){
            userMsgDO.setPayid(mm.getPayId());
            int i = userMsgDao.updatePayNumByUserId(userMsgDO);
            if (i<0){
                //2.1--更新失败，抛出异常
            }
        }
        return 0;
    }


}
