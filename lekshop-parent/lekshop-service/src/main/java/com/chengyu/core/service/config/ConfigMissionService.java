package com.chengyu.core.service.config;

import com.chengyu.core.model.ConfigMission;
import com.chengyu.core.model.ConfigMissionDetail;

import java.util.List;

/**
 * @title  佣金组
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigMissionService {

	/**
	 * 佣金组分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.ConfigMission>
	 */
	List<ConfigMission> getConfigMissionList(Integer page, Integer pageSize);

	/**
	 * 获取所有的佣金组
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.ConfigMission>
	 */
	List<ConfigMission> getAllConfigMission();

	/**
	 * 获取默认佣金组
	 * @author LeGreen
	 * @date   2021/1/7
	 * @return com.chengyu.core.model.ConfigMission
	 */
	ConfigMission getDefaultConfigMission();

	/**
	 * 添加更新佣金组
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addConfigMission(ConfigMission config);

	/**
	 * 删除佣金组
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  id
	 */
	void deleteConfigMission(Integer id);

	/**
	 * 获取佣金组的佣金配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  missionConfigId
	 * @param  groupId
	 * @param  shopCateId
	 * @return com.chengyu.core.model.ConfigMissionDetail
	 */
	ConfigMissionDetail getConfigMissionDetail(Integer missionConfigId, Integer groupId);

	/**
	 * 添加更新佣金配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  detail
	 */
	void addConfigMissionDetail(ConfigMissionDetail detail);

	/**
	 * 修改参数
	 * @author LeGreen
	 * @date   2021/1/16
	 * @param  config
	 */
	void updateParams(ConfigMission config);
}