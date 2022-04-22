package com.chengyu.core.service.system;

import com.chengyu.core.model.SysJiguangConfig;

import java.util.List;

/**
 * @title  极光配置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface JiguangConfigService {

	/**
	 * 添加极光配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addJiguangConfig(SysJiguangConfig config);

	/**
	 * 更新极光配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 * @return int
	 */
	void updateJiguangConfig(SysJiguangConfig config);

	/**
	 * 删除极光配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteJiguangConfig(Integer id);

	/**
	 * 获取极光配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return com.chengyu.core.model.SysJiguangConfig
	 */
	SysJiguangConfig getJiguangConfig();

	/**
	 * 获取极光配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysJiguangConfig>
	 */
	List<SysJiguangConfig> getJiguangConfigList(Integer pageNum, Integer pageSize);

	/**
	 * 获取极光配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.SysJiguangConfig>
	 */
	List<SysJiguangConfig> getAllJiguangConfigs();
}