package com.chengyu.core.service.member;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberCollectionShop;

import java.util.List;

/**
 * @title  收藏店铺
 * @author LeGreen
 * @date   2022/4/28
 */
public interface MemberCollectShopService {

    /**
     * 店铺收藏列表
     * @author LeGreen
     * @date   2022/4/28
     * @param  memberId
     * @param  page
     * @param  pageSize
     * @return List<UmsMemberCollectionShop>
     */
    List<UmsMemberCollectionShop> getMemberCollectionShopList(Integer memberId, Integer page, Integer pageSize);

    /**
     * 收藏或取消收藏
     * @author LeGreen
     * @date   2022/4/28
     * @param  member
     * @param  shopId
     */
    void collectOrNoShop(UmsMember member, Integer shopId);

    /**
     * 是否收藏店铺
     * @author LeGreen
     * @date   2022/4/28
     * @param  member
     * @param  shopId
     * @return boolean
     */
    boolean isCollectShop(UmsMember member, Integer shopId);

    /**
     * 删除收藏的店铺
     * @author LeGreen
     * @date   2022/4/28
     * @param  collectShopId
     */
    void deleteCollectionShop(Integer collectShopId);

    /**
     * 删除该店铺下所有的收藏
     * @author LeGreen
     * @date   2022/4/28
     * @param  shopId
     */
    void deleteCollectionByShopId(Integer shopId);
}
