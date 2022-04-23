package com.chengyu.core.service.shop.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.UmsShopServiceMapper;
import com.chengyu.core.model.UmsShopService;
import com.chengyu.core.model.UmsShopServiceExample;
import com.chengyu.core.service.shop.ShopServiceService;
import com.chengyu.core.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  店铺服务
 * @author LeGreen
 * @date   2022/4/23
 */
@Service
public class ShopServiceServiceImpl implements ShopServiceService {

	@Autowired
	private UmsShopServiceMapper shopServiceMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addShopService(UmsShopService service) {
		service.setAddTime(new Date());
		service.setUpdTime(service.getAddTime());
		shopServiceMapper.insertSelective(service);

		redisUtil.delete(RedisEnums.SHOP_SERVICE.getKey());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateShopService(UmsShopService service) {
		service.setUpdTime(new Date());
		shopServiceMapper.updateByPrimaryKeySelective(service);

		redisUtil.delete(RedisEnums.SHOP_SERVICE.getKey());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteShopServiceById(Integer id) {
		shopServiceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public UmsShopService getShopServiceById(Integer id) {
		return shopServiceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UmsShopService> getAllShopServices() {
		List<UmsShopService> list = (List<UmsShopService>) redisUtil.getRedisValue(RedisEnums.SHOP_SERVICE.getKey());
		if(CollectionUtil.isNotEmpty(list)){
			return list;
		}

		UmsShopServiceExample example = new UmsShopServiceExample();
		example.setOrderByClause("id asc");
		example.createCriteria().andStatusEqualTo(CommonConstant.YES_INT);
		list = shopServiceMapper.selectByExample(example);

		redisUtil.setRedisValue(RedisEnums.SHOP_SERVICE.getKey(), list);
		return list;
	}

	@Override
	public List<UmsShopService> getShopServiceList(Integer status, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsShopServiceExample example = new UmsShopServiceExample();
		example.setOrderByClause("id asc");
		if(status != null){
			example.createCriteria().andStatusEqualTo(status);
		}
		return shopServiceMapper.selectByExample(example);
	}
}