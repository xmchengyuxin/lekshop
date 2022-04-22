package com.chengyu.core.service.content;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsAdvert;

import java.util.List;

/**
 * @title  广告API
 * @author LeGreen
 * @date   2021/1/6
 */
public interface AdvertService {

	/**
	 * 更新广告
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  advert
	 * @throws ServiceException 业务异常
	 */
	void updateAdvert(CmsAdvert advert) throws ServiceException;

	/**
	 * 添加广告
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  advert
	 * @throws ServiceException 业务异常
	 */
	void addAdvert(CmsAdvert advert) throws ServiceException;

	/**
	 * 根据类型获取广告
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 * @return com.chengyu.core.model.CmsAdvert
	 * @throws ServiceException 业务异常
	 */
	CmsAdvert getAdvertByCate(String cate) throws ServiceException;

	/**
	 * 获取广告列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsAdvert>
	 * @throws ServiceException 业务异常
	 */
	List<CmsAdvert> getAdvertList(Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 删除广告
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void deleteAdvert(Integer id) throws ServiceException;

	/**
	 * 增加点击次数
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void addNums(Integer id) throws ServiceException;
}