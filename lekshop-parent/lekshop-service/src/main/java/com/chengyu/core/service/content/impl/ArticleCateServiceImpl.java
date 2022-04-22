package com.chengyu.core.service.content.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.CmsArticleCateMapper;
import com.chengyu.core.model.CmsArticleCate;
import com.chengyu.core.model.CmsArticleCateExample;
import com.chengyu.core.service.content.ArticleCateService;
import com.chengyu.core.service.content.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  文章分类
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class ArticleCateServiceImpl implements ArticleCateService {

	@Autowired
	private CmsArticleCateMapper articleCateMapper;
	@Autowired
	private ArticleService articleService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addArticleCate(CmsArticleCate cate) {
		cate.setAddTime(new Date());
		cate.setUpdTime(cate.getAddTime());
		articleCateMapper.insertSelective(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateArticleCate(CmsArticleCate cate) {
		cate.setUpdTime(new Date());
		articleCateMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteArticleCateById(Integer id) {
		articleCateMapper.deleteByPrimaryKey(id);
		articleService.deleteArticleByCateId(id);
	}

	@Override
	public CmsArticleCate getArticleCateById(Integer id) {
		return articleCateMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CmsArticleCate> getAllArticleCates() {
		CmsArticleCateExample example = new CmsArticleCateExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andStatusEqualTo(CommonConstant.YES);
		return articleCateMapper.selectByExample(example);
	}

	@Override
	public List<CmsArticleCate> getArticleCateList(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		CmsArticleCateExample example = new CmsArticleCateExample();
		example.setOrderByClause("sort asc");
		return articleCateMapper.selectByExample(example);
	}
}