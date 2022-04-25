package com.chengyu.core.service.shop;

import com.chengyu.core.domain.result.ShopCateResult;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopCate;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @title  店铺商品分类
 * @author LeGreen
 * @date   2022/4/25
 */
public interface ShopCateService {

	/**
	 * 添加店铺分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shop
	 * @param  pid
	 * @param  name
	 * @param  sort
	 * @param  img
	 */
	void addShopCate(UmsShop shop, Integer pid, String name, Integer sort, String img);

	/**
	 * 更新店铺分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  cate
	 */
	void updateShopCate(UmsShopCate cate);

	/**
	 * 删除店铺分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  id
	 */
	void deleteShopCate(Integer id);

	/**
	 * 获取店铺分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shopId
	 * @return List<ShopCateResult>
	 */
	List<ShopCateResult> getShopCateResults(Integer shopId, Integer status);
}