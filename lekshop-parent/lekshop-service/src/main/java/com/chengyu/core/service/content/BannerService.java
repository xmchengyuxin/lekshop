package com.chengyu.core.service.content;

import java.util.List;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsBanner;

/**
 * @title  轮播图api
 * @author LeGreen
 * @date   2021/1/6
 */
public interface BannerService {

	/**
	 * 添加轮播图
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  banner
	 * @throws ServiceException
	 */
	void addBanner(CmsBanner banner) throws ServiceException;

	/**
	 * 更新轮播图
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  banner
	 * @throws ServiceException
	 */
	void updateBanner(CmsBanner banner) throws ServiceException;

	/**
	 * 删除轮播图
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException
	 */
	void deleteBannerById(Integer id) throws ServiceException;

	/**
	 * 获取轮播图
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.model.CmsBanner
	 * @throws ServiceException
	 */
	CmsBanner getBannerById(Integer id) throws ServiceException;

	/**
	 * 轮播图列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  location
	 * @return java.util.List<com.chengyu.core.model.CmsBanner>
	 * @throws ServiceException
	 */
	List<CmsBanner> getAllBanners(String location) throws ServiceException;

	/**
	 * 轮播图分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  location
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsBanner>
	 * @throws Exception
	 */
	List<CmsBanner> getBannerList(String location, Integer page, Integer pageSize) throws Exception;

	/**
	 * 轮播图点击次数
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  bannerId
	 * @throws ServiceException
	 */
	void updateBannerNums(Integer bannerId) throws ServiceException;
}