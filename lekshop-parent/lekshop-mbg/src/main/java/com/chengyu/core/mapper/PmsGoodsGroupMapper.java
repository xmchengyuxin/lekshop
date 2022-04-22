package com.chengyu.core.mapper;

import com.chengyu.core.model.PmsGoodsGroup;
import com.chengyu.core.model.PmsGoodsGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGoodsGroupMapper {
    long countByExample(PmsGoodsGroupExample example);

    int deleteByExample(PmsGoodsGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmsGoodsGroup record);

    int insertSelective(PmsGoodsGroup record);

    List<PmsGoodsGroup> selectByExample(PmsGoodsGroupExample example);

    PmsGoodsGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmsGoodsGroup record, @Param("example") PmsGoodsGroupExample example);

    int updateByExample(@Param("record") PmsGoodsGroup record, @Param("example") PmsGoodsGroupExample example);

    int updateByPrimaryKeySelective(PmsGoodsGroup record);

    int updateByPrimaryKey(PmsGoodsGroup record);
}