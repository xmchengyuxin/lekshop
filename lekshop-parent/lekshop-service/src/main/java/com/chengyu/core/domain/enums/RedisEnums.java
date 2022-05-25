package com.chengyu.core.domain.enums;

import lombok.Getter;

/**
 * @title  redis的key
 * @author LeGreen
 * @date   2021/1/6
 */
@Getter
public enum RedisEnums {
	/**
	 * 轮播图缓存key
	 */
	BANNER_KEY("rds-cms-banner"),
	/**
	 * 阿里云短信缓存key
	 */
	ALISMS_KEY("rds-sys-alismsConfig"),
	/**
	 * 七牛云缓存key
	 */
	QINIU_CONFIG_KEY("rds-system-qiniu"),
	/**
	 * 银行配置缓存key
	 */
	BANK_CONFIG_KEY("rds-system-bank"),
	/**
	 * 系统参数缓存key
	 */
	CONFIG_KEY("rds-sys-config"),
	/**
	 * 菜单缓存key
	 */
	MENU_KEY("rds-sys-menu"),
	/**
	 * 验证码缓存key
	 */
	VERIFY_CODE_KEY("rds-sys-message"),
	/**
	 * 微信配置缓存key
	 */
	WX_CONFIG_KEY("rds-sys-weixinConfig"),
	/**
	 * 支付宝缓存key
	 */
	ZFB_CONFIG_KEY("rds-sys-zfbConfig"),
	/**
	 * 码支付缓存key
	 */
	CODE_PAY_CONFIG_KEY("rds-sys-codePayConfig"),
	/**
	 * 认证缓存key
	 */
	APPROVE_CONFIG_KEY("rds-sys-approveConfig"),
	/**
	 * 极光配置缓存key
	 */
	JIGUANG_CONFIG_KEY("rds-sys-jiguangConfig"),
	/**
	 * 会员组缓存key
	 */
	MEMBER_GROUP_KEY("rds-ums-memberGroup"),
	/**
	 * 公众号配置缓存key
	 */
	GZH_CONFIG("rds-config-gzh"),
	/**
	 * 电商网站类别缓存key
	 */
	SHOP_CATE("rds-config-shopCate"),
	/**
	 * 微信模板消息
	 */
	WEIXIN_TEMPLATE("rds-sys-weixinTemplate"),
	/**
	 * 第三方
	 */
	THIRD_CONFIG("rds-third-config"),
	/**
	 * 第三方预警
	 */
	THIRD_WARN("rds-third-warn"),
	/**
	 * 订单配置
	 */
	CONFIG_ORDER("rds-ConfigOrder"),
	/**
	 * 店铺服务
	 */
	SHOP_SERVICE("rds-ShopService"),
	/**
	 * IM用户
	 */
	CHAT_MEMBER("rds-chatMember");

	private final String key;

	RedisEnums(String key){
		this.key = key;
	}
}
