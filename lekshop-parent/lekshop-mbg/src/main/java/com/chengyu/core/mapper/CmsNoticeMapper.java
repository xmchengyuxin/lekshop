package com.chengyu.core.mapper;

import com.chengyu.core.model.CmsNotice;
import com.chengyu.core.model.CmsNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsNoticeMapper {
    long countByExample(CmsNoticeExample example);

    int deleteByExample(CmsNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsNotice record);

    int insertSelective(CmsNotice record);

    List<CmsNotice> selectByExampleWithBLOBs(CmsNoticeExample example);

    List<CmsNotice> selectByExample(CmsNoticeExample example);

    CmsNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsNotice record, @Param("example") CmsNoticeExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsNotice record, @Param("example") CmsNoticeExample example);

    int updateByExample(@Param("record") CmsNotice record, @Param("example") CmsNoticeExample example);

    int updateByPrimaryKeySelective(CmsNotice record);

    int updateByPrimaryKeyWithBLOBs(CmsNotice record);

    int updateByPrimaryKey(CmsNotice record);
}