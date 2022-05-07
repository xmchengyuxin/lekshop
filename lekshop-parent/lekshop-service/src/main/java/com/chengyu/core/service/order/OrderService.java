package com.chengyu.core.service.order;

import com.chengyu.core.domain.form.OrderAddForm;
import com.chengyu.core.domain.form.OrderDeliveryForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderPayResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAddress;
import com.chengyu.core.model.UmsShop;

import java.util.List;

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
}