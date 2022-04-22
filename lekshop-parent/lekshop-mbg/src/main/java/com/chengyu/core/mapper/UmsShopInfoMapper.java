package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopInfo;
import com.chengyu.core.model.UmsShopInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopInfoMapper {
    long countByExample(UmsShopInfoExample example);

    int deleteByExample(UmsShopInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopInfo record);

    int insertSelective(UmsShopInfo record);

    List<UmsShopInfo> selectByExample(UmsShopInfoExample example);

    UmsShopInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopInfo record, @Param("example") UmsShopInfoExample example);

    int updateByExample(@Param("record") UmsShopInfo record, @Param("example") UmsShopInfoExample example);

    int updateByPrimaryKeySelective(UmsShopInfo record);

    int updateByPrimaryKey(UmsShopInfo record);
}