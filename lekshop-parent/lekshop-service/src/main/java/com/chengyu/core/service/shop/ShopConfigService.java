package com.chengyu.core.service.shop;

import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopConfig;

/**
 * @title  店铺配置
 * @author LeGreen
 * @date   2022/4/23
 */
public interface ShopConfigService {

	/**
	 * 初始化店铺配置
	 * @author LeGreen
	 * @date   2022/4/23
	 * @param  shop
	 */
	void initShopConfig(UmsShop shop);

	/**
	 * 获取店铺配置
	 * @author LeGreen
	 * @date   2022/4/23
	 * @param  shopId
	 * @return UmsShopConfig
	 */
	UmsShopConfig getShopConfig(Integer shopId);

	/**
	 * 更新店铺配置
	 * @author LeGreen
	 * @date   2022/4/23
	 * @param  config
	 */
	void updateShopConfig(UmsShopConfig config);
}