package com.chengyu.core.service.shop;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopCoupon;

import java.math.BigDecimal;
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
	List<UmsShopCoupon> getShopCouponList(Integer shopId, String name, Integer type, Integer sendCondition, Integer page, Integer pageSize);

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

	/**
	 * 发放优惠券
	 * @author LeGreen
	 * @date   2022/9/25
	 * @param  coupon 优惠券配置
	 * @param  memberIdList 发放用户Id
	 * @throws ServiceException 业务异常
	 */
	void presentCoupon(UmsShop shop, UmsShopCoupon coupon, List<Integer> memberIdList) throws ServiceException;

	/**
	 * 消费满多少赠送优惠券
	 * @author LeGreen
	 * @date   2022/10/10
	 * @param  member 消费用户
	 * @param  payPrice 支付金额
	 */
	void sendCouponByConsumption(UmsMember member, Integer shopId, BigDecimal payPrice);
}