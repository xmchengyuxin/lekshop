package com.chengyu.core.mapper;

import com.chengyu.core.model.OrgDept;
import com.chengyu.core.model.OrgDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgDeptMapper {
    long countByExample(OrgDeptExample example);

    int deleteByExample(OrgDeptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrgDept record);

    int insertSelective(OrgDept record);

    List<OrgDept> selectByExample(OrgDeptExample example);

    OrgDept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrgDept record, @Param("example") OrgDeptExample example);

    int updateByExample(@Param("record") OrgDept record, @Param("example") OrgDeptExample example);

    int updateByPrimaryKeySelective(OrgDept record);

    int updateByPrimaryKey(OrgDept record);
}