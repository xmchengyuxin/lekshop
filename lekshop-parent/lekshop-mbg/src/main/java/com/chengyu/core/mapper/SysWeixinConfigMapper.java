package com.chengyu.core.mapper;

import com.chengyu.core.model.SysWeixinConfig;
import com.chengyu.core.model.SysWeixinConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysWeixinConfigMapper {
    long countByExample(SysWeixinConfigExample example);

    int deleteByExample(SysWeixinConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysWeixinConfig record);

    int insertSelective(SysWeixinConfig record);

    List<SysWeixinConfig> selectByExample(SysWeixinConfigExample example);

    SysWeixinConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysWeixinConfig record, @Param("example") SysWeixinConfigExample example);

    int updateByExample(@Param("record") SysWeixinConfig record, @Param("example") SysWeixinConfigExample example);

    int updateByPrimaryKeySelective(SysWeixinConfig record);

    int updateByPrimaryKey(SysWeixinConfig record);
}