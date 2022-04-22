package com.chengyu.core.service.content.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.CmsArticleMapper;
import com.chengyu.core.mapper.CustomArticleMapper;
import com.chengyu.core.model.CmsArticle;
import com.chengyu.core.model.CmsArticleExample;
import com.chengyu.core.service.content.ArticleCateService;
import com.chengyu.core.service.content.ArticleService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  文章
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private CmsArticleMapper articleMapper;
	@Autowired
	private CustomArticleMapper customArticleMapper;
	@Autowired
	private ArticleCateService articleCateService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int addArticle(CmsArticle article){
		article.setAddTime(DateUtil.date());
		article.setUpdTime(article.getAddTime());
		article.setNums(0);
		article.setCateName(articleCateService.getArticleCateById(article.getCateId()).getName());
		articleMapper.insertSelective(article);
		return article.getId();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int updateArticle(CmsArticle article){
		article.setUpdTime(DateUtil.date());
		if(article.getCateId() != null){
			article.setCateName(articleCateService.getArticleCateById(article.getCateId()).getName());
		}
		articleMapper.updateByPrimaryKeySelective(article);
		return article.getId();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int deleteArticleById(Integer id) {
		articleMapper.deleteByPrimaryKey(id);
		return id;
	}

	@Override
	public CmsArticle getArticleById(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CmsArticle> getAllArticles(Integer cateId, Integer type) {
		CmsArticleExample example = new CmsArticleExample();
		example.setOrderByClause("sort asc");
		CmsArticleExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(CommonConstant.YES);
		if(cateId != null){
			criteria.andCateIdEqualTo(cateId);
		}
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		return articleMapper.selectByExample(example);
	}

	@Override
	public List<CmsArticle> getArticleList(Integer cateId, Integer type,  String title, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
        CmsArticleExample example = new CmsArticleExample();
        example.setOrderByClause("sort asc, id asc");
        CmsArticleExample.Criteria criteria = example.createCriteria();
		if(cateId != null){
			criteria.andCateIdEqualTo(cateId);
		}
		if(type != null){
			criteria.andTypeEqualTo(type);
		}
		if(StringUtils.isNotBlank(title)){
			criteria.andTitleLike("%"+title+"%");
		}
        return articleMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateArticleNums(Integer articleId) {
		customArticleMapper.updateArticleNums(articleId);
	}

	@Override
	public void deleteArticleByCateId(Integer cateId) {
		CmsArticleExample example = new CmsArticleExample();
		example.createCriteria().andCateIdEqualTo(cateId);
		articleMapper.deleteByExample(example);
	}
}