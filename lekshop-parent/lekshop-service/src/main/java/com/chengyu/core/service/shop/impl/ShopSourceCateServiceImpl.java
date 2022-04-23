package com.chengyu.core.service.shop.impl;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsShopSourceCateMapper;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopSourceCate;
import com.chengyu.core.model.UmsShopSourceCateExample;
import com.chengyu.core.service.shop.ShopSourceCateService;
import com.chengyu.core.service.shop.ShopSourceService;
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
public class ShopSourceCateServiceImpl implements ShopSourceCateService {
	
	@Autowired
	private UmsShopSourceCateMapper sourceCateMapper;
	@Autowired
	private ShopSourceService sourceService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addSourceCate(UmsShop shop, UmsShopSourceCate cate) {
		cate.setShopId(shop.getId());
		cate.setShopName(shop.getName());
		cate.setAddTime(new Date());
		cate.setUpdTime(cate.getAddTime());
		sourceCateMapper.insertSelective(cate);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateSourceCate(UmsShop shop, UmsShopSourceCate cate) {
		cate.setShopId(shop.getId());
		cate.setShopName(shop.getName());
		cate.setUpdTime(new Date());
		sourceCateMapper.updateByPrimaryKeySelective(cate);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteSourceCateById(UmsShop shop, Integer id) throws ServiceException {
		UmsShopSourceCateExample example = new UmsShopSourceCateExample();
		example.createCriteria().andIdEqualTo(id).andShopIdEqualTo(shop.getId());
		sourceCateMapper.deleteByExample(example);

		sourceService.deleteSourceByCateId(shop, id);
	}
	
	@Override
	public UmsShopSourceCate getSourceCateById(Integer id) {
		return sourceCateMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<UmsShopSourceCate> getAllSourceCates(Integer shopId) {
		UmsShopSourceCateExample example = new UmsShopSourceCateExample();
		example.setOrderByClause("sort asc");
		example.createCriteria().andShopIdEqualTo(shopId);
		return sourceCateMapper.selectByExample(example);
	}

}