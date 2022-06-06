package com.chengyu.core.service.system;

import com.chengyu.core.domain.result.InterfaceConfig;
import com.chengyu.core.model.SysInterfaceConfig;

/**
 * @title  第三方接口配置
 * @author LeGreen
 * @date   2022/6/6
 */
public interface InterfaceConfigService {

	SysInterfaceConfig getConfig(String nid);

	SysInterfaceConfig getConfigNoCache(String nid);

	void updateConfig(SysInterfaceConfig config);

	InterfaceConfig getParams(String nid);
}