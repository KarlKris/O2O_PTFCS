package com.dao.address;

import com.mapper.AddressDOMapper;
import com.model.AddressDO;
import com.model.AddressDOExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author:liyuanwen
 * @date: 2019/5/4 16:56
 **/
@Repository
public class AddressDao {

    @Autowired
    AddressDOMapper addressDOMapper;

    /**
     * 根据用户id和城市id
     * 返回用户地址信息
     **/
    public AddressDO getAddressByUserId(String userId){
        AddressDOExample example = new AddressDOExample();
        example.createCriteria().andUidEqualTo(userId);

        return addressDOMapper.selectByExample(example).get(0);
    }

    /**
     *  更新用户地址信息
     **/
    public int updateAddressByAddressDO(AddressDO addressDO){
        AddressDOExample example = new AddressDOExample();
        example.createCriteria().andIdEqualTo(addressDO.getId());
        return addressDOMapper.updateByExample(addressDO,example);
    }


}
