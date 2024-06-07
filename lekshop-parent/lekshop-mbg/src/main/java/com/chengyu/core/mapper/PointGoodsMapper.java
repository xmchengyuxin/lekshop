package com.chengyu.core.mapper;

import com.chengyu.core.model.PointGoods;
import com.chengyu.core.model.PointGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointGoodsMapper {
    long countByExample(PointGoodsExample example);

    int deleteByExample(PointGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PointGoods record);

    int insertSelective(PointGoods record);

    List<PointGoods> selectByExampleWithBLOBs(PointGoodsExample example);

    List<PointGoods> selectByExample(PointGoodsExample example);

    PointGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PointGoods record, @Param("example") PointGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") PointGoods record, @Param("example") PointGoodsExample example);

    int updateByExample(@Param("record") PointGoods record, @Param("example") PointGoodsExample example);

    int updateByPrimaryKeySelective(PointGoods record);

    int updateByPrimaryKeyWithBLOBs(PointGoods record);

    int updateByPrimaryKey(PointGoods record);
}