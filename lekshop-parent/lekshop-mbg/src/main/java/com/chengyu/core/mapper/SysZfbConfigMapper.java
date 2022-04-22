package com.chengyu.core.mapper;

import com.chengyu.core.model.SysZfbConfig;
import com.chengyu.core.model.SysZfbConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysZfbConfigMapper {
    long countByExample(SysZfbConfigExample example);

    int deleteByExample(SysZfbConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysZfbConfig record);

    int insertSelective(SysZfbConfig record);

    List<SysZfbConfig> selectByExampleWithBLOBs(SysZfbConfigExample example);

    List<SysZfbConfig> selectByExample(SysZfbConfigExample example);

    SysZfbConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysZfbConfig record, @Param("example") SysZfbConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") SysZfbConfig record, @Param("example") SysZfbConfigExample example);

    int updateByExample(@Param("record") SysZfbConfig record, @Param("example") SysZfbConfigExample example);

    int updateByPrimaryKeySelective(SysZfbConfig record);

    int updateByPrimaryKeyWithBLOBs(SysZfbConfig record);

    int updateByPrimaryKey(SysZfbConfig record);
}