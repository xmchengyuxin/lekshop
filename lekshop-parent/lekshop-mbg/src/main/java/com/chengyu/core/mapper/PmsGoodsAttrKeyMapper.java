package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsAttrKey;
import com.chengyu.core.model.PmsGoodsAttrKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsAttrKeyMapper {
    long countByExample(PmsGoodsAttrKeyExample example);

    int deleteByExample(PmsGoodsAttrKeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsAttrKey record);

    int insertSelective(PmsGoodsAttrKey record);

    List<PmsGoodsAttrKey> selectByExample(PmsGoodsAttrKeyExample example);

    PmsGoodsAttrKey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsAttrKey record, @Param("example") PmsGoodsAttrKeyExample example);

    int updateByExample(@Param("record") PmsGoodsAttrKey record, @Param("example") PmsGoodsAttrKeyExample example);

    int updateByPrimaryKeySelective(PmsGoodsAttrKey record);

    int updateByPrimaryKey(PmsGoodsAttrKey record);
}