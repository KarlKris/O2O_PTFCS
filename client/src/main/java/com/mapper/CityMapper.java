package com.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityMapper {

    @Select("select distinct cityName from city")
    List<String> findSome();

}
