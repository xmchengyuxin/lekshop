package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.OrderRefundForm;
import com.chengyu.core.domain.form.OrderRefundSearchForm;
import com.chengyu.core.domain.result.OrderRefundResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShop;

import java.util.List;

/**
 * @title  订单售后
 * @author LeGreen
 * @date   2022/4/29
 */
public interface OrderRefundService {

    /**
     * 退款列表
     * @author LeGreen
     * @date   2022/4/29
     * @param  form
     * @param  page
     * @param  pageSize
     * @return List<OmsOrderRefund>
     */
    List<OmsOrderRefund> getRefundList(OrderRefundSearchForm form, Integer page, Integer pageSize);

    /**
     * 退款详情
     * @author LeGreen
     * @date   2022/4/29
     * @param  refundId
     * @return OrderRefundResult
     */
    OrderRefundResult getOrderRefund(Integer refundId);
    
    /**
     * 申请退款
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  form
     * @throws ServiceException 业务异常
     */
    void applyRefund(UmsMember member, OrderRefundForm form) throws ServiceException;

    /**
     * 同意退款
     * @author LeGreen
     * @date   2022/4/29
     * @param  shop
     * @param  refundId
     * @throws ServiceException 业务异常
     */
    void agreeRefund(UmsShop shop, Integer refundId) throws ServiceException;

    /**
     * 拒绝退款
     * @author LeGreen
     * @date   2022/4/29
     * @param  shop
     * @param  refundId
     * @param  reason
     * @throws ServiceException 业务异常
     */
    void refuseRefund(UmsShop shop, Integer refundId, String reason) throws ServiceException;

    /**
     * 申请客服介入
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  refundId
     * @throws ServiceException 业务异常
     */
    void applyCustomerServiceIn(UmsMember member, Integer refundId) throws ServiceException;

    /**
     * 平台客服同意退款
     * @author LeGreen
     * @date   2022/4/29
     * @param  admin
     * @param  refundId
     * @throws ServiceException 业务异常
     */
    void agreeRefundByCustomer(SysAdmin admin, Integer refundId) throws ServiceException;

    /**
     * 平台客服判定拒绝退款，交易关闭
     * @author LeGreen
     * @date   2022/4/29
     * @param  admin
     * @param  refundId
     * @param  reason
     * @throws ServiceException 业务异常
     */
    void refuseRefundByCustomer(SysAdmin admin, Integer refundId, String reason) throws ServiceException;

    /**
     * 买家进行退货
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  refundId
     * @param  deliveryType
     * @param  deliveryNo
     * @throws ServiceException 业务异常
     */
    void salesReturnByMember(UmsMember member, Integer refundId, String deliveryType, String deliveryNo) throws ServiceException;

    /**
     * 卖家确认收货,退款完成
     * @author LeGreen
     * @date   2022/4/29
     * @param  shop
     * @param  refundId
     * @throws ServiceException 业务异常
     */
    void confirmReceiveByShop(UmsShop shop, Integer refundId) throws ServiceException;

    /**
     * 卖家超时未处理售后,自动同意
     * @author LeGreen
     * @date   2022/5/6
     * @param  refundNo
     */
    void autoAgreeRefund(String refundNo) throws ServiceException;

    /**
     * 买家超时未寄货，自动取消
     * @author LeGreen
     * @date   2022/5/6
     * @param  refundNo
     */
    void autoCancelRefund(String refundNo);

    /**
     * 卖家超时未确认收货,自动确认
     * @author LeGreen
     * @date   2022/5/6
     * @param  refundNo
     */
    void autoConfirmRefund(String refundNo);

    /**
     * 取消售后申请
     * @author LeGreen
     * @date   2022/5/16
     * @param  member
     * @param  refundId
     */
    void cancelRefund(UmsMember member, Integer refundId) throws ServiceException;

    /**
     * 退款详情
     * @author LeGreen
     * @date   2022/4/29
     * @param  detailId
     * @return OrderRefundResult
     */
    OrderRefundResult getOrderRefundByDetailId(Integer detailId);

    /**
     * 根据退款单号获取退款
     * @author LeGreen
     * @date   2022/6/9
     * @param  refundNo
     * @return OmsOrderRefund
     */
    OmsOrderRefund getOrderRefundByRefundNo(String refundNo);

    /**
     * 退款成功回调
     * @author LeGreen
     * @date   2022/6/9
     * @param  refund
     */
    void refundSusCallback(OmsOrderRefund refund);
}