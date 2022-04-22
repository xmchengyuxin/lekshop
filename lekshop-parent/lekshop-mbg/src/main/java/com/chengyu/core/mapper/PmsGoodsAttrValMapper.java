package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsAttrVal;
import com.chengyu.core.model.PmsGoodsAttrValExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsAttrValMapper {
    long countByExample(PmsGoodsAttrValExample example);

    int deleteByExample(PmsGoodsAttrValExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsAttrVal record);

    int insertSelective(PmsGoodsAttrVal record);

    List<PmsGoodsAttrVal> selectByExample(PmsGoodsAttrValExample example);

    PmsGoodsAttrVal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsAttrVal record, @Param("example") PmsGoodsAttrValExample example);

    int updateByExample(@Param("record") PmsGoodsAttrVal record, @Param("example") PmsGoodsAttrValExample example);

    int updateByPrimaryKeySelective(PmsGoodsAttrVal record);

    int updateByPrimaryKey(PmsGoodsAttrVal record);
}