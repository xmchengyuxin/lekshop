package com.chengyu.core.mapper;

import com.chengyu.core.model.SysInviteCode;
import com.chengyu.core.model.SysInviteCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysInviteCodeMapper {
    long countByExample(SysInviteCodeExample example);

    int deleteByExample(SysInviteCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysInviteCode record);

    int insertSelective(SysInviteCode record);

    List<SysInviteCode> selectByExample(SysInviteCodeExample example);

    SysInviteCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysInviteCode record, @Param("example") SysInviteCodeExample example);

    int updateByExample(@Param("record") SysInviteCode record, @Param("example") SysInviteCodeExample example);

    int updateByPrimaryKeySelective(SysInviteCode record);

    int updateByPrimaryKey(SysInviteCode record);
}