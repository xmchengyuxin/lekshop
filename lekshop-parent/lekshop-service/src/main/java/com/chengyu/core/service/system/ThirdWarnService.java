package com.chengyu.core.service.system;

import com.chengyu.core.model.SysThirdWarn;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  第三方余额预警
 * @author LeGreen
 * @date   2021/10/22
 */
public interface ThirdWarnService {

	/**
	 * 获取所有的余额预警
	 * @author LeGreen
	 * @date   2021/10/22
	 * @return java.util.List<com.chengyu.core.model.SysThirdConfig>
	 */
	List<SysThirdWarn> getAllConfig();

	/**
	 * 更新配置
	 * @author LeGreen
	 * @date   2021/10/22
	 * @param  config
	 */
	void updateConfig(SysThirdWarn config);

	/**
	 * 执行预警
	 * @author LeGreen
	 * @date   2021/10/23
	 */
	void warn() throws Exception;

	/**
	 * 获取第三方接口的额度
	 * @author LeGreen
	 * @date   2021/10/23
	 * @param  nid
	 * @param  interfaceNid
	 * @return java.math.BigDecimal
	 */
	BigDecimal getLeftAmount(String nid, String interfaceNid) throws Exception;
}