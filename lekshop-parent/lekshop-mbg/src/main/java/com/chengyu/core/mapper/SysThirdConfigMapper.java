package com.chengyu.core.mapper;

import com.chengyu.core.model.SysThirdConfig;
import com.chengyu.core.model.SysThirdConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysThirdConfigMapper {
    long countByExample(SysThirdConfigExample example);

    int deleteByExample(SysThirdConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysThirdConfig record);

    int insertSelective(SysThirdConfig record);

    List<SysThirdConfig> selectByExample(SysThirdConfigExample example);

    SysThirdConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysThirdConfig record, @Param("example") SysThirdConfigExample example);

    int updateByExample(@Param("record") SysThirdConfig record, @Param("example") SysThirdConfigExample example);

    int updateByPrimaryKeySelective(SysThirdConfig record);

    int updateByPrimaryKey(SysThirdConfig record);
}