package com.mapper;

import com.model.RecruitDO;
import com.model.RecruitDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    long countByExample(RecruitDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    int deleteByExample(RecruitDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    int insert(RecruitDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    int insertSelective(RecruitDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    List<RecruitDO> selectByExample(RecruitDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") RecruitDO record, @Param("example") RecruitDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") RecruitDO record, @Param("example") RecruitDOExample example);
}