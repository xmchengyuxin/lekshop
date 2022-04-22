package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsSource;
import com.chengyu.core.model.CmsSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSourceMapper {
    long countByExample(CmsSourceExample example);

    int deleteByExample(CmsSourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsSource record);

    int insertSelective(CmsSource record);

    List<CmsSource> selectByExample(CmsSourceExample example);

    CmsSource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsSource record, @Param("example") CmsSourceExample example);

    int updateByExample(@Param("record") CmsSource record, @Param("example") CmsSourceExample example);

    int updateByPrimaryKeySelective(CmsSource record);

    int updateByPrimaryKey(CmsSource record);
}