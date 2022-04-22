package com.chengyu.core.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.SysWeixinTemplateMapper;
import com.chengyu.core.model.SysWeixinTemplate;
import com.chengyu.core.model.SysWeixinTemplateExample;
import com.chengyu.core.service.system.WeixinTemplateService;
import com.chengyu.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeixinTemplateServiceImpl implements WeixinTemplateService {
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SysWeixinTemplateMapper templateMapper;

	@Override
	public void updateWeixinTemplate(SysWeixinTemplate template) {
		template.setUpdTime(new Date());
		templateMapper.updateByPrimaryKeySelective(template);
		redisUtil.delete(RedisEnums.WEIXIN_TEMPLATE.getKey());
	}

	@SuppressWarnings("unchecked")
	@Override
	public SysWeixinTemplate getWeixinTemplate(String cate) {
		List<SysWeixinTemplate> templateList = (List<SysWeixinTemplate>) redisUtil.getRedisValue(RedisEnums.WEIXIN_TEMPLATE.getKey());
		if(CollectionUtil.isEmpty(templateList)){
			SysWeixinTemplateExample example = new SysWeixinTemplateExample();
			templateList = templateMapper.selectByExample(example);
			redisUtil.setRedisValue(RedisEnums.WEIXIN_TEMPLATE.getKey(), templateList);
		}
		List<SysWeixinTemplate> list = templateList.stream().filter(template -> cate.equals(template.getCate())).collect(Collectors.toList());
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<SysWeixinTemplate> getWeixinTemplateList() {
		SysWeixinTemplateExample example = new SysWeixinTemplateExample();
		return templateMapper.selectByExample(example);
	}

}