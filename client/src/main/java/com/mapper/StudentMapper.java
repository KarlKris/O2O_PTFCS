package com.mapper;

import com.model.VO.StudentModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;

public interface StudentMapper {

    /**
     *  返回学生信息
     *  <ResultMap></ResultMap> == @Results
     **/
    @SelectProvider(type = StudentSqlProvider.class,method = "getMsg")
    @Results({
            @Result(column = "id",property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "chinese",property = "chinese", jdbcType = JdbcType.VARCHAR),
            @Result(column = "math",property = "math", jdbcType = JdbcType.VARCHAR),
            @Result(column = "english",property = "english", jdbcType = JdbcType.VARCHAR),
            @Result(column = "arts_or_science",property = "classify", javaType = Boolean.class),
            @Result(column = "comprehensive_liberal_or_science",property = "comprehensive_liberal_or_science", jdbcType = JdbcType.VARCHAR),
            @Result(column = "major",property = "major", jdbcType = JdbcType.VARCHAR),
            @Result(column = "university",property = "university", jdbcType = JdbcType.VARCHAR),
    })
    StudentModel getMsg(String id);





    class StudentSqlProvider{

        public String getMsg(){
            return new SQL()
                    .SELECT("id","chinese","math"
                            ,"english,arts_or_science","comprehensive_liberal_or_science"
                            ,"major","university")
                    .FROM("studentmsg")
                    .WHERE("id = #{id}")
                    .toString();
        }
    }
}
