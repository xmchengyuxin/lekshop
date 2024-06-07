package com.chengyu.core.service.pay.impl;

import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.enums.PayEnum;
import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.form.RefundBaseForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.*;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.service.member.MemberRechargeService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.order.OrderRefundService;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.pay.PayService;
import com.chengyu.core.service.pay.manager.PayManager;
import com.chengyu.core.service.point.PointOrderService;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayManager payManager;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberRechargeService rechargeService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PointOrderService pointOrderService;
    @Autowired
    private MemberAccountService memberAccountService;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public String orderPay(UmsMember member, String payOrderNo, PayBaseForm baseForm) throws Exception {
        OmsPayOrder payOrder = orderService.getPayOrder(payOrderNo);
        if(payOrder == null || payOrder.getStatus() != CommonConstant.WAIT_INT){
            throw new ServiceException("status.error");
        }
        orderService.updateOrderPaymethod(payOrderNo, baseForm.getPayMethod(), baseForm.getApplicationType());
        baseForm.setBody("订单支付-"+payOrderNo);
        if(PayEnum.PayMethod.WX_PAY.getValue().equals(baseForm.getPayMethod())){
            baseForm.setPayNotifyUrl(PayService.WXPAY_NOTIFY_URL_ORDER);
        }else if(PayEnum.PayMethod.ALI_PAY.getValue().equals(baseForm.getPayMethod())){
            baseForm.setPayNotifyUrl(PayService.ALIPAY_NOTIFY_URL_ORDER);
        }else if(PayEnum.PayMethod.BALANCE.getValue().equals(baseForm.getPayMethod())){
            baseForm.setMemberAccountTypes(AccountEnums.MemberAccountTypes.ACCOUNT_TRADE_OUT);
            baseForm.setBody("购买商品");
        }
        String message = payManager.getPayFactory(baseForm.getPayMethod()).pay(member, payOrderNo, payOrder.getAmount(), baseForm);
        if("noneedpay".equals(message)){
            orderService.paySus(payOrderNo);
        }
        return message;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public String recharge(UmsMember member, String orderNo, PayBaseForm baseForm) throws Exception {
        UmsMemberRecharge recharge = rechargeService.getRechargeLogByOrderNo(orderNo);
        if(recharge == null || !CommonConstant.WAIT.equals(recharge.getStatus())){
            throw new ServiceException("status.error");
        }
        baseForm.setBody("充值-"+orderNo);
        if(PayEnum.PayMethod.WX_PAY.getValue().equals(baseForm.getPayMethod())){
            baseForm.setPayNotifyUrl(PayService.WXPAY_NOTIFY_URL_RECHARGE);
        }else if(PayEnum.PayMethod.ALI_PAY.getValue().equals(baseForm.getPayMethod())){
            baseForm.setPayNotifyUrl(PayService.ALIPAY_NOTIFY_URL_RECHARGE);
        }else if(PayEnum.PayMethod.BALANCE.getValue().equals(baseForm.getPayMethod())){
            throw new ServiceException("status.error");
        }
        return payManager.getPayFactory(baseForm.getPayMethod()).pay(member, orderNo, recharge.getRealAmount(), baseForm);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public void refund(String refundNo) throws ServiceException {
        OmsOrderRefund refund = orderRefundService.getOrderRefundByRefundNo(refundNo);
        //查询出原订单
        OmsOrder order = orderService.getOrderById(refund.getOrderId());
        OmsPayOrder payOrder = orderService.getPayOrder(order.getPayOrderNo());

        RefundBaseForm refundBaseForm = new RefundBaseForm();
        refundBaseForm.setOrderNo(order.getPayOrderNo());
        refundBaseForm.setRefundNo(refund.getRefundNo());
        refundBaseForm.setOrderAmount(payOrder.getAmount());
        refundBaseForm.setRefundAmount(refund.getRefundAmount());
        if(PayEnum.PayMethod.BALANCE.getValue().equals(payOrder.getPayMethod())){
            refundBaseForm.setMember(memberService.getMemberById(payOrder.getMemberId()));
        }
        refundBaseForm.setPayChannel(payOrder.getPayChannel());
        //仅微信需要回调
        refundBaseForm.setRefundNotifyUrl(PayService.WXPAY_NOTIFY_URL_REFUND);
        try {
            String message = payManager.getPayFactory(refund.getPayMethod()).refund(refundBaseForm);
            if(StringUtils.isNotBlank(message) && CommonConstant.SUCCESS.equals(message)){
                orderRefundService.refundSusCallback(refund);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public void cancelAndRefundOrder(String orderNo) throws ServiceException {
        OmsOrder order = orderService.getOrderByOrderNo(orderNo);
        if(order.getStatus() != OrderEnums.OrderStatus.WAIT_DELIVERY.getValue()){
            throw new ServiceException("status.error");
        }

        RefundBaseForm refundBaseForm = new RefundBaseForm();
        refundBaseForm.setOrderNo(order.getPayOrderNo());
        refundBaseForm.setRefundNo(order.getOrderNo());
        refundBaseForm.setOrderAmount(order.getPayPrice());
        refundBaseForm.setRefundAmount(order.getPayPrice());
        if(PayEnum.PayMethod.BALANCE.getValue().equals(order.getPayMethod())){
            refundBaseForm.setMember(memberService.getMemberById(order.getMemberId()));
        }
        refundBaseForm.setPayChannel(order.getPayChannel());
        //仅微信需要回调
        refundBaseForm.setRefundNotifyUrl(PayService.WXPAY_NOTIFY_URL_CANCEL_ORDER);
        try {
            String message = payManager.getPayFactory(order.getPayMethod()).refund(refundBaseForm);
            if(StringUtils.isNotBlank(message) && CommonConstant.SUCCESS.equals(message)){
                orderService.cancelAndRefundOrderSus(order);
            }
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public String pointOrderPay(UmsMember member, String payOrderNo, PayBaseForm baseForm) throws Exception {
        PointOrder payOrder = pointOrderService.getOrderByOrderNo(payOrderNo);
        if(payOrder == null || payOrder.getStatus() != CommonConstant.WAIT_INT){
            throw new ServiceException("status.error");
        }
        //校验积分是否足够
        UmsMemberAccount memberAccount = memberAccountService.getMemberAccount(member.getId());
        if(memberAccount.getPoint().compareTo(payOrder.getBuyTotalPoint()) < 0) {
            throw new ServiceException("member.point.noenough");
        }
        String message = "noneedpay";
        //需要现金支付
        if(payOrder.getBuyTotalPrice().compareTo(BigDecimal.ZERO) > 0) {
            pointOrderService.updateOrderPayMethod(payOrderNo, baseForm.getPayMethod(), baseForm.getApplicationType());
            baseForm.setBody("积分商城订单支付-"+payOrderNo);
            if(PayEnum.PayMethod.WX_PAY.getValue().equals(baseForm.getPayMethod())){
                baseForm.setPayNotifyUrl(PayService.WXPAY_NOTIFY_URL_POINT_ORDER);
            }else if(PayEnum.PayMethod.ALI_PAY.getValue().equals(baseForm.getPayMethod())){
                baseForm.setPayNotifyUrl(PayService.ALIPAY_NOTIFY_URL_POINT_ORDER);
            }else if(PayEnum.PayMethod.BALANCE.getValue().equals(baseForm.getPayMethod())){
                baseForm.setMemberAccountTypes(AccountEnums.MemberAccountTypes.ACCOUNT_TRADE_OUT);
                baseForm.setBody("兑换商品");
            }
            message = payManager.getPayFactory(baseForm.getPayMethod()).pay(member, payOrderNo, payOrder.getBuyTotalPrice(), baseForm);
        }
        if("noneedpay".equals(message)){
            pointOrderService.paySus(payOrderNo);
        }
        return message;
    }
}