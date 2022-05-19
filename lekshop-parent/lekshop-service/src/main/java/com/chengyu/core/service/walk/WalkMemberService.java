package com.chengyu.core.service.walk;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.WalkMember;
import org.quartz.Job;

import java.util.Date;

/**
 * @title  创作号
 * @author LeGreen
 * @date   2022/5/19
 */
public interface WalkMemberService {

    /**
     * 添加创作号
     * @author LeGreen
     * @date   2022/5/19
     * @param  member
     */
    void addWalkMember(UmsMember member);

    /**
     * 更新创作号
     * @author LeGreen
     * @date   2022/5/19
     * @param  walkMember
     */
    void updateWalkMember(WalkMember walkMember);

    /**
     * 获取用户的创作号
     * @author LeGreen
     * @date   2022/5/19
     * @param  memberId
     * @return WalkMember
     */
    WalkMember getWalkMemberByMember(Integer memberId);
}
