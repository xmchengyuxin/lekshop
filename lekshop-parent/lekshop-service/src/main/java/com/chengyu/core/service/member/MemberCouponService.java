package com.chengyu.core.service.member;

import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberCoupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @title  用户优惠券
 * @author LeGreen
 * @date   2022/4/28
 */
public interface MemberCouponService {

    /**
     * 优惠券列表
     * @author LeGreen
     * @date   2022/4/28
     * @param  memberId
     * @param  status
     * @param  page
     * @param  pageSize
     * @return List<UmsMemberCoupon>
     */
    List<UmsMemberCoupon> getMemberCouponList(Integer memberId, Integer status, Integer page, Integer pageSize);

    /**
     * 使用优惠券
     * @author LeGreen
     * @date   2022/4/28
     * @param  member 用户
     * @param  couponId 优惠券ID
     * @param  totalAmount 订单金额
     * @param  goodsIdList 商品集合
     * @param  goodsCateIdList 商品分类集合
     * @return BigDecimal 优惠金额
     * @throws ServiceException 业务异常
     */
    BigDecimal validateCoupon(UmsMember member, Integer couponId, BigDecimal totalAmount, List<Integer> goodsIdList, List<Integer> goodsCateIdList) throws ServiceException;

    /**
     * 使用优惠券
     * @author LeGreen
     * @date   2022/5/5
     * @param  couponId
     * @throws ServiceException 业务异常
     */
    void useCoupon(Integer couponId) throws ServiceException;

    /**
     * 返回优惠券
     * @author LeGreen
     * @date   2022/4/28
     * @param  member
     * @param  couponId
     * @throws ServiceException 业务异常
     */
    void backCoupon(UmsMember member, Integer couponId) throws ServiceException;

    /**
     * 统计未使用优惠券
     * @author LeGreen
     * @date   2022/4/28
     * @param  memberId
     * @return Long
     */
    Long countUnUseCoupon(Integer memberId);

   /**
    * 获取可用的优惠券
    * @author LeGreen
    * @date   2022/5/19
    * @param  member
    * @param  shopId
    * @param  goodsIds
    * @param  totalAmount
    * @return List<UmsMemberCoupon>
    */
   List<Map<String,Object>> getCanUseCouponList(UmsMember member, Integer shopId, String goodsIds, String cateIds, BigDecimal totalAmount);
}
