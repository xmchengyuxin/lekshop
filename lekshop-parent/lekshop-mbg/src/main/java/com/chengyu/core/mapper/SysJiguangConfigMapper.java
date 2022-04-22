package com.chengyu.core.mapper;

import com.chengyu.core.model.SysJiguangConfig;
import com.chengyu.core.model.SysJiguangConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysJiguangConfigMapper {
    long countByExample(SysJiguangConfigExample example);

    int deleteByExample(SysJiguangConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysJiguangConfig record);

    int insertSelective(SysJiguangConfig record);

    List<SysJiguangConfig> selectByExample(SysJiguangConfigExample example);

    SysJiguangConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysJiguangConfig record, @Param("example") SysJiguangConfigExample example);

    int updateByExample(@Param("record") SysJiguangConfig record, @Param("example") SysJiguangConfigExample example);

    int updateByPrimaryKeySelective(SysJiguangConfig record);

    int updateByPrimaryKey(SysJiguangConfig record);
}