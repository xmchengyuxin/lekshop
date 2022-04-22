package com.chengyu.core.mapper;

import com.chengyu.core.model.SysAlismsConfig;
import com.chengyu.core.model.SysAlismsConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAlismsConfigMapper {
    long countByExample(SysAlismsConfigExample example);

    int deleteByExample(SysAlismsConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysAlismsConfig record);

    int insertSelective(SysAlismsConfig record);

    List<SysAlismsConfig> selectByExample(SysAlismsConfigExample example);

    SysAlismsConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysAlismsConfig record, @Param("example") SysAlismsConfigExample example);

    int updateByExample(@Param("record") SysAlismsConfig record, @Param("example") SysAlismsConfigExample example);

    int updateByPrimaryKeySelective(SysAlismsConfig record);

    int updateByPrimaryKey(SysAlismsConfig record);
}