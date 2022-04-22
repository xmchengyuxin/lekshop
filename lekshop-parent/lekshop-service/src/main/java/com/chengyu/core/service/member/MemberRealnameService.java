package com.chengyu.core.service.member;

import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRealname;

import java.util.List;

/**
 * @title  会员实名认证
 * @author LeGreen
 * @date   2021/1/7
 */
public interface MemberRealnameService {

    /**
     * 查询实名认证信息
     * @author LeGreen
     * @date   2021/1/7
     * @param  page
     * @param  pageSize
     * @return java.util.List<com.chengyu.core.model.UmsMemberRealname>
     */
    List<UmsMemberRealname> getMemberRealnameList(BaseSearchForm form, Integer page, Integer pageSize);

    /**
     * 获取用户已认证的实名信息
     * @author LeGreen
     * @date   2021/1/7
     * @param  memberId
     * @return com.chengyu.core.model.UmsMemberRealname
     */
    UmsMemberRealname getMemberRealnameByMemberId(Integer memberId);

    /**
     * 删除实名
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     */
    void deleteMemberRealname(Integer id);

    /**
     * 认证通过
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     * @throws ServiceException 业务异常
     */
    void verifyMemberRealnameSus(Integer id) throws ServiceException;

    /**
     * 认证不通过
     * @author LeGreen
     * @date   2021/1/7
     * @param  id
     * @throws ServiceException 业务异常
     */
    void verifyMemberRealnameFail(Integer id, String remark) throws ServiceException;

    /**
     * 是否已实名
     * @author LeGreen
     * @date   2021/1/8
     * @param  memberId
     * @return boolean
     */
    boolean validateRealname(Integer memberId);

    /**
     * 实名认证
     * @author LeGreen
     * @date   2021/1/8
     * @param  member
     * @param  realname
     * @param  idCard
     * @param  idcardFace
     * @param  idcardBack
     * @param  idcardFaceHand
     * @throws ServiceException 业务异常
     */
    UmsMemberRealname applyRealname(UmsMember member, String realname, String idCard, String idcardFace, String idcardBack, String idcardFaceHand) throws ServiceException;

    /**
     * 修改实名认证
     * @author LeGreen
     * @date   2021/1/22
     * @param  realname
     * @throws ServiceException 业务异常
     */
    void update(UmsMemberRealname realname) throws ServiceException;
}
