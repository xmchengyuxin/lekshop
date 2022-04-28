package com.chengyu.core.service.member;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberCollectionGoods;

import java.util.List;

/**
 * @title  收藏商品
 * @author LeGreen
 * @date   2022/4/28
 */
public interface MemberCollectGoodsService {

    /**
     * 商品收藏列表
     * @author LeGreen
     * @date   2022/4/28
     * @param  memberId
     * @param  page
     * @param  pageSize
     * @return List<UmsMemberCollectionGoods>
     */
    List<UmsMemberCollectionGoods> getMemberCollectionGoodsList(Integer memberId, Integer page, Integer pageSize);

    /**
     * 收藏或取消收藏
     * @author LeGreen
     * @date   2022/4/28
     * @param  member
     * @param  goodsId
     */
    void collectOrNoGoods(UmsMember member, Integer goodsId);

    /**
     * 是否收藏商品
     * @author LeGreen
     * @date   2022/4/28
     * @param  member
     * @param  goodsId
     * @return boolean
     */
    boolean isCollectGoods(UmsMember member, Integer goodsId);

    /**
     * 删除收藏的商品
     * @author LeGreen
     * @date   2022/4/28
     * @param  collectGoodsId
     */
    void deleteCollectionGoods(Integer collectGoodsId);

    /**
     * 删除该商品下所有的收藏
     * @author LeGreen
     * @date   2022/4/28
     * @param  goodsId
     */
    void deleteCollectionByGoodsId(Integer goodsId);
}
