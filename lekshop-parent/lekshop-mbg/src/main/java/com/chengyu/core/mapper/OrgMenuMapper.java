package com.chengyu.core.mapper;

import com.chengyu.core.model.OrgMenu;
import com.chengyu.core.model.OrgMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgMenuMapper {
    long countByExample(OrgMenuExample example);

    int deleteByExample(OrgMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrgMenu record);

    int insertSelective(OrgMenu record);

    List<OrgMenu> selectByExample(OrgMenuExample example);

    OrgMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrgMenu record, @Param("example") OrgMenuExample example);

    int updateByExample(@Param("record") OrgMenu record, @Param("example") OrgMenuExample example);

    int updateByPrimaryKeySelective(OrgMenu record);

    int updateByPrimaryKey(OrgMenu record);
}