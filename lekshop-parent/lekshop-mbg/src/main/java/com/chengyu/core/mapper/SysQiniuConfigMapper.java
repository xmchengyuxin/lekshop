package com.chengyu.core.mapper;

import com.chengyu.core.model.SysQiniuConfig;
import com.chengyu.core.model.SysQiniuConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysQiniuConfigMapper {
    long countByExample(SysQiniuConfigExample example);

    int deleteByExample(SysQiniuConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysQiniuConfig record);

    int insertSelective(SysQiniuConfig record);

    List<SysQiniuConfig> selectByExample(SysQiniuConfigExample example);

    SysQiniuConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysQiniuConfig record, @Param("example") SysQiniuConfigExample example);

    int updateByExample(@Param("record") SysQiniuConfig record, @Param("example") SysQiniuConfigExample example);

    int updateByPrimaryKeySelective(SysQiniuConfig record);

    int updateByPrimaryKey(SysQiniuConfig record);
}