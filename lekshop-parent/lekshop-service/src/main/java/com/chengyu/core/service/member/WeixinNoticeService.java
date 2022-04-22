package com.chengyu.core.service.member;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberWithdraw;

/**
 * @title  微信通知
 * @author LeGreen
 * @date   2021/2/5
 */
public interface WeixinNoticeService {

    /**
     * 账号禁用
     * @author LeGreen
     * @date   2021/2/5
     * @param  member
     * @param  reason
     */
    void closeAccount(UmsMember member, String reason);

    /**
     * 提现成功通知
     * @author LeGreen
     * @date   2021/2/5
     * @param  withdraw
     */
    void withdrawSusNotice(UmsMemberWithdraw withdraw);

    /**
     * 提现失败通知
     * @author LeGreen
     * @date   2021/2/5
     * @param  withdraw
     */
    void withdrawFailNotice(UmsMemberWithdraw withdraw);

}
