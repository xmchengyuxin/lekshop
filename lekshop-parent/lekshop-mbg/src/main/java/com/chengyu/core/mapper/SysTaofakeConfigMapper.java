package com.chengyu.core.mapper;

import com.chengyu.core.model.SysTaofakeConfig;
import com.chengyu.core.model.SysTaofakeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTaofakeConfigMapper {
    long countByExample(SysTaofakeConfigExample example);

    int deleteByExample(SysTaofakeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysTaofakeConfig record);

    int insertSelective(SysTaofakeConfig record);

    List<SysTaofakeConfig> selectByExample(SysTaofakeConfigExample example);

    SysTaofakeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysTaofakeConfig record, @Param("example") SysTaofakeConfigExample example);

    int updateByExample(@Param("record") SysTaofakeConfig record, @Param("example") SysTaofakeConfigExample example);

    int updateByPrimaryKeySelective(SysTaofakeConfig record);

    int updateByPrimaryKey(SysTaofakeConfig record);
}