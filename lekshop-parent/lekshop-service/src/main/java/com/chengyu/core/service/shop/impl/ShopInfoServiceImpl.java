package com.chengyu.core.service.shop.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.ShopEnums;
import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsShopInfoMapper;
import com.chengyu.core.mapper.UmsShopMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopInfo;
import com.chengyu.core.model.UmsShopInfoExample;
import com.chengyu.core.service.shop.ShopConfigService;
import com.chengyu.core.service.shop.ShopInfoService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  审核店铺
 * @author LeGreen
 * @date   2022/4/23
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {
	
	@Autowired
	private UmsShopInfoMapper shopInfoMapper;
	@Autowired
	private UmsShopMapper shopMapper;
	@Autowired
	private ShopConfigService shopConfigService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Integer applyShopCert(UmsMember member, UmsShopInfo shopInfo) throws ServiceException {
		if(shopInfo.getId() == null){
			//判断该用户是否申请过店铺
			UmsShopInfoExample example = new UmsShopInfoExample();
			example.createCriteria().andMemberIdEqualTo(member.getId());
			long count = shopInfoMapper.countByExample(example);
			if(count > 0){
				throw new ServiceException("您已申请过店铺,请勿重复申请");
			}
		}
		//判断店铺是否已通过
		UmsShopInfoExample example = new UmsShopInfoExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andStatusEqualTo(ShopEnums.ShopStatus.SUCCESS.getValue());
		long count = shopInfoMapper.countByExample(example);
		if(count > 0){
			throw new ServiceException("您已申请过店铺,请勿重复申请");
		}
		shopInfo.setStatus(ShopEnums.ShopStatus.SUBMIT_CERT.getValue());
		shopInfo.setUpdTime(DateUtil.date());
		if(shopInfo.getId() == null){
			shopInfo.setAddTime(shopInfo.getUpdTime());
			shopInfoMapper.insert(shopInfo);
		}else{
			shopInfoMapper.updateByPrimaryKeySelective(shopInfo);
		}
		return shopInfo.getId();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void applyShopDetail(UmsShopInfo shopInfo) {
		shopInfo.setUpdTime(DateUtil.date());
		shopInfo.setStatus(ShopEnums.ShopStatus.SUBMIT_SHOP.getValue());
		shopInfoMapper.updateByPrimaryKeySelective(shopInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void submitShopInfo(UmsShopInfo shopInfo) {
		shopInfo.setUpdTime(DateUtil.date());
		shopInfo.setStatus(ShopEnums.ShopStatus.SUBMIT_VERIFY.getValue());
		shopInfoMapper.updateByPrimaryKeySelective(shopInfo);
	}

	@Override
	public UmsShopInfo getShopInfo(UmsMember member) {
		UmsShopInfoExample example = new UmsShopInfoExample();
		example.createCriteria().andMemberIdEqualTo(member.getId());
		List<UmsShopInfo> list = shopInfoMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	public List<UmsShopInfo> getShopInfoList(ShopSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		UmsShopInfoExample example = new UmsShopInfoExample();
		example.setOrderByClause("add_time desc");
		UmsShopInfoExample.Criteria criteria = example.createCriteria();

		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getMemberName())){
			criteria.andMemberNameEqualTo(form.getMemberName());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(StringUtils.isNotBlank(form.getRealname())){
			criteria.andRealnameEqualTo(form.getRealname());
		}
		if(StringUtils.isNotBlank(form.getIdcard())){
			criteria.andIdcardEqualTo(form.getIdcard());
		}
		if(StringUtils.isNotBlank(form.getPhone())){
			criteria.andPhoneEqualTo(form.getPhone());
		}
		if(StringUtils.isNotBlank(form.getName())){
			criteria.andNameEqualTo(form.getName());
		}
		if(StringUtils.isNotBlank(form.getWeixin())){
			criteria.andWeixinEqualTo(form.getWeixin());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		return shopInfoMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyShop(Integer shopInfoId, Integer status, String reason) throws ServiceException {
		UmsShopInfo shopInfo = shopInfoMapper.selectByPrimaryKey(shopInfoId);
		if(shopInfo == null || shopInfo.getStatus() != ShopEnums.ShopStatus.SUBMIT_VERIFY.getValue()){
			throw new ServiceException("当前店铺状态不正确");
		}
		UmsShopInfo updateInfo = new UmsShopInfo();
		updateInfo.setId(shopInfoId);
		updateInfo.setStatus(status);
		updateInfo.setReason(reason);
		updateInfo.setUpdTime(DateUtil.date());
		shopInfoMapper.updateByPrimaryKeySelective(updateInfo);

		if(status == ShopEnums.ShopStatus.SUCCESS.getValue()){
			//审核通过，创建店铺
			UmsShop shop = new UmsShop();
			BeanUtil.copyProperties(shopInfo, shop);
			shop.setLevel(0);
			shop.setSort(9999);
			shop.setStatus(CommonConstant.SUS_INT);
			shop.setAddTime(DateUtil.date());
			shop.setUpdTime(shop.getAddTime());
			shopMapper.insert(shop);

			shopConfigService.initShopConfig(shop);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteShopInfo(Integer shopInfoId) throws ServiceException {
		UmsShopInfo shopInfo = shopInfoMapper.selectByPrimaryKey(shopInfoId);
		if(shopInfo == null || shopInfo.getStatus() == ShopEnums.ShopStatus.SUCCESS.getValue()){
			throw new ServiceException("入驻成功的店铺无法删除");
		}
		shopInfoMapper.deleteByPrimaryKey(shopInfoId);
	}
}