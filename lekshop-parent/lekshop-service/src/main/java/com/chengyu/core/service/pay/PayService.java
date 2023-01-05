package com.chengyu.core.service.pay;

import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;

/**
 * @title  支付接口
 * @author LeGreen
 * @date   2022/6/9
 */
public interface PayService {

    /**充值支付回调**/
    public static String ALIPAY_NOTIFY_URL_RECHARGE = "/common/recharge/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_RECHARGE = "/common/recharge/wxPay/paySus";
    /**订单支付回调**/
    public static String ALIPAY_NOTIFY_URL_ORDER = "/common/order/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_ORDER = "/common/order/wxPay/paySus";
    /**订单退款回调**/
    public static String ALIPAY_NOTIFY_URL_REFUND = "/common/refund/aliPay/callback";
    public static String WXPAY_NOTIFY_URL_REFUND = "/common/refund/wxPay/callback";
    /**订单取消回调**/
    public static String ALIPAY_NOTIFY_URL_CANCEL_ORDER = "/common/cancelOrder/aliPay/callback";
    public static String WXPAY_NOTIFY_URL_CANCEL_ORDER = "/common/cancelOrder/wxPay/callback";
    /**积分商城订单支付回调**/
    public static String ALIPAY_NOTIFY_URL_POINT_ORDER = "/common/pointOrder/aliPay/paySus";
    public static String WXPAY_NOTIFY_URL_POINT_ORDER = "/common/pointOrder/wxPay/paySus";

    /**
     * 支付商品订单
     * @author LeGreen
     * @date   2022/6/9
     * @param  member
     * @param  payOrderNo
     * @param  baseForm
     * @return String
     * @throws Exception 业务异常
     */
    String orderPay(UmsMember member, String payOrderNo, PayBaseForm baseForm) throws Exception;

    /**
     * 充值
     * @author LeGreen
     * @date   2022/6/9
     * @param  member
     * @param  orderNo
     * @param  baseForm
     * @return String
     * @throws Exception 业务异常
     */
    String recharge(UmsMember member, String orderNo, PayBaseForm baseForm) throws Exception;

    /**
     * 售后退款
     * @author LeGreen
     * @date   2022/6/9
     * @param  refundNo
     * @throws Exception 业务异常
     */
    void refund(String refundNo) throws ServiceException;

    /**
     * 取消订单并原路退款
     * @author LeGreen
     * @date   2022/6/9
     * @param  orderNo
     * @throws Exception 业务异常
     */
    void cancelAndRefundOrder(String orderNo) throws ServiceException;

    /**
     * 支付积分商城订单
     * @author LeGreen
     * @date   2023/1/5
     * @param  member
     * @param  payOrderNo
     * @param  payBaseForm
     * @return String
     */
    String pointOrderPay(UmsMember member, String payOrderNo, PayBaseForm payBaseForm) throws Exception;
}