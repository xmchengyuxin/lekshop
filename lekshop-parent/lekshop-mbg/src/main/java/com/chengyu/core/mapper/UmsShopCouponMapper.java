package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsShopCoupon;
import com.chengyu.core.model.UmsShopCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsShopCouponMapper {
    long countByExample(UmsShopCouponExample example);

    int deleteByExample(UmsShopCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmsShopCoupon record);

    int insertSelective(UmsShopCoupon record);

    List<UmsShopCoupon> selectByExample(UmsShopCouponExample example);

    UmsShopCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmsShopCoupon record, @Param("example") UmsShopCouponExample example);

    int updateByExample(@Param("record") UmsShopCoupon record, @Param("example") UmsShopCouponExample example);

    int updateByPrimaryKeySelective(UmsShopCoupon record);

    int updateByPrimaryKey(UmsShopCoupon record);
}