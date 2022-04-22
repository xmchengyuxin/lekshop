package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberCollectionShop;
import com.chengyu.core.model.UmsMemberCollectionShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberCollectionShopMapper {
    long countByExample(UmsMemberCollectionShopExample example);

    int deleteByExample(UmsMemberCollectionShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsMemberCollectionShop record);

    int insertSelective(UmsMemberCollectionShop record);

    List<UmsMemberCollectionShop> selectByExample(UmsMemberCollectionShopExample example);

    UmsMemberCollectionShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsMemberCollectionShop record, @Param("example") UmsMemberCollectionShopExample example);

    int updateByExample(@Param("record") UmsMemberCollectionShop record, @Param("example") UmsMemberCollectionShopExample example);

    int updateByPrimaryKeySelective(UmsMemberCollectionShop record);

    int updateByPrimaryKey(UmsMemberCollectionShop record);
}