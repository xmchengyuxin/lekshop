package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsRelate;
import com.chengyu.core.model.PmsGoodsRelateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsRelateMapper {
    long countByExample(PmsGoodsRelateExample example);

    int deleteByExample(PmsGoodsRelateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsRelate record);

    int insertSelective(PmsGoodsRelate record);

    List<PmsGoodsRelate> selectByExample(PmsGoodsRelateExample example);

    PmsGoodsRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsRelate record, @Param("example") PmsGoodsRelateExample example);

    int updateByExample(@Param("record") PmsGoodsRelate record, @Param("example") PmsGoodsRelateExample example);

    int updateByPrimaryKeySelective(PmsGoodsRelate record);

    int updateByPrimaryKey(PmsGoodsRelate record);
}