package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkTrends;
import com.chengyu.core.model.WalkTrendsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkTrendsMapper {
    long countByExample(WalkTrendsExample example);

    int deleteByExample(WalkTrendsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkTrends record);

    int insertSelective(WalkTrends record);

    List<WalkTrends> selectByExample(WalkTrendsExample example);

    WalkTrends selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkTrends record, @Param("example") WalkTrendsExample example);

    int updateByExample(@Param("record") WalkTrends record, @Param("example") WalkTrendsExample example);

    int updateByPrimaryKeySelective(WalkTrends record);

    int updateByPrimaryKey(WalkTrends record);
}