package com.chengyu.core.service.shop.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ShopEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomShopCouponMapper;
import com.chengyu.core.mapper.UmsMemberCouponMapper;
import com.chengyu.core.mapper.UmsMemberMapper;
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
import java.util.ArrayList;
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
	@Autowired
	private UmsMemberMapper memberMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addCoupon(UmsShop shop, UmsShopCoupon coupon) throws ServiceException {
		this.validateCoupon(coupon);

		coupon.setShopId(shop.getId());
		coupon.setShopName(shop.getName());
		coupon.setDrawNum(0);
		coupon.setStatus(ShopEnums.CouponStatus.SENDING.getValue());
		coupon.setAddTime(DateUtil.date());
		coupon.setUpdTime(coupon.getAddTime());
		if(coupon.getId() == null){
			shopCouponMapper.insertSelective(coupon);
		}else{
			UmsShopCoupon shopCoupon = shopCouponMapper.selectByPrimaryKey(coupon.getId());
			if(shopCoupon.getStatus() != ShopEnums.CouponStatus.SENDING.getValue()) {
				throw new ServiceException("发放中的优惠券才能进行修改");
			}
			shopCouponMapper.updateByPrimaryKeySelective(coupon);
		}
	}

	private void validateCoupon(UmsShopCoupon coupon) throws ServiceException {
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
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteCoupon(Integer couponId) {
		shopCouponMapper.deleteByPrimaryKey(couponId);
	}

	@Override
	public List<UmsShopCoupon> getShopCouponList(Integer shopId, String name, Integer type,  Integer sendCondition, Integer page, Integer pageSize) {
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
		if(sendCondition != null) {
			criteria.andSendConditionEqualTo(sendCondition);
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
			throw new ServiceException("member.coupon.empty");
		}
		if(coupon.getDrawNum() >= coupon.getTotalNum()){
			throw new ServiceException("member.coupon.empty");
		}
		Date now = DateUtil.date();
		if(coupon.getBeginDate().after(now)){
			throw new ServiceException("member.coupon.nostart");
		}
		if(coupon.getEndDate().before(now)){
			throw new ServiceException("member.coupon.expired");
		}
		if(coupon.getValidityType() == ShopEnums.CouponValidityType.FIXED_TIME.getValue()){
			if(coupon.getFixedEndDate().before(now)){
				throw new ServiceException("member.coupon.expired");
			}
		}
		//查询该优惠券是否可重复领
		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andCouponConfigIdEqualTo(shopCouponId);
		long count = memberCouponMapper.countByExample(example);
		if(count >= coupon.getLimitNum()){
			throw new ServiceException("member.coupon.drawed");
		}

		UmsMemberCoupon memberCoupon = this.genMemberCoupon(member, coupon);
		memberCouponMapper.insertSelective(memberCoupon);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void presentCoupon(UmsShop shop, UmsShopCoupon coupon, List<Integer> memberIdList) throws ServiceException {
		if(CollectionUtil.isEmpty(memberIdList)) {
			throw new ServiceException("请选择发放客户人群");
		}
		this.validateCoupon(coupon);

		coupon.setShopId(shop.getId());
		coupon.setShopName(shop.getName());
		coupon.setTotalNum(memberIdList.size());
		coupon.setLimitNum(1);
		coupon.setDrawNum(memberIdList.size());
		coupon.setStatus(ShopEnums.CouponStatus.END.getValue());
		coupon.setSendCondition(2);
		coupon.setAddTime(DateUtil.date());
		coupon.setUpdTime(coupon.getAddTime());
		shopCouponMapper.insertSelective(coupon);

		UmsMemberExample example = new UmsMemberExample();
		example.createCriteria().andIdIn(memberIdList).andStatusEqualTo(CommonConstant.SUS_INT);
		List<UmsMember> memberList = memberMapper.selectByExample(example);
		List<UmsMemberCoupon> couponList = new ArrayList<>();
		for(UmsMember member : memberList){
			UmsMemberCoupon memberCoupon = this.genMemberCoupon(member, coupon);
			couponList.add(memberCoupon);
		}
		customShopCouponMapper.insertMemberCouponList(couponList);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void sendCouponByConsumption(UmsMember member, Integer shopId, BigDecimal payPrice) {
		//查询所有的满送优惠券
		UmsShopCouponExample example = new UmsShopCouponExample();
		example.createCriteria().andShopIdEqualTo(shopId).andSendConditionEqualTo(3)
				.andStatusEqualTo(CommonConstant.YES_INT).andBeginDateLessThanOrEqualTo(DateUtil.date())
				.andEndDateGreaterThanOrEqualTo(DateUtil.date()).andFullSendAmountLessThanOrEqualTo(payPrice);
		List<UmsShopCoupon> couponList = shopCouponMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(couponList)) {
			return;
		}
		for(UmsShopCoupon coupon : couponList) {
			try {
				this.drawCoupon(member, coupon.getId());
			} catch (Exception ignored){
			}
		}

	}

	private UmsMemberCoupon genMemberCoupon(UmsMember member, UmsShopCoupon coupon) {
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
		memberCoupon.setStatus(CommonConstant.NO_INT);
		if(coupon.getValidityType() == ShopEnums.CouponValidityType.LIMIT_DAYS.getValue()){
			memberCoupon.setValidityBeginTime(DateUtil.date());
			memberCoupon.setValidityEndTime(DateUtil.offsetDay(memberCoupon.getValidityBeginTime(), coupon.getValidityDays()));
		}else if(coupon.getValidityType() == ShopEnums.CouponValidityType.FIXED_TIME.getValue()){
			memberCoupon.setValidityBeginTime(coupon.getFixedBeginDate());
			memberCoupon.setValidityEndTime(coupon.getFixedEndDate());
		}
		memberCoupon.setRules(coupon.getRules());
		memberCoupon.setAddTime(DateUtil.date());
		memberCoupon.setUpdTime(DateUtil.date());
		return memberCoupon;
	}
}