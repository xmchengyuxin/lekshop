package com.chengyu.core.mapper;

import com.chengyu.core.model.PointConfig;
import com.chengyu.core.model.PointConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointConfigMapper {
    long countByExample(PointConfigExample example);

    int deleteByExample(PointConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointConfig record);

    int insertSelective(PointConfig record);

    List<PointConfig> selectByExample(PointConfigExample example);

    PointConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointConfig record, @Param("example") PointConfigExample example);

    int updateByExample(@Param("record") PointConfig record, @Param("example") PointConfigExample example);

    int updateByPrimaryKeySelective(PointConfig record);

    int updateByPrimaryKey(PointConfig record);
}