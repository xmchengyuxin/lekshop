package com.chengyu.core.mapper;

import com.chengyu.core.model.OrgRoleMenu;
import com.chengyu.core.model.OrgRoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgRoleMenuMapper {
    long countByExample(OrgRoleMenuExample example);

    int deleteByExample(OrgRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrgRoleMenu record);

    int insertSelective(OrgRoleMenu record);

    List<OrgRoleMenu> selectByExample(OrgRoleMenuExample example);

    OrgRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrgRoleMenu record, @Param("example") OrgRoleMenuExample example);

    int updateByExample(@Param("record") OrgRoleMenu record, @Param("example") OrgRoleMenuExample example);

    int updateByPrimaryKeySelective(OrgRoleMenu record);

    int updateByPrimaryKey(OrgRoleMenu record);
}