package com.chengyu.core.service.content;

import java.util.List;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsSourceCate;

/**
 * @title  资源分类
 * @author LeGreen
 * @date   2021/1/6
 */
public interface SourceCateService {

	/**
	 * 添加资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 * @throws ServiceException 业务异常
	 */
	void addSourceCate(CmsSourceCate cate) throws ServiceException;

	/**
	 * 更新资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 * @throws ServiceException 业务异常
	 */
	void updateSourceCate(CmsSourceCate cate) throws ServiceException;

	/**
	 * 删除资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void deleteSourceCateById(Integer id) throws ServiceException;

	/**
	 * 根据ID获取资源分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.model.CmsSourceCate
	 * @throws ServiceException 业务异常
	 */
	CmsSourceCate getSourceCateById(Integer id) throws ServiceException;

	/**
	 * 资源分类列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.CmsSourceCate>
	 * @throws ServiceException 业务异常
	 */
	List<CmsSourceCate> getAllSourceCates() throws ServiceException;
}