package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsArticleCate;
import com.chengyu.core.model.CmsArticleCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsArticleCateMapper {
    long countByExample(CmsArticleCateExample example);

    int deleteByExample(CmsArticleCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsArticleCate record);

    int insertSelective(CmsArticleCate record);

    List<CmsArticleCate> selectByExample(CmsArticleCateExample example);

    CmsArticleCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsArticleCate record, @Param("example") CmsArticleCateExample example);

    int updateByExample(@Param("record") CmsArticleCate record, @Param("example") CmsArticleCateExample example);

    int updateByPrimaryKeySelective(CmsArticleCate record);

    int updateByPrimaryKey(CmsArticleCate record);
}