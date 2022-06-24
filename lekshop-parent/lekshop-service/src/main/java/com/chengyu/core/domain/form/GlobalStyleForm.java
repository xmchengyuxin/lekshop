package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalStyleForm {

	//首页背景图
	private String index_background="https://qiniu.chengyuwb.com/1656061896468.png";
	//首页导航颜色
	private String index_nav_color="#ff4950";
	//首页导航字体颜色
	private String index_nav_font_color="#FFFFFF";
	//逛逛导航颜色
	private String discovery_nav_color="#F33F3C";
	//逛逛导航字体颜色
	private String discovery_nav_font_color="#FFFFFF";
	//个人中心背景图
	private String user_center_nav_background="https://qiniu.chengyuwb.com/shops_user_bg.png";
	//是否隐藏扫一扫
	private String index_show_hide_scan="1";
	//是否隐藏优惠券入口
	private String index_show_hide_coupon="1";
	//是否隐藏分类入口
	private String index_show_hide_cate="1";
	//是否隐藏类目导航
	private String index_show_hide_category="1";
}
