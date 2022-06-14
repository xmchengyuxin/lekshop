package com.chengyu.core.mapper;

import com.chengyu.core.model.FloorMobileTemplate;
import com.chengyu.core.model.FloorMobileTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FloorMobileTemplateMapper {
    long countByExample(FloorMobileTemplateExample example);

    int deleteByExample(FloorMobileTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FloorMobileTemplate record);

    int insertSelective(FloorMobileTemplate record);

    List<FloorMobileTemplate> selectByExampleWithBLOBs(FloorMobileTemplateExample example);

    List<FloorMobileTemplate> selectByExample(FloorMobileTemplateExample example);

    FloorMobileTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FloorMobileTemplate record, @Param("example") FloorMobileTemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") FloorMobileTemplate record, @Param("example") FloorMobileTemplateExample example);

    int updateByExample(@Param("record") FloorMobileTemplate record, @Param("example") FloorMobileTemplateExample example);

    int updateByPrimaryKeySelective(FloorMobileTemplate record);

    int updateByPrimaryKeyWithBLOBs(FloorMobileTemplate record);

    int updateByPrimaryKey(FloorMobileTemplate record);
}