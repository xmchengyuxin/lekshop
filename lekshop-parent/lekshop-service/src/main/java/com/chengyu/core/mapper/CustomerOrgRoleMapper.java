package com.chengyu.core.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerOrgRoleMapper {

    void addRoleMenuList(@Param("roleId")Integer roleId, @Param("menuIdList") List<Integer> menuIdList);
}