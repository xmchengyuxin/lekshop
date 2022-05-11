package com.chengyu.core.service.shop.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.domain.enums.ShopEnums;
import com.chengyu.core.domain.result.ShopFreightResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsShopFreightTemplateAreaMapper;
import com.chengyu.core.mapper.UmsShopFreightTemplateMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.shop.ShopFreightService;
import com.chengyu.core.util.ValidateUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  店铺运费模板
 * @author LeGreen
 * @date   2022/4/25
 */
@Service
public class ShopFreightServiceImpl implements ShopFreightService {

	@Autowired
	private UmsShopFreightTemplateMapper shopFreightTemplateMapper;
	@Autowired
	private UmsShopFreightTemplateAreaMapper shopFreightTemplateAreaMapper;

	@Override
	public List<UmsShopFreightTemplate> getFreightTemplateList(Integer shopId, String name, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsShopFreightTemplateExample example = new UmsShopFreightTemplateExample();
		example.setOrderByClause("id desc");
		UmsShopFreightTemplateExample.Criteria criteria = example.createCriteria();
		if(shopId != null){
			criteria.andShopIdEqualTo(shopId);
		}
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike("%"+name+"%");
		}
		return shopFreightTemplateMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Integer addFreightTemplate(UmsShop shop, UmsShopFreightTemplate template, List<UmsShopFreightTemplateArea> areaList) throws ServiceException {
		if(template.getType() == 1){
			ValidateUtil.validateNull(new Object[]{template.getValuteType(), template.getDefaultFirstNum(), template.getDefaultFirstPrice(), template.getDefaultSecondNum(), template.getDefaultSecondPrice()},
					"首件/重/体积不能为空", "首件价格/重/体积不能为空", "续件/重/体积不能为空", "续件价格/重/体积不能为空");
		}

		template.setShopId(shop.getId());
		template.setShopName(shop.getName());
		if(template.getId() == null){
			shopFreightTemplateMapper.insert(template);
		}else{
			shopFreightTemplateMapper.updateByPrimaryKeySelective(template);
		}

		if(CollectionUtil.isNotEmpty(areaList)){
			UmsShopFreightTemplateAreaExample example = new UmsShopFreightTemplateAreaExample();
			example.createCriteria().andTemplateIdEqualTo(template.getId());
			shopFreightTemplateAreaMapper.deleteByExample(example);

			for(UmsShopFreightTemplateArea area : areaList){
				area.setTemplateId(template.getId());
				shopFreightTemplateAreaMapper.insert(area);
			}
		}
		return template.getId();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteFreightTemplate(Integer id) {
		shopFreightTemplateMapper.deleteByPrimaryKey(id);

		UmsShopFreightTemplateAreaExample example = new UmsShopFreightTemplateAreaExample();
		example.createCriteria().andTemplateIdEqualTo(id);
		shopFreightTemplateAreaMapper.deleteByExample(example);
	}

	@Override
	public List<UmsShopFreightTemplate> getAllFreightTemplate(Integer shopId) {
		UmsShopFreightTemplateExample example = new UmsShopFreightTemplateExample();
		example.createCriteria().andShopIdEqualTo(shopId);
		return shopFreightTemplateMapper.selectByExample(example);
	}

	@Override
	public ShopFreightResult getFreightTemplate(Integer id) {
		ShopFreightResult result = new ShopFreightResult();
		result.setTemplate(shopFreightTemplateMapper.selectByPrimaryKey(id));

		UmsShopFreightTemplateAreaExample example = new UmsShopFreightTemplateAreaExample();
		example.createCriteria().andTemplateIdEqualTo(id);
		result.setAreaList(shopFreightTemplateAreaMapper.selectByExample(example));
		return result;
	}

	@Override
	public BigDecimal caculateFreightFee(Integer freightId, String provinceCode, String cityCode, Integer num, BigDecimal weight) {
		UmsShopFreightTemplate template = shopFreightTemplateMapper.selectByPrimaryKey(freightId);
		if(template.getType() == 2){
			//卖家包邮
			return BigDecimal.ZERO;
		}
		BigDecimal firstNum = template.getDefaultFirstNum();
		BigDecimal firstPrice = template.getDefaultFirstPrice();
		BigDecimal secondNum = template.getDefaultSecondNum();
		BigDecimal secondPrice = template.getDefaultSecondPrice();

		if(StringUtils.isNotBlank(provinceCode) || StringUtils.isNotBlank(cityCode)){
			//判断是否在特殊地区运费里面
			UmsShopFreightTemplateAreaExample example = new UmsShopFreightTemplateAreaExample();
			example.createCriteria().andTemplateIdEqualTo(freightId).andProvinceCodeEqualTo(provinceCode).andCityCodeIsNull();
			List<UmsShopFreightTemplateArea> areaList = shopFreightTemplateAreaMapper.selectByExample(example);
			if(CollectionUtil.isNotEmpty(areaList)){
				UmsShopFreightTemplateArea area = areaList.get(0);
				firstNum = area.getFirstNum();
				firstPrice = area.getFirstPrice();
				secondNum = area.getSecondNum();
				secondPrice = area.getSecondPrice();
			}

			example = new UmsShopFreightTemplateAreaExample();
			example.createCriteria().andTemplateIdEqualTo(freightId).andCityCodeEqualTo(cityCode);
			areaList = shopFreightTemplateAreaMapper.selectByExample(example);
			if(CollectionUtil.isNotEmpty(areaList)){
				UmsShopFreightTemplateArea area = areaList.get(0);
				firstNum = area.getFirstNum();
				firstPrice = area.getFirstPrice();
				secondNum = area.getSecondNum();
				secondPrice = area.getSecondPrice();
			}
		}

		//计价方式
		BigDecimal compareNum;
		if(template.getValuteType() == ShopEnums.FreightValuteType.BY_NUMBER.getValue()){
			compareNum = new BigDecimal(num);
		}else{
			compareNum = weight;
		}
		if(compareNum.compareTo(firstNum) <= 0){
			return firstPrice;
		}
		BigDecimal xuNum = NumberUtil.sub(compareNum,firstNum);
		BigDecimal times = NumberUtil.div(xuNum, secondNum);
		return NumberUtil.add(firstPrice, NumberUtil.mul(secondPrice, times));
	}
}