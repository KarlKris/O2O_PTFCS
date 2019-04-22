package com.mapper;

import com.model.PO.City;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityMapper {

    @Select("select distinct cityName from city")
    List<String> findSome();

    @Select("select cityArea from city where cityName = #{name}")
    List<String> findAny(String name);

    @Select("select * from city where cityName=#{cityName} and cityArea=#{cityArea}")
    City getCity(String cityName,String cityArea);


}
