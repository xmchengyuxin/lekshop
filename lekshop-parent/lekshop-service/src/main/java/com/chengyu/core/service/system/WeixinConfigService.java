package com.chengyu.core.service.system;

import com.chengyu.core.model.SysWeixinConfig;

import java.util.List;

/**
 * @title  微信配置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface WeixinConfigService {

	/**
	 * 添加微信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addWeixinConfig(SysWeixinConfig config);

	/**
	 * 更新微信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void updateWeixinConfig(SysWeixinConfig config);

	/**
	 * 删除微信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteWeixinConfig(Integer id);

	/**
	 * 获取微信配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return com.chengyu.core.model.SysWeixinConfig
	 */
	SysWeixinConfig getWeixinConfig();

	/**
	 * 微信配置分页信息
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysWeixinConfig>
	 */
	List<SysWeixinConfig> getWeixinConfigList(Integer pageNum, Integer pageSize);
}