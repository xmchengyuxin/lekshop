package com.chengyu.core.mapper;

import com.chengyu.core.model.SysAdminOperationLog;
import com.chengyu.core.model.SysAdminOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAdminOperationLogMapper {
    long countByExample(SysAdminOperationLogExample example);

    int deleteByExample(SysAdminOperationLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAdminOperationLog record);

    int insertSelective(SysAdminOperationLog record);

    List<SysAdminOperationLog> selectByExampleWithBLOBs(SysAdminOperationLogExample example);

    List<SysAdminOperationLog> selectByExample(SysAdminOperationLogExample example);

    SysAdminOperationLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAdminOperationLog record, @Param("example") SysAdminOperationLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SysAdminOperationLog record, @Param("example") SysAdminOperationLogExample example);

    int updateByExample(@Param("record") SysAdminOperationLog record, @Param("example") SysAdminOperationLogExample example);

    int updateByPrimaryKeySelective(SysAdminOperationLog record);

    int updateByPrimaryKeyWithBLOBs(SysAdminOperationLog record);

    int updateByPrimaryKey(SysAdminOperationLog record);
}