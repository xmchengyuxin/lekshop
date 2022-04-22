package com.chengyu.core.service.content;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.CmsArticle;

import java.util.List;

/**
 * @title  文章列表
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ArticleService {

	/**
	 * 添加文章
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  article
	 * @return int
	 * @throws ServiceException 业务异常
	 */
	int addArticle(CmsArticle article) throws ServiceException;

	/**
	 * 更新文章
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  article
	 * @return int
	 * @throws ServiceException 业务异常
	 */
	int updateArticle(CmsArticle article) throws ServiceException;

	/**
	 * 删除文章
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return int
	 * @throws ServiceException 业务异常
	 */
	int deleteArticleById(Integer id) throws ServiceException;

	/**
	 * 获取单篇文章
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @return com.chengyu.core.model.CmsArticle
	 * @throws ServiceException 业务异常
	 */
	CmsArticle getArticleById(Integer id) throws ServiceException;

	/**
	 * 根据分类ID获取文章列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cateId
	 * @return java.util.List<com.chengyu.core.model.CmsArticle>
	 * @throws ServiceException 业务异常
	 */
	List<CmsArticle> getAllArticles(Integer cateId, Integer type) throws ServiceException;

	/**
	 * 文章分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cateId
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.CmsArticle>
	 * @throws ServiceException 业务异常
	 */
	List<CmsArticle> getArticleList(Integer cateId, Integer type, String title, Integer page, Integer pageSize) throws ServiceException;

	/**
	 * 更新文章观看次数
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 * @throws ServiceException 业务异常
	 */
	void updateArticleNums(Integer id) throws ServiceException;

	/**
	 * 根据分类删除文章
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  cateId
	 * @throws ServiceException 业务异常
	 */
    void deleteArticleByCateId(Integer cateId);
}