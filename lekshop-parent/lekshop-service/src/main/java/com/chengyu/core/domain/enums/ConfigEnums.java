package com.chengyu.core.domain.enums;

import lombok.Getter;

public class ConfigEnums {

	@Getter
	public enum ConfigTypes{
		/**
		 * 配置枚举值
		 */
		LOGO("后台LOGO", "logo", "https://qiniu.chengyuwb.com/1597290713361.png", "https://qiniu.chengyuwb.com/1597290713361.png"),
		HEADIMG("默认头像", "default_head_img", "https://qiniu.miaotu2018.com/1587609809364.png", "https://qiniu.miaotu2018.com/1587609809364.png"),
		WEBNAME("网站名称", "web_name", "#", "NVL"),
		SHAREURL("分享链接", "share_url", "#", "NVL"),
		SHARETITLE("分享标题", "share_invite_title", "#", "NVL"),
		SHARECONTENT("分享内容", "share_invite_content", "#", "NVL"),
		SHAREIMG("分享图片", "share_invite_img", "NVL", "NVL"),
		ANDROIDURL("安卓APP下载连接", "android_app_download_url", "#", "NVL"),
		IOSURL("苹果APP下载连接", "ios_app_download_url", "#", "NVL"),
		ANDROIDVERSION("安卓版本", "android_version", "10000", "NVL"),
		IOSVERSION("苹果版本", "ios_version", "10000", "NVL"),
		ANDROIDUPDATESTATUS("安卓是否强制更新(0:关 1开)", "android_update_status", "0", "NVL"),
		IOSUPDATESTATUS("IOS是否强制更新(0:关 1开)", "ios_update_status", "0", "NVL"),
		KEFUTEL("客服电话", "kefu_tel", "#", "NVL");
		
		private final String name;
		private final String nid;
		private final String value;
		private final String image;
		
		private ConfigTypes(String name, String nid, String value, String image){
			this.name = name;
			this.value = value;
			this.nid = nid;
			this.image = image;
		}
	}
	
}
