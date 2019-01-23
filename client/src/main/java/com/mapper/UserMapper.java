package com.mapper;

import com.model.PO.User;
import com.model.VO.MessageModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 *  用户映射类
 **/
public interface UserMapper {

    @Select("select*from User where phone=#{phone} ")
    User selectOne(String phone);

    @Insert({"insert user(id,name,phone,psw) value( #{id},#{name},#{phone},#{psw} )"})
    boolean addOne(User user);


    boolean addUserMsg(Map map);

    @SelectProvider(type = UserDaoProvider.class,method = "getUserMsg")
    @ResultType(MessageModel.class)
    MessageModel getUserMsg(String phone);

    class UserDaoProvider{
        public String getUserMsg(String phone){
            String sql = new SQL()
                    .SELECT("payId","role","chinese","math"
                            ,"english,arts_or_science","comprehensive_liberal_or_science"
                            ,"major","university","cityName","cityArea","addressDetail")
                    .FROM("user inner join "
                                    +"(usermsg inner join studentmsg on usermsg.id=studentmsg.id) "
                                    + "on user.id = usermsg.uid",
                            "address inner join city on address.cid = city.id")
                    .WHERE("phone=#{phone}")
                    .toString();

            return sql;
        }

        public String setUserMsg(Map map){
            return new SQL()
                    .toString();
        }
    }

}
