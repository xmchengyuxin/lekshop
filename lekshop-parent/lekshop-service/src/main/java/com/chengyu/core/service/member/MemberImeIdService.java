package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberImeid;

import java.util.List;

/**
 * 设备Id
 * @author LeGreen
 * @date   2021/9/13
 */
public interface MemberImeIdService {

    /**
     * 登录设备记录
     * @author LeGreen
     * @date   2021/9/14
     * @param  member
     * @param  imeId
     * @param  phoneType
     * @throws ServiceException 业务异常
     */
    void loginByImeId(UmsMember member, String imeId, String phoneType) throws ServiceException;

    /**
     * 设备是否被登录过
     * @author LeGreen
     * @date   2021/9/13
     * @param  imeId
     * @return long
     */
    boolean isLoginByImeId(Integer memberId, String imeId);

    /**
     * 获取所有的设备
     * @author LeGreen
     * @date   2021/9/13
     * @param  memberId
     * @return java.util.List<com.chengyu.core.model.UmsMemberImeid>
     */
    List<UmsMemberImeid> getAllImeid(Integer memberId);

    /**
     * 删除登录设备
     * @author LeGreen
     * @date   2021/9/13
     * @param  imeId
     */
    void deleteImeid(Integer imeId);
}
