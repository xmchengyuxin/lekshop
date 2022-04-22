package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopSourceCate;
import com.chengyu.core.model.UmsShopSourceCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopSourceCateMapper {
    long countByExample(UmsShopSourceCateExample example);

    int deleteByExample(UmsShopSourceCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopSourceCate record);

    int insertSelective(UmsShopSourceCate record);

    List<UmsShopSourceCate> selectByExample(UmsShopSourceCateExample example);

    UmsShopSourceCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopSourceCate record, @Param("example") UmsShopSourceCateExample example);

    int updateByExample(@Param("record") UmsShopSourceCate record, @Param("example") UmsShopSourceCateExample example);

    int updateByPrimaryKeySelective(UmsShopSourceCate record);

    int updateByPrimaryKey(UmsShopSourceCate record);
}