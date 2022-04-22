package com.chengyu.core.service.content.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.CmsSourceMapper;
import com.chengyu.core.model.CmsSource;
import com.chengyu.core.model.CmsSourceExample;
import com.chengyu.core.service.content.SourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title  资源
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class SourceServiceImpl implements SourceService {
	
	@Autowired
	private CmsSourceMapper sourceMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSource(CmsSource source) {
		source.setAddTime(DateUtil.date());
		sourceMapper.insertSelective(source);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSourceById(Integer id) {
		sourceMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSourceByCateId(Integer cateId) {
		CmsSourceExample example = new CmsSourceExample();
		example.createCriteria().andCateIdEqualTo(cateId);
		sourceMapper.deleteByExample(example);
	}
	
	@Override
	public List<CmsSource> getSourceList(Integer cateId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		CmsSourceExample example = new CmsSourceExample();
		example.setOrderByClause("add_time desc");
		if(cateId != null){
			example.createCriteria().andCateIdEqualTo(cateId);
		}
		return sourceMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSources(String sources, Integer cateId, String type) {
		for(String sourceUrl : sources.split(CommonConstant.REGEX)){
			CmsSource source = new CmsSource();
			source.setCateId(cateId);
			source.setType(type);
			source.setSource(sourceUrl);
			this.addSource(source);
		}
		
	}

	@Override
	public long countNums(Integer cateId) {
		CmsSourceExample example = new CmsSourceExample();
		example.createCriteria().andCateIdEqualTo(cateId);
		return sourceMapper.countByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void moveCate(Integer id, Integer cateId) {
		CmsSource source = new CmsSource();
		source.setId(id);
		source.setCateId(cateId);
		sourceMapper.updateByPrimaryKeySelective(source);
	}
	

}