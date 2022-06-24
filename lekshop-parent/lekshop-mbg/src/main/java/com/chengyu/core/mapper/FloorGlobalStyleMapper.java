package com.chengyu.core.mapper;

import com.chengyu.core.model.FloorGlobalStyle;
import com.chengyu.core.model.FloorGlobalStyleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FloorGlobalStyleMapper {
    long countByExample(FloorGlobalStyleExample example);

    int deleteByExample(FloorGlobalStyleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FloorGlobalStyle record);

    int insertSelective(FloorGlobalStyle record);

    List<FloorGlobalStyle> selectByExample(FloorGlobalStyleExample example);

    FloorGlobalStyle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FloorGlobalStyle record, @Param("example") FloorGlobalStyleExample example);

    int updateByExample(@Param("record") FloorGlobalStyle record, @Param("example") FloorGlobalStyleExample example);

    int updateByPrimaryKeySelective(FloorGlobalStyle record);

    int updateByPrimaryKey(FloorGlobalStyle record);
}