package com.chengyu.core.service.point;

import com.chengyu.core.domain.form.OrderDeliveryForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.PointOrderPayResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrder;
import com.chengyu.core.model.PointOrder;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAddress;

import java.util.List;

/**
 * @title  积分商城订单
 * @author LeGreen
 * @date   2023/1/4
 */
public interface PointOrderService {

    /**
     * 订单列表
     * @author LeGreen
     * @date   2023/1/4
     * @param  form 查询表单
     * @param  page 分页
     * @param  pageSize 分页
     * @return List<PointOrder>
     */
    List<PointOrder> getOrderList(OrderSearchForm form, Integer page, Integer pageSize);

    /**
     * 订单详情
     * @author LeGreen
     * @date   2023/1/4
     * @param  orderId 订单ID
     * @return PointOrder
     */
    PointOrder getOrder(Integer orderId);

    /**
     * 提交订单
     * @author LeGreen
     * @date   2023/1/4
     * @param  member 购买用户
     * @param  address 地址
     * @param  goodsId 兑换商品
     * @param  buyNum 兑换数量
     * @return PointOrderPayResult
     * @throws ServiceException 业务异常
     */
    PointOrderPayResult addOrder(UmsMember member, UmsMemberAddress address, Integer goodsId, Integer buyNum) throws ServiceException;

    /**
     * 支付成功
     * @author LeGreen
     * @date   2023/1/4
     * @param  orderNo
     */
    void paySus(String orderNo) throws ServiceException;

    /**
     * 批量发货
     * @author LeGreen
     * @date   2023/1/4
     * @param  list
     * @return String
     * @throws ServiceException 业务异常
     */
    String delivery(List<OrderDeliveryForm> list) throws ServiceException;

    /**
     * 确认收货
     * @author LeGreen
     * @date   2023/1/4
     * @param  member
     * @param  orderId
     * @throws ServiceException 业务异常
     */
    void confirmOrder(UmsMember member, Integer orderId) throws ServiceException;

    /**
     * 完成订单
     * @author LeGreen
     * @date   2022/9/20
     * @param  order 订单
     * @throws ServiceException 业务异常
     */
    void finishOrder(PointOrder order) throws ServiceException;

    /**
     * 修改订单地址
     * @author LeGreen
     * @date   2023/1/4
     * @param  member
     * @param  orderId
     * @param  addressId
     * @throws ServiceException 业务异常
     */
    void updateOrderAddress(UmsMember member, Integer orderId, Integer addressId) throws ServiceException;

    /**
     * 取消待支付订单
     * @author LeGreen
     * @date   2023/1/4
     * @param  member
     * @param  orderId
     * @throws ServiceException 业务异常
     */
    void cancelOrder(UmsMember member, Integer orderId) throws ServiceException;

    /**
     * 删除交易关闭订单
     * @author LeGreen
     * @date   2023/1/4
     * @param  member
     * @param  orderId
     * @throws ServiceException 业务异常
     */
    void deleteOrder(UmsMember member, Integer orderId) throws ServiceException;

    /**
     * 订单超时未支付自动取消
     * @author LeGreen
     * @date   2022/5/5
     * @param  orderNo
     */
    void autoCancelOrder(String orderNo);

    /**
     * 订单自动确认收货
     * @author LeGreen
     * @date   2022/5/5
     * @param  orderNo
     */
    void autoFinishOrder(String orderNo) throws ServiceException;

    /**
     * 总后台删除订单
     * @author LeGreen
     * @date   2022/5/7
     * @param  idList
     */
    void deleteOrderByAdmin(List<Integer> idList);

    /**
     * 更新订单的支付方式和支付渠道
     * @author LeGreen
     * @date   2022/6/9
     * @param  orderNo
     * @param  payMethod
     * @param  applicationType
     */
    void updateOrderPayMethod(String orderNo, String payMethod, String applicationType);

    /**
     * 根据订单号查询订单
     * @author LeGreen
     * @date   2022/6/9
     * @param  orderNo
     * @return OmsOrder
     */
    PointOrder getOrderByOrderNo(String orderNo);

    /**
     * 取消订单成功
     * @author LeGreen
     * @date   2022/6/9
     * @param  order
     * @throws ServiceException 业务异常
     */
    void cancelAndRefundOrderSus(PointOrder order);

}