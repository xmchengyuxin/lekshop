package com.chengyu.core.service.system;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysVerifycode;

import java.util.List;
import java.util.Map;

/**
 * @title  验证码记录
 * @author LeGreen
 * @date   2021/1/6
 */
public interface VerifyCodeService {

	/**
	 * 验证码记录分页
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  type
	 * @param  name
	 * @param  sendStatus
	 * @param  page
	 * @param  pageSize
	 * @return java.util.List<com.chengyu.core.model.SysVerifycode>
	 */
	List<SysVerifycode> getVerifyCodeList(String type, String name, String sendStatus, Integer page, Integer pageSize);
	
	/**
	 * 生成验证码
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  verifyCode
	 */
	void addVerifycode(SysVerifycode verifyCode);
	
	/**
	 * 获取最新的验证码
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  type
	 * @param  name
	 * @return java.lang.String
	 * @throws ServiceException 业务异常
	 */
	String getLastVerifyCode(String type, String name) throws ServiceException;
	
	/**
	 * 发送短信
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  phone 手机
	 * @param  templateType 模板类型
	 * @param  templateParam 模板参数
	 * @throws ServiceException 业务异常
	 */
	void sendSms(String phone, String templateType, Map<String, String> templateParam) throws ServiceException;
	
	/**
	 * 校验验证码
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  userName 手机号码
	 * @param  code 验证码
	 * @throws ServiceException 业务异常
	 */
	void validateCode(String userName, String code) throws ServiceException;
}