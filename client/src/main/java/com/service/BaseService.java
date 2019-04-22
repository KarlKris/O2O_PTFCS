package com.service;

/**
 * serveice基类
 **/
public interface BaseService {

    /**根据主键查找**/
    Object selectOne(String id);

    /**根据某个字段查找**/
    Object findOne(String phone);

    /**查找一些**/
    Object findSome();

    /**查找一些**/
    Object findSome(String object);

    /**
     * 添加一条属性
     **/
    Boolean addOne(Object object);

    /**
     *  插入用户信息
     **/
     Object addOneToMsg(Object object);
}

