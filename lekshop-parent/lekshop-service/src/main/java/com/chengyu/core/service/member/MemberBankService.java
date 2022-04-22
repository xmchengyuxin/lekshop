package com.chengyu.core.service.member;

import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberBank;

import java.util.List;

/**
 * @title  会员银行卡认证
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberBankService {

    /**
     * 查询银行卡认证信息
     * @author LeGreen
     * @date   2021/1/7
     * @param  page
     * @param  pageSize
     * @return java.util.List<com.chengyu.core.model.UmsMemberBank>
     */
    List<UmsMemberBank> getMemberBankList(BaseSearchForm form, Integer page, Integer pageSize);

    /**
     * 获取用户已认证的银行卡信息
     * @author LeGreen
     * @date   2021/1/7
     * @param  memberId
     * @return com.chengyu.core.model.UmsMemberBank
     */
    UmsMemberBank getMemberBankByMemberId(Integer memberId);

    /**
     * 删除银行卡
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     */
    void deleteMemberBank(Integer id);

    /**
     * 认证通过
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     * @throws ServiceException 业务异常
     */
    void verifyMemberBankSus(Integer id) throws ServiceException;

    /**
     * 认证不通过
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     * @throws ServiceException 业务异常
     */
    void verifyMemberBankFail(Integer id, String reason) throws ServiceException;

    /**
     * 银行卡认证
     * @author LeGreen
     * @date   2021/1/8
     * @param  member
     * @param  memberBank
     * @throws ServiceException 业务异常
     */
    UmsMemberBank applyBank(UmsMember member, UmsMemberBank memberBank) throws ServiceException;

    /**
     * 修改银行认证
     * @author LeGreen
     * @date   2021/1/22
     * @param  memberBank
     * @throws ServiceException 业务异常
     */
    void update(UmsMemberBank memberBank) throws ServiceException;
}
