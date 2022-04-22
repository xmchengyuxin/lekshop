package com.chengyu.core.mapper;

import com.chengyu.core.model.SysApproveConfig;
import com.chengyu.core.model.SysApproveConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysApproveConfigMapper {
    long countByExample(SysApproveConfigExample example);

    int deleteByExample(SysApproveConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysApproveConfig record);

    int insertSelective(SysApproveConfig record);

    List<SysApproveConfig> selectByExample(SysApproveConfigExample example);

    SysApproveConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysApproveConfig record, @Param("example") SysApproveConfigExample example);

    int updateByExample(@Param("record") SysApproveConfig record, @Param("example") SysApproveConfigExample example);

    int updateByPrimaryKeySelective(SysApproveConfig record);

    int updateByPrimaryKey(SysApproveConfig record);
}