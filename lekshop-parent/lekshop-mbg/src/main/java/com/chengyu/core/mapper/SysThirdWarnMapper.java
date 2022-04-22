package com.chengyu.core.mapper;

import com.chengyu.core.model.SysThirdWarn;
import com.chengyu.core.model.SysThirdWarnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysThirdWarnMapper {
    long countByExample(SysThirdWarnExample example);

    int deleteByExample(SysThirdWarnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysThirdWarn record);

    int insertSelective(SysThirdWarn record);

    List<SysThirdWarn> selectByExample(SysThirdWarnExample example);

    SysThirdWarn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysThirdWarn record, @Param("example") SysThirdWarnExample example);

    int updateByExample(@Param("record") SysThirdWarn record, @Param("example") SysThirdWarnExample example);

    int updateByPrimaryKeySelective(SysThirdWarn record);

    int updateByPrimaryKey(SysThirdWarn record);
}