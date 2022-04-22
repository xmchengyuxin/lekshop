package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopMapper {
    long countByExample(UmsShopExample example);

    int deleteByExample(UmsShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShop record);

    int insertSelective(UmsShop record);

    List<UmsShop> selectByExample(UmsShopExample example);

    UmsShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShop record, @Param("example") UmsShopExample example);

    int updateByExample(@Param("record") UmsShop record, @Param("example") UmsShopExample example);

    int updateByPrimaryKeySelective(UmsShop record);

    int updateByPrimaryKey(UmsShop record);
}