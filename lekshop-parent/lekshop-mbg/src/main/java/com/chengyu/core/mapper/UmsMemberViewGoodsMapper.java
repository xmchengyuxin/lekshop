package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberViewGoods;
import com.chengyu.core.model.UmsMemberViewGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberViewGoodsMapper {
    long countByExample(UmsMemberViewGoodsExample example);

    int deleteByExample(UmsMemberViewGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberViewGoods record);

    int insertSelective(UmsMemberViewGoods record);

    List<UmsMemberViewGoods> selectByExample(UmsMemberViewGoodsExample example);

    UmsMemberViewGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberViewGoods record, @Param("example") UmsMemberViewGoodsExample example);

    int updateByExample(@Param("record") UmsMemberViewGoods record, @Param("example") UmsMemberViewGoodsExample example);

    int updateByPrimaryKeySelective(UmsMemberViewGoods record);

    int updateByPrimaryKey(UmsMemberViewGoods record);
}