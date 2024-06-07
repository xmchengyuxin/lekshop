package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.CaculateFreightFeeForm;
import com.chengyu.core.domain.form.OrderAddForm;
import com.chengyu.core.domain.form.OrderDeliveryForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderPayResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @title  订单
 * @author LeGreen
 * @date   2022/4/29
 */
public interface OrderService {

    /**
     * 订单列表
     * @author LeGreen
     * @date   2022/4/29
     * @param  form
     * @param  page
     * @param  pageSize
     * @return CommonPage<OrderResult>
     */
    CommonPage<OrderResult> getOrderList(OrderSearchForm form, Integer page, Integer pageSize);

    /**
     * 订单详情
     * @author LeGreen
     * @date   2022/4/29
     * @param  orderId
     * @return OrderResult
     */
    OrderResult getOrder(Integer orderId);

    /**
     * 提交订单
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  address
     * @param  addFormList
     * @return OrderPayResult
     * @throws ServiceException 业务异常
     */
    OrderPayResult addOrder(UmsMember member, UmsMemberAddress address, List<OrderAddForm> addFormList) throws ServiceException;

    /**
     * 计算运费
     * @author LeGreen
     * @date   2022/5/16
     * @param  shopId
     * @param  address
     * @param  freightFeeFormList
     * @return BigDecimal
     */
    BigDecimal caculateFreightFee(Integer shopId, UmsMemberAddress address, List<CaculateFreightFeeForm> freightFeeFormList);

    /**
     * 支付成功
     * @author LeGreen
     * @date   2022/4/29
     * @param  payOrderNo
     */
    void paySus(String payOrderNo) throws ServiceException;

    /**
     * 批量发货
     * @author LeGreen
     * @date   2022/4/29
     * @param  list
     * @return String
     * @throws ServiceException 业务异常
     */
    String delivery(UmsShop shop, List<OrderDeliveryForm> list) throws ServiceException;

    /**
     * 确认收货
     * @author LeGreen
     * @date   2022/4/29
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
    void finishOrder(OmsOrder order) throws ServiceException;

    /**
     * 修改订单地址
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  orderId
     * @param  addressId
     * @throws ServiceException 业务异常
     */
    void updateOrderAddress(UmsMember member, Integer orderId, Integer addressId) throws ServiceException;

    /**
     * 取消待支付订单
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  orderId
     * @throws ServiceException 业务异常
     */
    void cancelOrder(UmsMember member, Integer orderId) throws ServiceException;

    /**
     * 删除交易关闭订单
     * @author LeGreen
     * @date   2022/4/29
     * @param  member
     * @param  orderId
     * @throws ServiceException 业务异常
     */
    void deleteOrder(UmsMember member, Integer orderId) throws ServiceException;

    /**
     * 订单超时未支付自动取消
     * @author LeGreen
     * @date   2022/5/5
     * @param  payOrderNo
     */
    void autoCancelOrder(String payOrderNo);

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
     * 查询已购买的商品列表
     * @author LeGreen
     * @date   2022/5/24
     * @param  memberId
     * @return List<OmsOrderDetail>
     */
    List<OmsOrderDetail> getBuyGoodsList(Integer memberId, String goodsName);

    /**
     * 统计待发货，待收货，待评价，退款数量
     * @author LeGreen
     * @date   2022/6/2
     * @param  memberId
     * @return Map<String,Object>
     * @throws ServiceException 业务异常
     */
    Map<String, Object> count(Integer memberId);

    /**
     * 获取支付订单
     * @author LeGreen
     * @date   2022/6/9
     * @param  payOrderNo
     * @return OmsPayOrder
     */
    OmsPayOrder getPayOrder(String payOrderNo);

    /**
     * 更新订单的支付方式和支付渠道
     * @author LeGreen
     * @date   2022/6/9
     * @param  payOrderNo
     * @param  payMethod
     * @param  applicationType
     */
    void updateOrderPaymethod(String payOrderNo, String payMethod, String applicationType);

    /**
     * 查询订单
     * @author LeGreen
     * @date   2022/6/9
     * @param  orderId
     * @return OmsOrder
     */
    OmsOrder getOrderById(Integer orderId);

    /**
     * 根据订单号查询订单
     * @author LeGreen
     * @date   2022/6/9
     * @param  orderNo
     * @return OmsOrder
     */
    OmsOrder getOrderByOrderNo(String orderNo);

    /**
     * 取消订单成功
     * @author LeGreen
     * @date   2022/6/9
     * @param  order
     * @throws ServiceException 业务异常
     */
    void cancelAndRefundOrderSus(OmsOrder order);

    /**
     * 查询订单详情
     * @author LeGreen
     * @date   2022/6/16
     * @param  form
     * @param  page
     * @param  pageSize
     * @return List<OmsOrderDetail>
     */
    List<OmsOrderDetail> getOrderDetailList(OrderSearchForm form, Integer page, Integer pageSize);
}