package com.chengyu.core.service.point;

import com.chengyu.core.model.PointConfig;

/**
 * @title  积分配置
 * @author LeGreen
 * @date   2023/1/4
 */
public interface PointConfigService {

    /**
     * 获取积分配置(缓存)
     * @author LeGreen
     * @date   2023/1/4
     * @return PointConfig
     */
    PointConfig getPointConfig();

    /**
     * 获取积分配置(不带缓存)
     * @author LeGreen
     * @date   2023/1/4
     * @return PointConfig
     */
    PointConfig getPointConfigNoCache();

    /**
     * 保存积分配置
     * @author LeGreen
     * @date   2023/1/4
     * @param  config 配置信息
     */
    void addPointConfig(PointConfig config);
}
