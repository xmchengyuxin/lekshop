package com.chengyu.core.service.content.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.CmsBannerMapper;
import com.chengyu.core.mapper.CustomBannerMapper;
import com.chengyu.core.model.CmsBanner;
import com.chengyu.core.model.CmsBannerExample;
import com.chengyu.core.service.content.BannerService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private CmsBannerMapper bannerMapper;
	@Autowired
	private CustomBannerMapper customBannerMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addBanner(CmsBanner banner){
		banner.setAddTime(new Date());
		banner.setUpdTime(banner.getAddTime());
		banner.setNums(0);
		bannerMapper.insert(banner);
		redisUtil.delete(RedisEnums.BANNER_KEY.getKey());
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateBanner(CmsBanner banner){
		banner.setUpdTime(banner.getAddTime());
		bannerMapper.updateByPrimaryKeySelective(banner);
		redisUtil.delete(RedisEnums.BANNER_KEY.getKey());
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteBannerById(Integer id) {
		bannerMapper.deleteByPrimaryKey(id);
		redisUtil.delete(RedisEnums.BANNER_KEY.getKey());
	}

	@Override
	public CmsBanner getBannerById(Integer id) {
		return bannerMapper.selectByPrimaryKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsBanner> getAllBanners(String location) {
		List<CmsBanner> bannerList = (List<CmsBanner>) redisUtil.getRedisValue(RedisEnums.BANNER_KEY.getKey());
		if(CollectionUtil.isEmpty(bannerList)){
			CmsBannerExample example = new CmsBannerExample();
			CmsBannerExample.Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo(CommonConstant.YES);
			example.setOrderByClause("sort asc");
			bannerList = bannerMapper.selectByExample(example);
		}
		if(CollectionUtil.isEmpty(bannerList)) {
			return bannerList;
		}

		return bannerList.stream().filter(banner->banner.getLocation().equals(location)).collect(Collectors.toList());
	}

	@Override
	public List<CmsBanner> getBannerList(String location, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
        CmsBannerExample example = new CmsBannerExample();
        example.setOrderByClause("sort asc, status desc");
        CmsBannerExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(location)){
        	criteria.andLocationEqualTo(location);
        }
        return bannerMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateBannerNums(Integer bannerId) {
		customBannerMapper.updateBannerNums(bannerId);
	}
}