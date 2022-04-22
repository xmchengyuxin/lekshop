package com.chengyu.core.mapper;

import com.chengyu.core.model.SysWeixinTemplate;
import com.chengyu.core.model.SysWeixinTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysWeixinTemplateMapper {
    long countByExample(SysWeixinTemplateExample example);

    int deleteByExample(SysWeixinTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysWeixinTemplate record);

    int insertSelective(SysWeixinTemplate record);

    List<SysWeixinTemplate> selectByExample(SysWeixinTemplateExample example);

    SysWeixinTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysWeixinTemplate record, @Param("example") SysWeixinTemplateExample example);

    int updateByExample(@Param("record") SysWeixinTemplate record, @Param("example") SysWeixinTemplateExample example);

    int updateByPrimaryKeySelective(SysWeixinTemplate record);

    int updateByPrimaryKey(SysWeixinTemplate record);
}