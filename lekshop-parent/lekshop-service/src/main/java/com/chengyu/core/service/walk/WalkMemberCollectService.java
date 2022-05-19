package com.chengyu.core.service.walk;

import com.chengyu.core.model.WalkMember;
import com.chengyu.core.model.WalkMemberCollection;

import java.util.List;

/**
 * @title  关注创作人
 * @author LeGreen
 * @date   2022/5/19
 */
public interface WalkMemberCollectService {

    /**
     * 创作人收藏列表
     * @author LeGreen
     * @date   2022/4/28
     * @param  viewWalkMemberId
     * @param  page
     * @param  pageSize
     * @return List<WalkMemberCollection>
     */
    List<WalkMemberCollection> getWalkMemberCollectionList(Integer viewWalkMemberId, Integer walkMemberId, Integer page, Integer pageSize);

    /**
     * 收藏或取消收藏
     * @author LeGreen
     * @date   2022/4/28
     * @param  walkMember
     * @param  walkMemberId
     */
    void collectOrNoWalkMember(WalkMember walkMember, Integer walkMemberId);

    /**
     * 是否收藏创作人
     * @author LeGreen
     * @date   2022/4/28
     * @param  walkMember
     * @param  walkMemberId
     * @return boolean
     */
    boolean isCollectWalkMember(WalkMember walkMember, Integer walkMemberId);

    /**
     * 删除收藏的创作人
     * @author LeGreen
     * @date   2022/4/28
     * @param  collectId
     */
    void deleteCollectionWalkMember(Integer collectId);
}
