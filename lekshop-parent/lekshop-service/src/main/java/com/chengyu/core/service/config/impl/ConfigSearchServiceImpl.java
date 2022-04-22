package com.chengyu.core.service.config.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.ConfigAccountMapper;
import com.chengyu.core.mapper.ConfigSearchMapper;
import com.chengyu.core.mapper.CustomConfigAccountMapper;
import com.chengyu.core.model.ConfigAccount;
import com.chengyu.core.model.ConfigAccountExample;
import com.chengyu.core.model.ConfigSearch;
import com.chengyu.core.model.ConfigSearchExample;
import com.chengyu.core.service.config.ConfigAccountService;
import com.chengyu.core.service.config.ConfigSearchService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  搜索关键词
 * @author LeGreen
 * @date   2022/4/22
 */
@Service
public class ConfigSearchServiceImpl implements ConfigSearchService {
	
	@Autowired
	private ConfigSearchMapper configSearchMapper;
	@Autowired
	private BaseMapper baseMapper;

	@Override
	public void addConfigSearch(ConfigSearch search) {
		search.setAddTime(DateUtil.date());
		search.setUpdTime(search.getAddTime());
		configSearchMapper.insert(search);
	}

	@Override
	public void updateConfigSearch(ConfigSearch search) {
		search.setUpdTime(DateUtil.date());
		configSearchMapper.updateByPrimaryKeySelective(search);
	}

	@Override
	public void deleteConfigSearchById(Integer id) {
		configSearchMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ConfigSearch getConfigSearchById(Integer id) {
		return configSearchMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ConfigSearch> getConfigSearchList(Integer status, String keyword, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		ConfigSearchExample example = new ConfigSearchExample();
		example.setOrderByClause("num desc, add_time desc");
		ConfigSearchExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(keyword)){
			criteria.andNameLike("%"+keyword+"%");
		}
		if(status != null){
			criteria.andStatusEqualTo(status);
		}
		return configSearchMapper.selectByExample(example);
	}

	@Override
	public List<ConfigSearch> getTenSearchList() {
		ConfigSearchExample example = new ConfigSearchExample();
		example.setOrderByClause("num desc, add_time desc limit 10");
		ConfigSearchExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(CommonConstant.YES_INT);
		return configSearchMapper.selectByExample(example);
	}

	@Override
	public void addSearch(String keyword) {
		ConfigSearchExample example = new ConfigSearchExample();
		example.createCriteria().andNameEqualTo(keyword);
		if(configSearchMapper.countByExample(example) > 0){
			//搜索次数加1
			baseMapper.update("update config_search set num = num+1 where name = "+keyword);
		}else{
			ConfigSearch search = new ConfigSearch();
			search.setName(keyword);
			search.setNum(1);
			search.setHotStatus(CommonConstant.NO_INT);
			search.setStatus(CommonConstant.YES_INT);
			this.addConfigSearch(search);
		}
	}
}