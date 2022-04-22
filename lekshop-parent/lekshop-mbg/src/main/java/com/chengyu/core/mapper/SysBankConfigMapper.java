package com.chengyu.core.mapper;

import com.chengyu.core.model.SysBankConfig;
import com.chengyu.core.model.SysBankConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysBankConfigMapper {
    long countByExample(SysBankConfigExample example);

    int deleteByExample(SysBankConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysBankConfig record);

    int insertSelective(SysBankConfig record);

    List<SysBankConfig> selectByExample(SysBankConfigExample example);

    SysBankConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysBankConfig record, @Param("example") SysBankConfigExample example);

    int updateByExample(@Param("record") SysBankConfig record, @Param("example") SysBankConfigExample example);

    int updateByPrimaryKeySelective(SysBankConfig record);

    int updateByPrimaryKey(SysBankConfig record);
}