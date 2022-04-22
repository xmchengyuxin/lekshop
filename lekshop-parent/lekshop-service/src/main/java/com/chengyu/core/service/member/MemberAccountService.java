package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberGroup;

/**
 * @title  会员账户
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberAccountService {

    /**
     * 初始化账户
     * @author LeGreen
     * @date   2021/1/7
     * @param  member
     * @param  memberGroup
     * @throws ServiceException 业务异常
     */
    void initAccount(UmsMember member, UmsMemberGroup memberGroup) throws ServiceException;

    /**
     * @author LeGreen
     * 获取账户
     * @date   2021/1/7
     * @param  memberId
     * @return com.chengyu.core.model.UmsMemberAccount
     */
    UmsMemberAccount getMemberAccount(Integer memberId);
}
