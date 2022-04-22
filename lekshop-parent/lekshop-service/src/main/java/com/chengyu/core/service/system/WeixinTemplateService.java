package com.chengyu.core.service.system;

import com.chengyu.core.model.SysWeixinTemplate;

import java.util.List;

public interface WeixinTemplateService {
	
	void updateWeixinTemplate(SysWeixinTemplate template);
	
	SysWeixinTemplate getWeixinTemplate(String cate);
	
	List<SysWeixinTemplate> getWeixinTemplateList();
}