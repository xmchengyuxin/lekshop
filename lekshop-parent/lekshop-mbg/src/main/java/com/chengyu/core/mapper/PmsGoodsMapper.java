package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.PmsGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsMapper {
    long countByExample(PmsGoodsExample example);

    int deleteByExample(PmsGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoods record);

    int insertSelective(PmsGoods record);

    List<PmsGoods> selectByExampleWithBLOBs(PmsGoodsExample example);

    List<PmsGoods> selectByExample(PmsGoodsExample example);

    PmsGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoods record, @Param("example") PmsGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") PmsGoods record, @Param("example") PmsGoodsExample example);

    int updateByExample(@Param("record") PmsGoods record, @Param("example") PmsGoodsExample example);

    int updateByPrimaryKeySelective(PmsGoods record);

    int updateByPrimaryKeyWithBLOBs(PmsGoods record);

    int updateByPrimaryKey(PmsGoods record);
}