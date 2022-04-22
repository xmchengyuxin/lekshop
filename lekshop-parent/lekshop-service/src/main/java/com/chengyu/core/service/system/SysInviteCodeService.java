package com.chengyu.core.service.system;

import com.chengyu.core.model.SysInviteCode;

/**
 * @title  邀请码
 * @author LeGreen
 * @date   2021/10/20
 */
public interface SysInviteCodeService {

	/**
	 * 生成邀请码
	 * @author LeGreen
	 * @date   2021/10/20
	 * @return java.lang.String
	 */
	String genInviteCode();

	void updateUserId(String inviteCode, Integer type, Integer userId);

	/**
	 * 根据邀请码获取对应的用户
	 * @author LeGreen
	 * @date   2021/10/20
	 * @param  inviteCode
	 * @return com.chengyu.core.model.ConfigInviteCode
	 */
	SysInviteCode getConfigByInviteCode(String inviteCode);
}