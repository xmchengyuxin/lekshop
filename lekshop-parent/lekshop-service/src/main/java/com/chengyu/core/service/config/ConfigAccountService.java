package com.chengyu.core.service.config;

import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.model.ConfigAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  收款账户设置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface ConfigAccountService {

    /**
     * 根据类型获取收款账户
     * @author LeGreen
     * @date   2021/1/6
     * @param  type
     * @return com.chengyu.core.model.ConfigAccount
     */
    ConfigAccount getAccountByType(AccountEnums.AccountTypes type);

    /**
     * 根据类型获取收款账户
     * @author LeGreen
     * @date   2021/1/9
     * @param  type
     * @return com.chengyu.core.model.ConfigAccount
     */
    ConfigAccount getAccountByType(Integer type);

    /**
     * 添加收款账号
     * @author LeGreen
     * @date   2021/1/6
     * @param  account
     */
    void addConfigAccount(ConfigAccount account);

    /**
     * 收款账号列表
     * @author LeGreen
     * @date   2021/4/21
     * @param  type
     * @param  page
     * @param  pageSize
     * @return java.util.List<com.chengyu.core.model.ConfigAccount>
     */
    List<ConfigAccount> getAccountList(Integer type, Integer page, Integer pageSize);

    /**
     * 删除
     * @author LeGreen
     * @date   2021/4/21
     * @param  id
     */
    void deleteAccount(Integer id);

    /**
     * 更新收款金额
     * @author LeGreen
     * @date   2021/4/21
     * @param  accountId
     * @param  amount
     * @param  isUpdateToday
     */
    void updateAmount(Integer accountId, BigDecimal amount, boolean isUpdateToday);

    /**
     * 清除今日收款额度
     * @author LeGreen
     * @date   2021/4/21
     */
    void clearTodayIncome();
}
