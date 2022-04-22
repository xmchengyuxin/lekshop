package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsSourceCate;
import com.chengyu.core.model.CmsSourceCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSourceCateMapper {
    long countByExample(CmsSourceCateExample example);

    int deleteByExample(CmsSourceCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsSourceCate record);

    int insertSelective(CmsSourceCate record);

    List<CmsSourceCate> selectByExample(CmsSourceCateExample example);

    CmsSourceCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsSourceCate record, @Param("example") CmsSourceCateExample example);

    int updateByExample(@Param("record") CmsSourceCate record, @Param("example") CmsSourceCateExample example);

    int updateByPrimaryKeySelective(CmsSourceCate record);

    int updateByPrimaryKey(CmsSourceCate record);
}