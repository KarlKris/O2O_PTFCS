package com.mapper;

import com.model.CourseTimeDO;
import com.model.CourseTimeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseTimeDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    long countByExample(CourseTimeDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    int deleteByExample(CourseTimeDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    int insert(CourseTimeDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    int insertSelective(CourseTimeDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    List<CourseTimeDO> selectByExample(CourseTimeDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CourseTimeDO record, @Param("example") CourseTimeDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coursetime
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CourseTimeDO record, @Param("example") CourseTimeDOExample example);
}