package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberPointLog;
import com.chengyu.core.model.UmsMemberPointLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberPointLogMapper {
    long countByExample(UmsMemberPointLogExample example);

    int deleteByExample(UmsMemberPointLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberPointLog record);

    int insertSelective(UmsMemberPointLog record);

    List<UmsMemberPointLog> selectByExample(UmsMemberPointLogExample example);

    UmsMemberPointLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberPointLog record, @Param("example") UmsMemberPointLogExample example);

    int updateByExample(@Param("record") UmsMemberPointLog record, @Param("example") UmsMemberPointLogExample example);

    int updateByPrimaryKeySelective(UmsMemberPointLog record);

    int updateByPrimaryKey(UmsMemberPointLog record);
}