package com.chengyu.core.service.member;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberViewGoods;

import java.util.Date;
import java.util.List;

/**
 * @title  浏览商品
 * @author LeGreen
 * @date   2022/4/28
 */
public interface MemberViewGoodsService {

    /**
     * 商品浏览列表
     * @author LeGreen
     * @date   2022/4/28
     * @param  memberId
     * @param  date
     * @param  page
     * @param  pageSize
     * @return List<UmsMemberViewGoods>
     */
    List<UmsMemberViewGoods> getMemberViewGoodsList(Integer memberId, Date date, Integer page, Integer pageSize);

    /**
     * 浏览商品
     * @author LeGreen
     * @date   2022/4/28
     * @param  member
     * @param  goodsId
     */
    void viewGoods(UmsMember member, Integer goodsId);

    /**
     * 删除浏览商品
     * @author LeGreen
     * @date   2022/4/28
     * @param  viewGoodsId
     */
    void deleteViewGoods(Integer viewGoodsId);

    /**
     * 删除用户的浏览记录
     * @author LeGreen
     * @date   2022/4/28
     * @param  memberId
     */
    void deleteViewGoodsByMemberId(Integer memberId);
}
