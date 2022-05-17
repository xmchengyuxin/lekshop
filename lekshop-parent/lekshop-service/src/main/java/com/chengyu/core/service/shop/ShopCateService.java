package com.chengyu.core.service.shop;

import com.chengyu.core.domain.result.ShopCateResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopCate;
import io.swagger.models.auth.In;

import java.util.ArrayList;
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
	void deleteShopCate(Integer id) throws ServiceException;

	/**
	 * 获取店铺分类
	 * @author LeGreen
	 * @date   2022/4/25
	 * @param  shopId
	 * @return List<ShopCateResult>
	 */
	List<ShopCateResult> getShopCateResults(Integer shopId, Integer status);

	/**
	 * 按分级查询店铺分类
	 * @author LeGreen
	 * @date   2022/5/13
	 * @param  shopId
	 * @return List<UmsShopCate>
	 */
    List<UmsShopCate> getAllShopCateByLevel(Integer shopId);

	/**
	 * 根据ID获取店铺分类
	 * @author LeGreen
	 * @date   2022/5/14
	 * @param  shopCateId
	 * @return UmsShopCate
	 */
	UmsShopCate getShopCateById(Integer shopCateId);

	/**
	 * 获取商品分类名称
	 * @author LeGreen
	 * @date   2022/5/14
	 * @param  cateIdList
	 * @return String
	 */
	String getGoodsCateName(List<Integer> cateIdList);
}