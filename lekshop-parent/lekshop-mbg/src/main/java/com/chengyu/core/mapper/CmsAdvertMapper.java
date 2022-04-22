package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsAdvert;
import com.chengyu.core.model.CmsAdvertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsAdvertMapper {
    long countByExample(CmsAdvertExample example);

    int deleteByExample(CmsAdvertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsAdvert record);

    int insertSelective(CmsAdvert record);

    List<CmsAdvert> selectByExample(CmsAdvertExample example);

    CmsAdvert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsAdvert record, @Param("example") CmsAdvertExample example);

    int updateByExample(@Param("record") CmsAdvert record, @Param("example") CmsAdvertExample example);

    int updateByPrimaryKeySelective(CmsAdvert record);

    int updateByPrimaryKey(CmsAdvert record);
}