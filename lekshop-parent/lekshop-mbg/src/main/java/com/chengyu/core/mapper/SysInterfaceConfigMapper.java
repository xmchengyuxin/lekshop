package com.chengyu.core.mapper;

import com.chengyu.core.model.SysInterfaceConfig;
import com.chengyu.core.model.SysInterfaceConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysInterfaceConfigMapper {
    long countByExample(SysInterfaceConfigExample example);

    int deleteByExample(SysInterfaceConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysInterfaceConfig record);

    int insertSelective(SysInterfaceConfig record);

    List<SysInterfaceConfig> selectByExampleWithBLOBs(SysInterfaceConfigExample example);

    List<SysInterfaceConfig> selectByExample(SysInterfaceConfigExample example);

    SysInterfaceConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysInterfaceConfig record, @Param("example") SysInterfaceConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") SysInterfaceConfig record, @Param("example") SysInterfaceConfigExample example);

    int updateByExample(@Param("record") SysInterfaceConfig record, @Param("example") SysInterfaceConfigExample example);

    int updateByPrimaryKeySelective(SysInterfaceConfig record);

    int updateByPrimaryKeyWithBLOBs(SysInterfaceConfig record);

    int updateByPrimaryKey(SysInterfaceConfig record);
}