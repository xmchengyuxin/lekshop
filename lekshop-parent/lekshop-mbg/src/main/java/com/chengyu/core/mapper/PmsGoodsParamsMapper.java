package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsParams;
import com.chengyu.core.model.PmsGoodsParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsParamsMapper {
    long countByExample(PmsGoodsParamsExample example);

    int deleteByExample(PmsGoodsParamsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsParams record);

    int insertSelective(PmsGoodsParams record);

    List<PmsGoodsParams> selectByExample(PmsGoodsParamsExample example);

    PmsGoodsParams selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsParams record, @Param("example") PmsGoodsParamsExample example);

    int updateByExample(@Param("record") PmsGoodsParams record, @Param("example") PmsGoodsParamsExample example);

    int updateByPrimaryKeySelective(PmsGoodsParams record);

    int updateByPrimaryKey(PmsGoodsParams record);
}