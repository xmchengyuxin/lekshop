package com.chengyu.core.service.system;

import com.chengyu.core.model.SysThirdConfig;

import java.util.List;

/**
 * @title  三方接口切换
 * @author LeGreen
 * @date   2021/10/22
 */
public interface ThirdConfigService {

	/**
	 * 获取所有的接口切换
	 * @author LeGreen
	 * @date   2021/10/22
	 * @return java.util.List<com.chengyu.core.model.SysThirdConfig>
	 */
	List<SysThirdConfig> getAllConfig();

	/**
	 * 获取接口使用平台标识
	 * @author LeGreen
	 * @date   2021/10/22
	 * @param  nid
	 * @return java.lang.String
	 */
	String getThirdConfigByNid(String nid);

	/**
	 * 更新配置
	 * @author LeGreen
	 * @date   2021/10/22
	 * @param  config
	 */
	void updateConfig(SysThirdConfig config);

}