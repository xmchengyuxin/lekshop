package com.chengyu.core.service.config;

import com.chengyu.core.model.ConfigOrder;

/**
 * @title  订单参数接口
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigOrderService {

    /**
     * 订单参数接口
     * @author LeGreen
     * @date   2021/1/6
     * @return com.chengyu.core.model.ConfigOrder
     */
    ConfigOrder getConfigOrder();

    /**
     * 获取订单参数配置
     * @author LeGreen
     * @date   2021/2/19
     * @return com.chengyu.core.model.ConfigOrder
     */
    ConfigOrder getConfigOrderNoCache();

    /**
     * 添加订单参数接口
     * @author LeGreen
     * @date   2021/1/6
     * @param  config
     */
    void addConfigOrder(ConfigOrder config);
}
