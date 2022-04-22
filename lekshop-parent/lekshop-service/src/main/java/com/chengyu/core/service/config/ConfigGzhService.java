package com.chengyu.core.service.config;

import com.chengyu.core.model.ConfigGzh;

/**
 * @title  公众号接口
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigGzhService {

    /**
     * 公众号接口
     * @author LeGreen
     * @date   2021/1/6
     * @return com.chengyu.core.model.ConfigGzh
     */
    ConfigGzh getGzh();

    /**
     * 获取公众号配置
     * @author LeGreen
     * @date   2021/2/19
     * @return com.chengyu.core.model.ConfigGzh
     */
    ConfigGzh getGzhNoCache();

    /**
     * 添加公众号接口
     * @author LeGreen
     * @date   2021/1/6
     * @param  Gzh
     */
    void addConfigGzh(ConfigGzh Gzh);
}
