package com.chengyu.core.service.shop.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.ShopEnums;
import com.chengyu.core.mapper.UmsShopConfigMapper;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopConfig;
import com.chengyu.core.model.UmsShopConfigExample;
import com.chengyu.core.service.shop.ShopConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  店铺配置
 * @author LeGreen
 * @date   2022/4/23
 */
@Service
public class ShopConfigServiceImpl implements ShopConfigService {

	@Autowired
	private UmsShopConfigMapper shopConfigMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void initShopConfig(UmsShop shop) {
		UmsShopConfig config = new UmsShopConfig();
		config.setShopId(shop.getId());
		config.setShopName(shop.getName());
		config.setCateStyle(ShopEnums.CateStyle.ONE_BIG_IMG.getValue());
		config.setAddTime(DateUtil.date());
		config.setUpdTime(config.getAddTime());
		shopConfigMapper.insert(config);
	}

	@Override
	public UmsShopConfig getShopConfig(Integer shopId) {
		UmsShopConfigExample example = new UmsShopConfigExample();
		example.createCriteria().andShopIdEqualTo(shopId);
		List<UmsShopConfig> list = shopConfigMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateShopConfig(UmsShopConfig config) {
		config.setUpdTime(DateUtil.date());
		shopConfigMapper.updateByPrimaryKeySelective(config);
	}
}