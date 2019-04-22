package com.mapper;

import com.model.PO.Address;
import org.apache.ibatis.annotations.Insert;

public interface AddressMapper {

    @Insert("insert into address values(#{id},#{user.id},#{city.id},#{addressDetail})")
    int addAddress(Address address);
}
