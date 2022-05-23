package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsOrder implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "交易单号")
    private String payOrderNo;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "1普通商品>>2秒杀商品>>3拼团商品")
    private Integer type;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名")
    private String memberName;

    @ApiModelProperty(value = "用户昵称")
    private String memberNickname;

    @ApiModelProperty(value = "头像")
    private String memberHeadImg;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "商家LOGO")
    private String shopLogo;

    @ApiModelProperty(value = "0不使用优惠券 1使用优惠券")
    private Integer couponStatus;

    @ApiModelProperty(value = "优惠券ID")
    private Integer couponId;

    @ApiModelProperty(value = "优惠券抵用金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "运费")
    private BigDecimal freightFee;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "实际支付价格")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "下单时间")
    private Date buyTime;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "完成时间")
    private Date finishTime;

    @ApiModelProperty(value = "支付方式(wxPay微信 aliPay支付宝 balance余额支付)")
    private String payMethod;

    @ApiModelProperty(value = "支付渠道(app,xcx,gzh)")
    private String payChannel;

    @ApiModelProperty(value = "0待支付>>1待发货>>2待收货>>3已完成>>4已取消")
    private Integer status;

    @ApiModelProperty(value = "0未退款>>1退款中>>2部分退款>>3整单退款")
    private Integer refundStatus;

    @ApiModelProperty(value = "0未删除 1已删除")
    private Integer delInd;

    @ApiModelProperty(value = "收货姓名")
    private String receiveName;

    @ApiModelProperty(value = "收货手机")
    private String receivePhone;

    @ApiModelProperty(value = "X号码")
    private String receivePhoneX;

    @ApiModelProperty(value = "收货地址")
    private String receiveAddress;

    @ApiModelProperty(value = "经度")
    private String receiveLng;

    @ApiModelProperty(value = "纬度")
    private String receiveLat;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "物流方式")
    private String deliveryType;

    @ApiModelProperty(value = "第三方配送单号")
    private String deliveryNo;

    @ApiModelProperty(value = "是否需要发票")
    private Integer invoiceStatus;

    @ApiModelProperty(value = "超过这个时间未支付订单取消")
    private Date payEndTime;

    @ApiModelProperty(value = "超过这个时间无法评论订单")
    private Date commentExpiredTime;

    @ApiModelProperty(value = "自动确认收货时间")
    private Date finishExpiredTime;

    @ApiModelProperty(value = "售后截止时间")
    private Date refundExpiredTime;

    @ApiModelProperty(value = "拼团购买方式>>-1单独购买>>0普通拼团>>其他为阶梯拼团ID")
    private Integer groupId;

    @ApiModelProperty(value = "新单或复购单")
    private Integer newOrOld;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberHeadImg() {
        return memberHeadImg;
    }

    public void setMemberHeadImg(String memberHeadImg) {
        this.memberHeadImg = memberHeadImg;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getDelInd() {
        return delInd;
    }

    public void setDelInd(Integer delInd) {
        this.delInd = delInd;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhoneX() {
        return receivePhoneX;
    }

    public void setReceivePhoneX(String receivePhoneX) {
        this.receivePhoneX = receivePhoneX;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveLng() {
        return receiveLng;
    }

    public void setReceiveLng(String receiveLng) {
        this.receiveLng = receiveLng;
    }

    public String getReceiveLat() {
        return receiveLat;
    }

    public void setReceiveLat(String receiveLat) {
        this.receiveLat = receiveLat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public Date getCommentExpiredTime() {
        return commentExpiredTime;
    }

    public void setCommentExpiredTime(Date commentExpiredTime) {
        this.commentExpiredTime = commentExpiredTime;
    }

    public Date getFinishExpiredTime() {
        return finishExpiredTime;
    }

    public void setFinishExpiredTime(Date finishExpiredTime) {
        this.finishExpiredTime = finishExpiredTime;
    }

    public Date getRefundExpiredTime() {
        return refundExpiredTime;
    }

    public void setRefundExpiredTime(Date refundExpiredTime) {
        this.refundExpiredTime = refundExpiredTime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getNewOrOld() {
        return newOrOld;
    }

    public void setNewOrOld(Integer newOrOld) {
        this.newOrOld = newOrOld;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getUpdBy() {
        return updBy;
    }

    public void setUpdBy(String updBy) {
        this.updBy = updBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payOrderNo=").append(payOrderNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", type=").append(type);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", memberNickname=").append(memberNickname);
        sb.append(", memberHeadImg=").append(memberHeadImg);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopLogo=").append(shopLogo);
        sb.append(", couponStatus=").append(couponStatus);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", freightFee=").append(freightFee);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", payPrice=").append(payPrice);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", payChannel=").append(payChannel);
        sb.append(", status=").append(status);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", delInd=").append(delInd);
        sb.append(", receiveName=").append(receiveName);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", receivePhoneX=").append(receivePhoneX);
        sb.append(", receiveAddress=").append(receiveAddress);
        sb.append(", receiveLng=").append(receiveLng);
        sb.append(", receiveLat=").append(receiveLat);
        sb.append(", remark=").append(remark);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", deliveryNo=").append(deliveryNo);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", payEndTime=").append(payEndTime);
        sb.append(", commentExpiredTime=").append(commentExpiredTime);
        sb.append(", finishExpiredTime=").append(finishExpiredTime);
        sb.append(", refundExpiredTime=").append(refundExpiredTime);
        sb.append(", groupId=").append(groupId);
        sb.append(", newOrOld=").append(newOrOld);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}