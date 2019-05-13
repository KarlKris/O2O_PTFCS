package com.mapper;

import com.model.CourseDO;
import com.model.CourseDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    long countByExample(CourseDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    int deleteByExample(CourseDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    int insert(CourseDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    int insertSelective(CourseDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    List<CourseDO> selectByExample(CourseDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CourseDO record, @Param("example") CourseDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CourseDO record, @Param("example") CourseDOExample example);
}