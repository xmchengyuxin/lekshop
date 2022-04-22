package com.chengyu.core.service.system;

import com.chengyu.core.model.SysAlismsConfig;

import java.util.List;

/**
 * @title  阿里短信配置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface AlismsConfigService {

	/**
	 * 添加短信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addAlismsConfig(SysAlismsConfig config);

	/**
	 * 更新短信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void updateAlismsConfig(SysAlismsConfig config);

	/**
	 * 删除短信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteAlismsConfig(Integer id);

	/**
	 * 获取短信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return com.chengyu.core.model.SysAlismsConfig
	 */
	SysAlismsConfig getAlismsConfig();

	/**
	 * 短信配置列表
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysAlismsConfig>
	 */
	List<SysAlismsConfig> getAlismsConfigList(Integer pageNum, Integer pageSize);
}