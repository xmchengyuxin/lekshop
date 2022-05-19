package com.chengyu.core.service.member.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ShopEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberCouponMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberCoupon;
import com.chengyu.core.model.UmsMemberCouponExample;
import com.chengyu.core.service.member.MemberCouponService;
import com.chengyu.core.utils.NumberUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title  用户优惠券
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class MemberCouponServiceImpl implements MemberCouponService {
	
	@Autowired
	private UmsMemberCouponMapper memberCouponMapper;

	@Override
	public List<UmsMemberCoupon> getMemberCouponList(Integer memberId, Integer status, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.setOrderByClause("add_time desc");
		UmsMemberCouponExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(status != null){
			criteria.andStatusEqualTo(status);
		}
		return memberCouponMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public BigDecimal validateCoupon(UmsMember member, Integer couponId, BigDecimal totalAmount, List<Integer> goodsIdList, List<Integer> goodsCateIdList) throws ServiceException {
		if(couponId == null){
			return BigDecimal.ZERO;
		}
		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andIdEqualTo(couponId);
		List<UmsMemberCoupon> list = memberCouponMapper.selectByExample(example);
		UmsMemberCoupon coupon = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
		if(coupon == null || coupon.getStatus() != CommonConstant.NO_INT){
			throw new ServiceException("优惠券不可用");
		}
		if(coupon.getFullAmount().compareTo(totalAmount) > 0){
			throw new ServiceException("满"+ NumberUtils.format2(coupon.getFullAmount())+"元才能使用");
		}
		Date now = DateUtil.date();
		if(coupon.getValidityBeginTime() != null && coupon.getValidityBeginTime().after(now)){
			throw new ServiceException("未到优惠券使用时间");
		}
		if(coupon.getValidityEndTime() != null && coupon.getValidityEndTime().before(now)){
			throw new ServiceException("优惠券已过期");
		}
		if(coupon.getUseType() == ShopEnums.CouponUseType.SOME_GOODS.getValue()){
			//指定商品
			for(Integer goodsId : goodsIdList){
				if(!coupon.getUseGoodsIds().contains(","+goodsId.toString()+",")){
					throw new ServiceException("该优惠券仅限指定商品使用");
				}
			}
		}else if(coupon.getUseType() == ShopEnums.CouponUseType.SOME_CATE.getValue()){
			//指定商品分类
			for(Integer goodsCateId : goodsCateIdList){
				if(!coupon.getUseGoodsCateIds().contains(","+goodsCateId.toString()+",")){
					throw new ServiceException("该优惠券仅限指定分类使用");
				}
			}
		}
		return coupon.getType() == ShopEnums.CouponType.FULL_REDUCE_COUPON.getValue() ? coupon.getAmount() : NumberUtil.sub(totalAmount, NumberUtil.mul(totalAmount, NumberUtil.div(coupon.getAmount(), 10)));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void useCoupon(Integer couponId) throws ServiceException {
		UmsMemberCoupon coupon = memberCouponMapper.selectByPrimaryKey(couponId);
		if(coupon == null || coupon.getStatus() != CommonConstant.WAIT_INT){
			throw new ServiceException("该张优惠券无法使用");
		}

		//优惠券已使用
		UmsMemberCoupon updateCoupon = new UmsMemberCoupon();
		updateCoupon.setId(couponId);
		updateCoupon.setStatus(CommonConstant.YES_INT);
		updateCoupon.setUpdTime(DateUtil.date());
		memberCouponMapper.updateByPrimaryKeySelective(updateCoupon);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void backCoupon(UmsMember member, Integer couponId) {
		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andIdEqualTo(couponId);
		List<UmsMemberCoupon> list = memberCouponMapper.selectByExample(example);
		UmsMemberCoupon coupon = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
		if(coupon != null && coupon.getStatus() == CommonConstant.YES_INT){
			//优惠券返还
			UmsMemberCoupon updateCoupon = new UmsMemberCoupon();
			updateCoupon.setId(couponId);
			updateCoupon.setStatus(CommonConstant.NO_INT);
			updateCoupon.setUpdTime(DateUtil.date());
			memberCouponMapper.updateByPrimaryKeySelective(updateCoupon);
		}
	}

	@Override
	public Long countUnUseCoupon(Integer memberId) {
		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(CommonConstant.NO_INT);
		return memberCouponMapper.countByExample(example);
	}

	@Override
	public List<Map<String,Object>> getCanUseCouponList(UmsMember member, Integer shopId, String goodsIds, String cateIds, BigDecimal totalAmount) {
		UmsMemberCouponExample example = new UmsMemberCouponExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andShopIdEqualTo(shopId).andStatusEqualTo(CommonConstant.NO_INT);
		List<UmsMemberCoupon> list = memberCouponMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		List<Map<String,Object>> canUseList = new ArrayList<>();
		List<Map<String,Object>> cannotUseList = new ArrayList<>();
		for(UmsMemberCoupon coupon : list){
			Map<String,Object> couponMap = BeanUtil.beanToMap(coupon);
			try {
				List<Integer> goodsIdList = Convert.convert(new TypeReference<List<Integer>>() {}, goodsIds.split(CommonConstant.DH_REGEX));
				List<Integer> cateIdList = Convert.convert(new TypeReference<List<Integer>>() {}, cateIds.split(CommonConstant.DH_REGEX));
				BigDecimal discountAmount = this.validateCoupon(member, coupon.getId(), totalAmount, goodsIdList, cateIdList);
				couponMap.put("discountAmount", discountAmount);
				canUseList.add(couponMap);
			}catch (ServiceException e){
				couponMap.put("reason", e.getMessage());
				cannotUseList.add(couponMap);
			}
		}
		canUseList.addAll(cannotUseList);
		return canUseList;
	}
}