package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsFeedback;
import com.chengyu.core.model.CmsFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsFeedbackMapper {
    long countByExample(CmsFeedbackExample example);

    int deleteByExample(CmsFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsFeedback record);

    int insertSelective(CmsFeedback record);

    List<CmsFeedback> selectByExample(CmsFeedbackExample example);

    CmsFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsFeedback record, @Param("example") CmsFeedbackExample example);

    int updateByExample(@Param("record") CmsFeedback record, @Param("example") CmsFeedbackExample example);

    int updateByPrimaryKeySelective(CmsFeedback record);

    int updateByPrimaryKey(CmsFeedback record);
}