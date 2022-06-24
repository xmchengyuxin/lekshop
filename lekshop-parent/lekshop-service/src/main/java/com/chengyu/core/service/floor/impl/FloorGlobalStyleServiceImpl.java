package com.chengyu.core.service.floor.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.FloorGlobalStyleMapper;
import com.chengyu.core.model.FloorGlobalStyle;
import com.chengyu.core.model.FloorGlobalStyleExample;
import com.chengyu.core.service.floor.FloorGlobalStyleService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FloorGlobalStyleServiceImpl implements FloorGlobalStyleService {
	
	@Autowired
	private FloorGlobalStyleMapper floorGlobalStyleMapper;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
	public void addGlobalStyle(FloorGlobalStyle style) {
		FloorGlobalStyleExample example = new FloorGlobalStyleExample();
		example.createCriteria().andStyleKeyEqualTo(style.getStyleKey());
		List<FloorGlobalStyle> list = floorGlobalStyleMapper.selectByExample(example);
		if(CollectionUtil.isNotEmpty(list)){
			FloorGlobalStyle updateStyle = new FloorGlobalStyle();
			updateStyle.setId(list.get(0).getId());
			updateStyle.setUpdTime(DateUtil.date());
			updateStyle.setStyleValue(style.getStyleValue());
			floorGlobalStyleMapper.updateByPrimaryKeySelective(updateStyle);
		}else{
			style.setUpdTime(DateUtil.date());
			style.setAddTime(style.getUpdTime());
			floorGlobalStyleMapper.insertSelective(style);
		}
		redisUtil.delete(RedisEnums.MOBILE_GLOBAL_STYLE.getKey());
	}

	@Override
	public Map<String, String> getGlobalStyleByCache() {
		Map<String, String> result = (Map<String, String>) redisUtil.getRedisValue(RedisEnums.MOBILE_GLOBAL_STYLE.getKey());
		if(result != null){
			return result;
		}
		result = this.getGlobalStyleByNoCache();
		if(result != null){
			redisUtil.setRedisValue(RedisEnums.MOBILE_GLOBAL_STYLE.getKey(), result);
		}
		return result;
	}

	@Override
	public Map<String, String> getGlobalStyleByNoCache() {
		FloorGlobalStyleExample example = new FloorGlobalStyleExample();
		List<FloorGlobalStyle> list = floorGlobalStyleMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		return list.stream().collect(Collectors.toMap(FloorGlobalStyle::getStyleKey, FloorGlobalStyle::getStyleValue, (key1, key2) -> key2));
	}
}