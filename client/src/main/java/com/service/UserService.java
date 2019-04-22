package com.service;

import com.mapper.AddressMapper;
import com.mapper.CityMapper;
import com.mapper.UserMapper;
import com.model.PO.Address;
import com.model.PO.City;
import com.model.PO.User;
import com.model.VO.MessageModel;
import com.model.VO.RegisterModel;
import com.util.RandomUserName.RandomUName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
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
public class UserService implements BaseService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CityMapper cityMapper;

    /**查询用户表不需要返回大量信息**/
    @Override
    public Object findSome() {
        return null;
    }

    /**
     * 查询用户信息表
     **/
    @Override
    public Object selectOne(String id) {
        return userMapper.getUserMsg(id);
    }

    @Override
    public Object findOne(String phone) {
        return userMapper.selectOne(phone);
    }

    @Override
    public Object findSome(String object) {
        return cityMapper.findAny(object);
    }

    @Override
    public Boolean addOne(Object object) {
        String phone=(String) object;
        User user=new User();
        user.setName(RandomUName.getStringRandom(8));
        user.setPhone(phone);
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setPsw(new Md5Hash(phone,user.getName()).toString());
        return userMapper.addOne(user);
    }

    @Override
    public Object addOneToMsg(Object object) {
        MessageModel mm=(MessageModel) object;
        //添加地址信息To数据库
        City city=cityMapper.getCity(mm.getCityName(),mm.getCityArea());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String aid=UUID.randomUUID().toString();
        Address address=new Address();
        address.setId(aid);address.setUser(user);address.setCity(city);
        address.setAddressDetail(mm.getAddressDetail());
        addressMapper.addAddress(address);
        Map map = new HashMap();
        map.put("id",UUID.randomUUID().toString());
        map.put("uid",user.getId());
        map.put("aid",address.getId());
        map.put("payId",mm.getPayId());
        map.put("role",mm.isRole());
        //如果是学生
        if (!mm.isRole()){
            userMapper.addUserMsg(map);
            map.put("chinese","#{chinese}");
            map.put("math","#{math}");
            map.put("english","#{english}");
            map.put("major","#{major}");
            map.put("university","#{university}");
            map.put("arts_or_science","#{arts_or_science}");
            map.put("comprehensive_liberal_or_science",
                    "#{comprehensive_liberal_or_science}");
            userMapper.addStudentMsg(map);
        }else{
            userMapper.addUserMsg(map);
        }
        return null;
    }
}
