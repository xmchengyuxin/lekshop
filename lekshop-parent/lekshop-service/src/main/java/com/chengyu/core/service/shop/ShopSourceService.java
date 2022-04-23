package com.chengyu.core.service.shop;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopSource;

import java.util.List;

/**
 * @title  资源API
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ShopSourceService {

	/**
	 * 添加资源
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  source
	 * @throws ServiceException 业务异常
	 */
	void addSource(UmsShop shop, UmsShopSource source) throws ServiceException;

	/**
	 * 删除资源
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void deleteSourceById(UmsShop shop, Integer id) throws ServiceException;

	/**
	 * 根据资源分类删除资源
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cateId
	 * @throws ServiceException 业务异常
	 */
	void deleteSourceByCateId(UmsShop shop, Integer cateId) throws ServiceException;

	/**
	 * 资源列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cateId
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsSource>
	 * @throws ServiceException 业务异常
	 */
	List<UmsShopSource> getSourceList(UmsShop shop, Integer cateId, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 添加上传资源
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  sources
	 * @param  cateId
	 * @param  type
	 * @throws ServiceException 业务异常
	 */
	void addSources(UmsShop shop, String sources, Integer cateId, String type) throws ServiceException;

	/**
	 * 根据分类统计资源数量
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cateId
	 * @return long
	 * @throws ServiceException 业务异常
	 */
	long countNums(Integer cateId) throws ServiceException;

	/**
	 * 移动资源
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @param  cateId
	 * @throws ServiceException 业务异常
	 */
	void moveCate(Integer id, Integer cateId) throws ServiceException;
}