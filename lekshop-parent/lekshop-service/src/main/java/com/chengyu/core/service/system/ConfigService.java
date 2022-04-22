package com.chengyu.core.service.system;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * @title  系统配置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigService {

	/**
	 * 添加配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void addConfig(SysConfig config);
	
	/**
	 * 更新配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  config
	 */
	void updateConfig(SysConfig config);
	
	/**
	 * 批量更新配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  configList
	 */
	void updateConfig(List<SysConfig> configList);
	
	/**
	 * 根据nid删除配置
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  nid
	 */
	void deleteConfigByNid(String nid);
	
	/**
	 * 获取所有配置信息
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.List<com.chengyu.core.model.SysConfig>
	 */
	List<SysConfig> getAllConfigs();

	/**
	 * 获取所有配置信息(无缓存)
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param
	 * @return java.util.List<com.chengyu.core.model.SysConfig>
	 */
	List<SysConfig> getAllConfigsByNoCache();
	
	/**
	 * 根据nid获取参数
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  nid
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String getValueByNid(String nid);
	
	/**
	 * 根据nid获取配置信息
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  nid
	 * @return com.chengyu.core.model.SysConfig
	 */
	SysConfig getConfigByNid(String nid);

	/**
	 * 获取所有的配置信息(Map格式)
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.Map<java.lang.String,com.chengyu.core.model.SysConfig>
	 */
	Map<String, SysConfig> getAllConfigMap();

	/**
	 * 获取所有的配置信息(Map格式)
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 */
	Map<String, String> getAllConfigValueMap();

	/**
	 * 获取所有的配置信息(无缓存)
	 * @author LeGreen
	 * @date   2021/1/6
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 */
	Map<String, String> getAllConfigValueMapByNoCache();

	/**
	 * 校验网站维护状态
	 * @author LeGreen
	 * @date   2021/3/8
	 * @throws ServiceException 业务异常
	 */
	void validateWeihuOnlyLogin() throws ServiceException;

	/**
	 * 校验网站维护状态
	 * @author LeGreen
	 * @date   2021/3/8
	 * @throws ServiceException 业务异常
	 */
	void validateWeihuNoTakeTask() throws ServiceException;

	/**
	 * 是否正确的平台
	 * @author LeGreen
	 * @date   2021/8/26
	 * @param  platform
	 * @return boolean
	 * @throws ServiceException 业务异常
	 */
	boolean isPlatformRight(String platform);
}