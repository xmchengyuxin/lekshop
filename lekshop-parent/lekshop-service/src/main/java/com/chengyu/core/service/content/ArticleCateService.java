package com.chengyu.core.service.content;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsArticleCate;

import java.util.List;

/**
 * @title  文章分类
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ArticleCateService {

	/**
	 * 添加文章分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 */
	void addArticleCate(CmsArticleCate cate) ;

	/**
	 * 更新文章分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cate
	 */
	void updateArticleCate(CmsArticleCate cate) ;

	/**
	 * 删除文章分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteArticleCateById(Integer id) ;

	/**
	 * 根据ID获取文章分类
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.model.CmsArticleCate
	 */
	CmsArticleCate getArticleCateById(Integer id) ;

	/**
	 * 文章分类列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.CmsArticleCate>
	 */
	List<CmsArticleCate> getAllArticleCates();

	/**
	 * 文章分类分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsArticleCate>
	 * @throws ServiceException 业务异常
	 */
	List<CmsArticleCate> getArticleCateList(Integer page, Integer pageSize);
}