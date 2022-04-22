package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopCate;
import com.chengyu.core.model.UmsShopCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopCateMapper {
    long countByExample(UmsShopCateExample example);

    int deleteByExample(UmsShopCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopCate record);

    int insertSelective(UmsShopCate record);

    List<UmsShopCate> selectByExample(UmsShopCateExample example);

    UmsShopCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopCate record, @Param("example") UmsShopCateExample example);

    int updateByExample(@Param("record") UmsShopCate record, @Param("example") UmsShopCateExample example);

    int updateByPrimaryKeySelective(UmsShopCate record);

    int updateByPrimaryKey(UmsShopCate record);
}