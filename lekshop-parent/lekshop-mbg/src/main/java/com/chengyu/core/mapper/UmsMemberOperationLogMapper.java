package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberOperationLog;
import com.chengyu.core.model.UmsMemberOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberOperationLogMapper {
    long countByExample(UmsMemberOperationLogExample example);

    int deleteByExample(UmsMemberOperationLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberOperationLog record);

    int insertSelective(UmsMemberOperationLog record);

    List<UmsMemberOperationLog> selectByExampleWithBLOBs(UmsMemberOperationLogExample example);

    List<UmsMemberOperationLog> selectByExample(UmsMemberOperationLogExample example);

    UmsMemberOperationLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberOperationLog record, @Param("example") UmsMemberOperationLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UmsMemberOperationLog record, @Param("example") UmsMemberOperationLogExample example);

    int updateByExample(@Param("record") UmsMemberOperationLog record, @Param("example") UmsMemberOperationLogExample example);

    int updateByPrimaryKeySelective(UmsMemberOperationLog record);

    int updateByPrimaryKeyWithBLOBs(UmsMemberOperationLog record);

    int updateByPrimaryKey(UmsMemberOperationLog record);
}