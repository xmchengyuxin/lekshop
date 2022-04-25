package com.chengyu.core.service.shop;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopCoupon;

import java.util.List;

/**
 * @title  店铺发放优惠券
 * @author LeGreen
 * @date   2022/4/25
 */
public interface ShopCouponService {

	/**
	 * 发放优惠券
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shop
	 * @param  coupon
	 * @throws ServiceException 业务异常
	 */
	void addCoupon(UmsShop shop, UmsShopCoupon coupon) throws ServiceException;

	/**
	 * 删除优惠券
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  couponId
	 */
	void deleteCoupon(Integer couponId);

	/**
	 * 优惠券列表
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shopId
	 * @param  name
	 * @param  type
	 * @param  page
	 * @param  pageSize
	 * @return List<UmsShopCoupon>
	 */
	List<UmsShopCoupon> getShopCouponList(Integer shopId, String name, Integer type, Integer page, Integer pageSize);

	/**
	 * 获取当前条件下可用优惠券列表
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shopId
	 * @param  goodsId
	 * @param  goodsCateId
	 * @return List<UmsShopCoupon>
	 */
	List<UmsShopCoupon> getAllShopCoupons(Integer shopId, Integer goodsId, Integer goodsCateId);

	/**
	 * 领取优惠券
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  member
	 * @param  shopCouponId
	 * @throws ServiceException 业务异常
	 */
	void drawCoupon(UmsMember member, Integer shopCouponId) throws ServiceException;

}