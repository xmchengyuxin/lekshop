package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsSku;
import com.chengyu.core.model.PmsGoodsSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsSkuMapper {
    long countByExample(PmsGoodsSkuExample example);

    int deleteByExample(PmsGoodsSkuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsSku record);

    int insertSelective(PmsGoodsSku record);

    List<PmsGoodsSku> selectByExample(PmsGoodsSkuExample example);

    PmsGoodsSku selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsSku record, @Param("example") PmsGoodsSkuExample example);

    int updateByExample(@Param("record") PmsGoodsSku record, @Param("example") PmsGoodsSkuExample example);

    int updateByPrimaryKeySelective(PmsGoodsSku record);

    int updateByPrimaryKey(PmsGoodsSku record);
}