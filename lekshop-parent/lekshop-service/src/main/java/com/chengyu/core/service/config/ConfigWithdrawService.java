package com.chengyu.core.service.config;

import com.chengyu.core.model.ConfigWithdraw;

/**
 * @title  会员组提现设置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigWithdrawService {

    /**
     * 根据会员组获取提现设置
     * @author LeGreen
     * @date   2021/1/6
     * @param  groupId
     * @return com.chengyu.core.model.ConfigBlack
     */
    ConfigWithdraw getConfigWithdrawByGroupId(Integer groupId);

    /**
     * 添加更新提现配置
     * @author LeGreen
     * @date   2021/1/6
     * @param  config
     */
    void addConfigWithdraw(ConfigWithdraw config);
}
