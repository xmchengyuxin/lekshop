package com.chengyu.core.service.shop.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.ShopSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.*;
import com.chengyu.core.model.*;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.service.shop.ShopServiceService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  店铺接口
 * @author LeGreen
 * @date   2022/4/28
 */
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private UmsShopMapper shopMapper;
	@Autowired
	private UmsShopCateMapper shopCateMapper;
	@Autowired
	private UmsShopConfigMapper shopConfigMapper;
	@Autowired
	private UmsShopCouponMapper shopCouponMapper;
	@Autowired
	private UmsShopFreightTemplateMapper shopFreightTemplateMapper;
	@Autowired
	private UmsShopInfoMapper shopInfoMapper;
	@Autowired
	private UmsShopSourceMapper shopSourceMapper;
	@Autowired
	private UmsShopSourceCateMapper shopSourceCateMapper;
	@Autowired
	private PmsGoodsMapper goodsMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ShopServiceService shopServiceService;

	@Override
	public List<UmsShop> getShopList(ShopSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsShopExample example = new UmsShopExample();
		UmsShopExample.Criteria criteria = example.createCriteria();
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getMemberName())){
			criteria.andMemberNameEqualTo(form.getMemberName());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(StringUtils.isNotBlank(form.getName())){
			criteria.andNameLike("%"+form.getName()+"%");
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		return shopMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateShop(UmsShop shop) {
		if(StringUtils.isNotBlank(shop.getService())){
			List<Integer> serviceIds = Convert.convert(new TypeReference<List<Integer>>() {}, shop.getService().split(CommonConstant.DH_REGEX));
			shop.setServiceName(shopServiceService.getShopServiceName(serviceIds));
		}
		shop.setUpdTime(DateUtil.date());
		shopMapper.updateByPrimaryKeySelective(shop);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteShop(Integer shopId) throws ServiceException {
		UmsShop shop = shopMapper.selectByPrimaryKey(shopId);
		shopMapper.deleteByPrimaryKey(shopId);

		//删除店铺相关信息
		UmsShopCateExample cateExample = new UmsShopCateExample();
		cateExample.createCriteria().andShopIdEqualTo(shopId);
		shopCateMapper.deleteByExample(cateExample);

		UmsShopConfigExample configExample = new UmsShopConfigExample();
		configExample.createCriteria().andShopIdEqualTo(shopId);
		shopConfigMapper.deleteByExample(configExample);

		UmsShopCouponExample couponExample = new UmsShopCouponExample();
		couponExample.createCriteria().andShopIdEqualTo(shopId);
		shopCouponMapper.deleteByExample(couponExample);

		UmsShopFreightTemplateExample freightTemplateExample = new UmsShopFreightTemplateExample();
		freightTemplateExample.createCriteria().andShopIdEqualTo(shopId);
		shopFreightTemplateMapper.deleteByExample(freightTemplateExample);

		UmsShopInfoExample shopInfoExample = new UmsShopInfoExample();
		shopInfoExample.createCriteria().andMemberIdEqualTo(shop.getMemberId());
		shopInfoMapper.deleteByExample(shopInfoExample);

		UmsShopSourceExample sourceExample = new UmsShopSourceExample();
		sourceExample.createCriteria().andShopIdEqualTo(shopId);
		shopSourceMapper.deleteByExample(sourceExample);

		UmsShopSourceCateExample sourceCateExample = new UmsShopSourceCateExample();
		sourceCateExample.createCriteria().andShopIdEqualTo(shopId);
		shopSourceCateMapper.deleteByExample(sourceCateExample);

		//删除商品
		PmsGoodsExample goodsExample = new PmsGoodsExample();
		goodsExample.createCriteria().andShopIdEqualTo(shopId);
		goodsMapper.deleteByExample(goodsExample);
	}

	@Override
	public UmsShop getShopById(Integer shopId) {
		return shopMapper.selectByPrimaryKey(shopId);
	}

	@Override
	public UmsShop getShopByMemberId(Integer memberId) {
		UmsShopExample example = new UmsShopExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<UmsShop> list = shopMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	public UmsMember getMemberByShopId(Integer shopId) {
		UmsShop shop = this.getShopById(shopId);
		return memberService.getMemberById(shop.getMemberId());
	}
}