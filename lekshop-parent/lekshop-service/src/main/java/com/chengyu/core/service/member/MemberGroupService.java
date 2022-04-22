package com.chengyu.core.service.member;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberGroup;

import java.util.List;

/**
 * @title  会员组别设置
 * @author LeGreen
 * @date   2021/1/6
 */
public interface MemberGroupService {

    /**
     * 会员组别分页
     * @author LeGreen
     * @date   2021/1/6
     * @param  type
     * @param  page
     * @param  pageSize
     * @return java.util.List<com.chengyu.core.model.UmsMemberGroup>
     */
    List<UmsMemberGroup> getMemberGroupList(Integer type, Integer page, Integer pageSize);

    /**
     * 新增或修改会员组别
     * @author LeGreen
     * @date   2021/1/6
     * @param  group
     */
    void addMemberGroup(UmsMemberGroup group);

    /**
     * 删除会员组别
     * @author LeGreen
     * @date   2021/1/6
     * @param  groupId
     */
    void deleteGroup(Integer groupId);

    /**
     * 获取所有的买家组别
     * @author LeGreen
     * @date   2021/1/6
     * @return java.util.List<com.chengyu.core.model.UmsMemberGroup>
     */
    List<UmsMemberGroup> getAllBuyerGroupList();

    /**
     * 获取所有的卖家组别
     * @author LeGreen
     * @date   2021/1/6
     * @return java.util.List<com.chengyu.core.model.UmsMemberGroup>
     */
    List<UmsMemberGroup> getAllSellerGroupList();

    /**
     * 获取所有的会员组别
     * @author LeGreen
     * @date   2021/1/6
     * @return java.util.List<com.chengyu.core.model.UmsMemberGroup>
     */
    List<UmsMemberGroup> getAllGroupList();

    /**
     * 获取默认买家组
     * @author LeGreen
     * @date   2021/1/6
     * @return com.chengyu.core.model.UmsMemberGroup
     */
    UmsMemberGroup getDefaultBuyerGroup();

    /**
     * 获取默认卖家组
     * @author LeGreen
     * @date   2021/1/6
     * @return com.chengyu.core.model.UmsMemberGroup
     */
    UmsMemberGroup getDefaultSellerGroup();

    /**
     * 修改状态
     * @author LeGreen
     * @date   2021/1/15
     * @param  id
     * @param  status
     */
    void updateStatus(Integer id, Integer status);

    /**
     * 获取组别名称
     * @author LeGreen
     * @date   2021/1/15
     * @param  groupId
     * @return java.lang.String
     */
    String getGroupName(Integer groupId);

    /**
     * 根据ID获取会员组别
     * @author LeGreen
     * @date   2021/5/29
     * @param  groupId
     * @return com.chengyu.core.model.UmsMemberGroup
     */
    UmsMemberGroup getGroup(Integer groupId);

    /**
     * 会员组升级
     * @author LeGreen
     * @date   2021/9/2
     * @param  member
     */
    void upLevel(UmsMember member);
}
