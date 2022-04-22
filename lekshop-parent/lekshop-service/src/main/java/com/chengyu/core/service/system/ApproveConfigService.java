package com.chengyu.core.service.system;

import com.chengyu.core.model.SysApproveConfig;

import java.util.List;

/**
 * @title  认证配置
 * @author LeGreen
 * @date   2021/1/29
 */
public interface ApproveConfigService {

	/**
	 * 添加认证配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addApproveConfig(SysApproveConfig config);

	/**
	 * 更新认证配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void updateApproveConfig(SysApproveConfig config);

	/**
	 * 删除认证配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteApproveConfig(Integer id);

	/**
	 * 获取认证配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return com.chengyu.core.model.SysApproveConfig
	 */
	SysApproveConfig getApproveConfig();

	/**
	 * 认证配置分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  pageNum
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysApproveConfig>
	 */
	List<SysApproveConfig> getApproveConfigList(Integer pageNum, Integer pageSize);
}