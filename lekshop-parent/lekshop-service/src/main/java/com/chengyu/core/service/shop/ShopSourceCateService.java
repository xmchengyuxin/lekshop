package com.chengyu.core.service.shop;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopSourceCate;
import com.chengyu.core.model.UmsShopSourceCate;

import java.util.List;

/**
 * @title  资源分类
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ShopSourceCateService {

	/**
	 * 添加资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 * @throws ServiceException 业务异常
	 */
	void addSourceCate(UmsShop shop, UmsShopSourceCate cate) throws ServiceException;

	/**
	 * 更新资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 * @throws ServiceException 业务异常
	 */
	void updateSourceCate(UmsShop shop, UmsShopSourceCate cate) throws ServiceException;

	/**
	 * 删除资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void deleteSourceCateById(UmsShop shop, Integer id) throws ServiceException;

	/**
	 * 根据ID获取资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.model.UmsShopSourceCate
	 * @throws ServiceException 业务异常
	 */
	UmsShopSourceCate getSourceCateById(Integer id) throws ServiceException;

	/**
	 * 资源分类列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.UmsShopSourceCate>
	 * @throws ServiceException 业务异常
	 */
	List<UmsShopSourceCate> getAllSourceCates(Integer shopId) throws ServiceException;
}