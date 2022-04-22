package com.chengyu.core.domain.enums;

/**
 * @title  用户类型
 * @author LeGreen
 * @date   2021/1/7
 */
public enum MemberTypes {
	/**用户名**/
	CODE,
	/**微信开放OpenId**/
	KF_OPEN_ID,
	/**微信小程序OpenId**/
	XCX_OPEN_ID,
	/**微信公众号OpenId**/
	GZH_OPEN_ID,
	/**微信unionId**/
	UNIONID,
	/**手机**/
	PHONE,
	/**邀请码**/
	INVITE_CODE,
	/**唯一ID**/
	UID
}
