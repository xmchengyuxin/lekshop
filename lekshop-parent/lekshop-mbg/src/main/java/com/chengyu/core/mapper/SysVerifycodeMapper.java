package com.chengyu.core.mapper;

import com.chengyu.core.model.SysVerifycode;
import com.chengyu.core.model.SysVerifycodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysVerifycodeMapper {
    long countByExample(SysVerifycodeExample example);

    int deleteByExample(SysVerifycodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysVerifycode record);

    int insertSelective(SysVerifycode record);

    List<SysVerifycode> selectByExample(SysVerifycodeExample example);

    SysVerifycode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysVerifycode record, @Param("example") SysVerifycodeExample example);

    int updateByExample(@Param("record") SysVerifycode record, @Param("example") SysVerifycodeExample example);

    int updateByPrimaryKeySelective(SysVerifycode record);

    int updateByPrimaryKey(SysVerifycode record);
}