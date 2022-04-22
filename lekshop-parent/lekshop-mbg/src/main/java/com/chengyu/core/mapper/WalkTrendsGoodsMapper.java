package com.chengyu.core.mapper;

import com.chengyu.core.model.WalkTrendsGoods;
import com.chengyu.core.model.WalkTrendsGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WalkTrendsGoodsMapper {
    long countByExample(WalkTrendsGoodsExample example);

    int deleteByExample(WalkTrendsGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WalkTrendsGoods record);

    int insertSelective(WalkTrendsGoods record);

    List<WalkTrendsGoods> selectByExample(WalkTrendsGoodsExample example);

    WalkTrendsGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WalkTrendsGoods record, @Param("example") WalkTrendsGoodsExample example);

    int updateByExample(@Param("record") WalkTrendsGoods record, @Param("example") WalkTrendsGoodsExample example);

    int updateByPrimaryKeySelective(WalkTrendsGoods record);

    int updateByPrimaryKey(WalkTrendsGoods record);
}