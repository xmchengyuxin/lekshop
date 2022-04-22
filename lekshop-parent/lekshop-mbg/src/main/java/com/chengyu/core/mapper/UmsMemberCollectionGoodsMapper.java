package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberCollectionGoods;
import com.chengyu.core.model.UmsMemberCollectionGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberCollectionGoodsMapper {
    long countByExample(UmsMemberCollectionGoodsExample example);

    int deleteByExample(UmsMemberCollectionGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberCollectionGoods record);

    int insertSelective(UmsMemberCollectionGoods record);

    List<UmsMemberCollectionGoods> selectByExample(UmsMemberCollectionGoodsExample example);

    UmsMemberCollectionGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberCollectionGoods record, @Param("example") UmsMemberCollectionGoodsExample example);

    int updateByExample(@Param("record") UmsMemberCollectionGoods record, @Param("example") UmsMemberCollectionGoodsExample example);

    int updateByPrimaryKeySelective(UmsMemberCollectionGoods record);

    int updateByPrimaryKey(UmsMemberCollectionGoods record);
}