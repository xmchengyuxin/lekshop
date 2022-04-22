package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberPunishment;

import java.util.List;

/**
 * @title  用户处罚
 * @author LeGreen
 * @date   2021/1/30
 */
public interface MemberPunishmentService {

    /**
     * 处罚列表
     * @author LeGreen
     * @date   2021/1/30
     * @param  memberId
     * @param  memberName
     * @param  status
     * @param  page
     * @param  pageSize
     * @return java.util.List<com.chengyu.core.model.UmsMemberPunishment>
     */
    List<UmsMemberPunishment> getPunishmentList(Integer memberId, String memberName, Integer status, Integer agentId, Integer page, Integer pageSize);

    /**
     * 加入处罚
     * @author LeGreen
     * @date   2021/1/30
     * @param  punishment
     */
    void addPunishment(UmsMemberPunishment punishment) throws ServiceException;

    /**
     * 删除处罚
     * @author LeGreen
     * @date   2021/1/30
     * @param  id
     */
    void deletePunishment(Integer id);

    /**
     * 判断是否正在处罚
     * @author LeGreen
     * @date   2021/1/30
     * @param  memberId
     * @return boolean
     */
    boolean isPunishmenting(Integer memberId);
}
