package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberAccountLog;
import com.chengyu.core.model.UmsMemberAccountLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberAccountLogMapper {
    long countByExample(UmsMemberAccountLogExample example);

    int deleteByExample(UmsMemberAccountLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberAccountLog record);

    int insertSelective(UmsMemberAccountLog record);

    List<UmsMemberAccountLog> selectByExample(UmsMemberAccountLogExample example);

    UmsMemberAccountLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberAccountLog record, @Param("example") UmsMemberAccountLogExample example);

    int updateByExample(@Param("record") UmsMemberAccountLog record, @Param("example") UmsMemberAccountLogExample example);

    int updateByPrimaryKeySelective(UmsMemberAccountLog record);

    int updateByPrimaryKey(UmsMemberAccountLog record);
}