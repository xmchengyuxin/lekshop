package com.chengyu.core.service.content.impl;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CmsSourceCateMapper;
import com.chengyu.core.model.CmsSourceCate;
import com.chengyu.core.model.CmsSourceCateExample;
import com.chengyu.core.service.content.SourceCateService;
import com.chengyu.core.service.content.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  资源分类
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class SourceCateServiceImpl implements SourceCateService {
	
	@Autowired
	private CmsSourceCateMapper sourceCateMapper;
	@Autowired
	private SourceService sourceService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSourceCate(CmsSourceCate cate) {
		cate.setAddTime(new Date());
		cate.setUpdTime(cate.getAddTime());
		sourceCateMapper.insertSelective(cate);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateSourceCate(CmsSourceCate cate) {
		cate.setUpdTime(new Date());
		sourceCateMapper.updateByPrimaryKeySelective(cate);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSourceCateById(Integer id) throws ServiceException {
		sourceCateMapper.deleteByPrimaryKey(id);
		sourceService.deleteSourceByCateId(id);
	}
	
	@Override
	public CmsSourceCate getSourceCateById(Integer id) {
		return sourceCateMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<CmsSourceCate> getAllSourceCates() {
		CmsSourceCateExample example = new CmsSourceCateExample();
		example.setOrderByClause("sort asc");
		return sourceCateMapper.selectByExample(example);
	}

}