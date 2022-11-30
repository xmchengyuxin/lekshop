package com.chengyu.core.mapper;

import com.chengyu.core.model.OrgRole;
import com.chengyu.core.model.OrgRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgRoleMapper {
    long countByExample(OrgRoleExample example);

    int deleteByExample(OrgRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrgRole record);

    int insertSelective(OrgRole record);

    List<OrgRole> selectByExample(OrgRoleExample example);

    OrgRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrgRole record, @Param("example") OrgRoleExample example);

    int updateByExample(@Param("record") OrgRole record, @Param("example") OrgRoleExample example);

    int updateByPrimaryKeySelective(OrgRole record);

    int updateByPrimaryKey(OrgRole record);
}