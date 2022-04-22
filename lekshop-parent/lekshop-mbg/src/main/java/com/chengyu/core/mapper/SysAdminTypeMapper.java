package com.chengyu.core.mapper;

import com.chengyu.core.model.SysAdminType;
import com.chengyu.core.model.SysAdminTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAdminTypeMapper {
    long countByExample(SysAdminTypeExample example);

    int deleteByExample(SysAdminTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysAdminType record);

    int insertSelective(SysAdminType record);

    List<SysAdminType> selectByExampleWithBLOBs(SysAdminTypeExample example);

    List<SysAdminType> selectByExample(SysAdminTypeExample example);

    SysAdminType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysAdminType record, @Param("example") SysAdminTypeExample example);

    int updateByExampleWithBLOBs(@Param("record") SysAdminType record, @Param("example") SysAdminTypeExample example);

    int updateByExample(@Param("record") SysAdminType record, @Param("example") SysAdminTypeExample example);

    int updateByPrimaryKeySelective(SysAdminType record);

    int updateByPrimaryKeyWithBLOBs(SysAdminType record);

    int updateByPrimaryKey(SysAdminType record);
}