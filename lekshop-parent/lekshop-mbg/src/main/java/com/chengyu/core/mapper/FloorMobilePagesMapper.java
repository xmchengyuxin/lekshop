package com.chengyu.core.mapper;

import com.chengyu.core.model.FloorMobilePages;
import com.chengyu.core.model.FloorMobilePagesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FloorMobilePagesMapper {
    long countByExample(FloorMobilePagesExample example);

    int deleteByExample(FloorMobilePagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FloorMobilePages record);

    int insertSelective(FloorMobilePages record);

    List<FloorMobilePages> selectByExampleWithBLOBs(FloorMobilePagesExample example);

    List<FloorMobilePages> selectByExample(FloorMobilePagesExample example);

    FloorMobilePages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FloorMobilePages record, @Param("example") FloorMobilePagesExample example);

    int updateByExampleWithBLOBs(@Param("record") FloorMobilePages record, @Param("example") FloorMobilePagesExample example);

    int updateByExample(@Param("record") FloorMobilePages record, @Param("example") FloorMobilePagesExample example);

    int updateByPrimaryKeySelective(FloorMobilePages record);

    int updateByPrimaryKeyWithBLOBs(FloorMobilePages record);

    int updateByPrimaryKey(FloorMobilePages record);
}