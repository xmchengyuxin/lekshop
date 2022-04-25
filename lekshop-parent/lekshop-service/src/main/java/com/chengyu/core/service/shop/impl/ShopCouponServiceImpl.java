package com.chengyu.core.service.shop.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.ShopEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomShopCouponMapper;
import com.chengyu.core.mapper.UmsMemberCouponMapper;
import com.chengyu.core.mapper.UmsShopCouponMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.shop.ShopCouponService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ShopCouponServiceImpl implements ShopCouponService {

	@Autowired
	private UmsShopCouponMapper shopCouponMapper;
	@Autowired
	private CustomShopCouponMapper customShopCouponMapper;
	@Autowired
	private UmsMemberCouponMapper memberCouponMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addCoupon(UmsShop shop, UmsShopCoupon coupon) throws ServiceException {
		if(coupon.getType() == ShopEnums.CouponType.DISCOUNT_COUPON.getValue()){
			//折扣券校验
			if(coupon.getAmount() == null || coupon.getAmount().compareTo(new BigDecimal(10)) > 0){
				throw new ServiceException("折扣券设置不能大于10折");
			}
		}
		if(coupon.getUseType() == ShopEnums.CouponUseType.SOME_GOODS.getValue()){
			//指定商品
			if(StringUtils.isBlank(coupon.getUseGoodsIds())){
				throw new ServiceException("请选择指定商品");
			}
		}else if(coupon.getUseType() == ShopEnums.CouponUseType.SOME_CATE.getValue()){
			//指定分类
			if(StringUtils.isBlank(coupon.getUseGoodsCateIds())){
				throw new ServiceException("请选择指定商品分类");
			}
		}
		if(coupon.getValidityType() == ShopEnums.CouponValidityType.LIMIT_DAYS.getValue()){
			if(coupon.getValidityDays() == null){
				throw new ServiceException("有效天数不能为空");
			}
		}else if(coupon.getValidityType() == ShopEnums.CouponValidityType.FIXED_TIME.getValue()){
			if(coupon.getFixedBeginDate() == null || coupon.getFixedEndDate() == null){
				throw new ServiceException("固定时间不能为空");
			}
		}
		if(coupon.getEndDate().before(coupon.getBeginDate())) {
			throw new ServiceException("活动开始时间不能大于结束时间");
		}
		if(coupon.getEndDate().before(DateUtil.date())) {
			throw new ServiceException("活动结束时间不能小于当前时间");
		}

		coupon.setShopId(shop.getId());
		coupon.setShopName(shop.getName());
		coupon.setDrawNum(0);
		coupon.setStatus(ShopEnums.CouponStatus.SENDING.getValue());
		coupon.setAddTime(DateUtil.date());
		coupon.setUpdTime(coupon.getAddTime());
		if(coupon.getId() == null){
			shopCouponMapper.insertSelective(coupon);
		}else{
			shopCouponMapper.updateByPrimaryKeySelective(coupon);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCoupon(Integer couponId) {
		shopCouponMapper.deleteByPrimaryKey(couponId);
	}

	@Override
	public List<UmsShopCoupon> getShopCouponList(Integer shopId, String name, Integer type, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsShopCouponExample example = new UmsShopCouponExample();
		example.setOrderByClause("id desc");
		UmsShopCouponExample.Criteria criteria = example.createCriteria();
		if(shopId != null){
			criteria.andShopIdEqualTo(shopId);
		}
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike("%"+name+"%");
		}
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		return shopCouponMapper.selectByExample(example);
	}

	@Override
	public List<UmsShopCoupon> getAllShopCoupons(Integer shopId, Integer goodsId, Integer goodsCateId) {
		return customShopCouponMapper.getAllShopCoupons(shopId, goodsId, goodsCateId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void drawCoupon(UmsMember member, Integer shopCouponId) throws ServiceException {
		UmsShopCoupon coupon = shopCouponMapper.selectByPrimaryKey(shopCouponId);
		if(coupon == null || coupon.getStatus() != ShopEnums.CouponStatus.SENDING.getValue()){
			throw new ServiceException("优惠券已被抢光了");
		}
		if(coupon.getDrawNum() >= coupon.getTotalNum()){
			throw new ServiceException("优惠券已被抢光了");
		}
		Date now = DateUtil.date();
		if(coupon.getBeginDate().after(now)){
			throw new ServiceException("优惠券即将抢购");
		}
		if(coupon.getEndDate().before(now)){
			throw new ServiceException("优惠券已过期");
		}
		if(coupon.getValidityType() == ShopEnums.CouponValidityType.FIXED_TIME.getValue()){
			if(coupon.getFixedEndDate().before(now)){
				throw new ServiceException("优惠券已过期");
			}
		}
		//查询该优惠券是否可重复领
		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andCouponConfigIdEqualTo(shopCouponId);
		long count = memberCouponMapper.countByExample(example);
		if(count >= coupon.getLimitNum()){
			throw new ServiceException("您已领取过");
		}

		UmsMemberCoupon memberCoupon = new UmsMemberCoupon();
		memberCoupon.setMemberId(member.getId());
		memberCoupon.setMemberName(member.getNickname());
		memberCoupon.setShopId(coupon.getShopId());
		memberCoupon.setShopName(coupon.getShopName());
		memberCoupon.setCouponConfigId(coupon.getId());
		memberCoupon.setName(coupon.getName());
		memberCoupon.setType(coupon.getType());
		memberCoupon.setUseType(coupon.getUseType());
		memberCoupon.setUseGoodsIds(coupon.getUseGoodsIds());
		memberCoupon.setUseGoodsCateIds(coupon.getUseGoodsCateIds());
		memberCoupon.setAmount(coupon.getAmount());
		memberCoupon.setFullAmount(coupon.getFullAmount());
		memberCoupon.setStatus(coupon.getStatus());
		if(coupon.getValidityType() == ShopEnums.CouponValidityType.LIMIT_DAYS.getValue()){
			memberCoupon.setValidityBeginTime(now);
			memberCoupon.setValidityEndTime(DateUtil.offsetDay(now, coupon.getValidityDays()));
		}else if(coupon.getValidityType() == ShopEnums.CouponValidityType.FIXED_TIME.getValue()){
			memberCoupon.setValidityBeginTime(coupon.getFixedBeginDate());
			memberCoupon.setValidityEndTime(coupon.getFixedEndDate());
		}
		memberCoupon.setRules(coupon.getRules());
		memberCoupon.setAddTime(now);
		memberCoupon.setUpdTime(now);
		memberCouponMapper.insert(memberCoupon);
	}
}