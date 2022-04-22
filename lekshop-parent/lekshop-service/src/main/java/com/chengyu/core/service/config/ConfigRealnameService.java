package com.chengyu.core.service.config;

import com.chengyu.core.model.ConfigRealname;

/**
 * @title  会员组实名认证设置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigRealnameService {

    /**
     * 根据会员组获取实名认证设置
     * @author LeGreen
     * @date   2021/1/6
     * @param  groupId
     * @return com.chengyu.core.model.ConfigBlack
     */
    ConfigRealname getConfigRealnameByGroupId(Integer groupId);

    /**
     * 添加更新实名认证配置
     * @author LeGreen
     * @date   2021/1/6
     * @param  config
     */
    void addConfigRealname(ConfigRealname config);
}
