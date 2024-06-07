package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.model.UmsShopAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopAccountMapper {
    long countByExample(UmsShopAccountExample example);

    int deleteByExample(UmsShopAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopAccount record);

    int insertSelective(UmsShopAccount record);

    List<UmsShopAccount> selectByExample(UmsShopAccountExample example);

    UmsShopAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopAccount record, @Param("example") UmsShopAccountExample example);

    int updateByExample(@Param("record") UmsShopAccount record, @Param("example") UmsShopAccountExample example);

    int updateByPrimaryKeySelective(UmsShopAccount record);

    int updateByPrimaryKey(UmsShopAccount record);
}