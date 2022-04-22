package com.chengyu.core.service.system;

import com.chengyu.core.model.SysZfbConfig;

import java.util.List;

/**
 * @title  支付宝配置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ZfbConfigService {

	/**
	 * 添加支付宝配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addZfbConfig(SysZfbConfig config);

	/**
	 * 更新支付宝配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void updateZfbConfig(SysZfbConfig config);

	/**
	 * 删除支付宝配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteZfbConfig(Integer id);

	/**
	 * 获取支付宝配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return com.chengyu.core.model.SysZfbConfig
	 */
	SysZfbConfig getZfbConfig();

	/**
	 * 支付宝配置分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysZfbConfig>
	 */
	List<SysZfbConfig> getZfbConfigList(Integer pageNum, Integer pageSize);
}