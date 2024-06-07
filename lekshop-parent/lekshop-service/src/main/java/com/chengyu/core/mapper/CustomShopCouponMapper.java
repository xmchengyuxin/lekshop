package com.chengyu.core.mapper;

import com.chengyu.core.model.UmsMemberCoupon;
import com.chengyu.core.model.UmsShopCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomShopCouponMapper {
	
    List<UmsShopCoupon> getAllShopCoupons(@Param("shopId") Integer shopId, @Param("goodsId") Integer goodsId, @Param("goodsCateId") Integer goodsCateId);

    void insertMemberCouponList(@Param("couponList") List<UmsMemberCoupon> couponList);
}