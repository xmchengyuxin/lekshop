package com.chengyu.core.service.shop.impl;

import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.mapper.UmsShopSourceMapper;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopSource;
import com.chengyu.core.model.UmsShopSourceExample;
import com.chengyu.core.service.shop.ShopSourceService;
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
public class ShopSourceServiceImpl implements ShopSourceService {
	
	@Autowired
	private UmsShopSourceMapper sourceMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSource(UmsShop shop, UmsShopSource source) {
		source.setShopId(shop.getId());
		source.setShopName(shop.getName());
		source.setAddTime(DateUtil.date());
		sourceMapper.insertSelective(source);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSourceById(UmsShop shop,Integer id) {
		UmsShopSourceExample example = new UmsShopSourceExample();
		example.createCriteria().andIdEqualTo(id).andShopIdEqualTo(shop.getId());
		sourceMapper.deleteByExample(example);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSourceByCateId(UmsShop shop, Integer cateId) {
		UmsShopSourceExample example = new UmsShopSourceExample();
		example.createCriteria().andCateIdEqualTo(cateId).andShopIdEqualTo(shop.getId());
		sourceMapper.deleteByExample(example);
	}
	
	@Override
	public List<UmsShopSource> getSourceList(UmsShop shop, Integer cateId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsShopSourceExample example = new UmsShopSourceExample();
		example.setOrderByClause("add_time desc");
		UmsShopSourceExample.Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(shop.getId());
		if(cateId != null){
			criteria.andCateIdEqualTo(cateId);
		}
		return sourceMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSources(UmsShop shop, String sources, Integer cateId, String type) {
		for(String sourceUrl : sources.split(CommonConstant.REGEX)){
			UmsShopSource source = new UmsShopSource();
			source.setCateId(cateId);
			source.setType(type);
			source.setSource(sourceUrl);
			this.addSource(shop, source);
		}
		
	}

	@Override
	public long countNums(Integer cateId) {
		UmsShopSourceExample example = new UmsShopSourceExample();
		example.createCriteria().andCateIdEqualTo(cateId);
		return sourceMapper.countByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void moveCate(Integer id, Integer cateId) {
		UmsShopSource source = new UmsShopSource();
		source.setId(id);
		source.setCateId(cateId);
		sourceMapper.updateByPrimaryKeySelective(source);
	}
	

}