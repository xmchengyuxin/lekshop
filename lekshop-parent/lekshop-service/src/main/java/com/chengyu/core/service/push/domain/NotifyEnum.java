package com.chengyu.core.service.push.domain;

import lombok.Getter;

/**
 * @title  通知类型
 * @author LeGreen
 * @date   2021/1/5
 */
@Getter
public enum NotifyEnum {

	/**通知枚举**/
	ORDER_NOTICE("order_notice", "配送订单分配", "appointorder"),
	ORDER_GRAB_NOTICE("order_grab_notice", "任务大厅到单提醒", "oncall"),
	ORDER_APPOINT_NOTICE("order_appoint_notice", "指派订单", "appointorder"),
	ORDER_RECEIVE_WARN("order_receive_warn", "订单收到超时提醒", "pushbysystemagain"),
	ORDER_TRANSFER_FAIL("order_transfer_fail", "转单失败", "attornorderfail"),
	ORDER_TRANSFER_SUS("order_transfer_sus", "转单成功", "pushtransfersucceed"),
	ORDER_TRANSFER_RECEIVE("order_transfer_receive", "收到转单", "appointorder"),
	ORDER_OVERTIME("order_overtime", "订单即将超时提醒", "timeout"),
	ORDER_CANCEL("order_cancel", "订单取消提醒", "cancelorder"),
	ORDER_UNPICKUP("order_unpick", "订单未取餐提醒", "pushtofetch"),
	WORK_ON("work_on", "上线", "workon"),
	WORK_OFF("work_off", "下线", "workoff"),
	RIDER_REFRESH("rider_refresh", "刷新页面", "mutechecker");
	
	
    private String key;
    
    private String name;
    
    private String sound;

    NotifyEnum(String key, String name, String sound) {
    	this.key = key;
        this.name = name;
        this.sound = sound;
    }
}
