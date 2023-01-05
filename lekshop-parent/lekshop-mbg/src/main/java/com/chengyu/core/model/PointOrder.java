package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PointOrder implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名")
    private String memberName;

    @ApiModelProperty(value = "头像")
    private String memberHeadImg;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    private String goodsName;

    @ApiModelProperty(value = "主图")
    private String goodsMainImg;

    @ApiModelProperty(value = "库存计算方式>>1下单减库存>>2付款减库存")
    private Integer stockType;

    @ApiModelProperty(value = "原价")
    private BigDecimal buyOriPrice;

    @ApiModelProperty(value = "购买单价")
    private BigDecimal buyPrice;

    @ApiModelProperty(value = "兑换积分")
    private BigDecimal buyPoint;

    @ApiModelProperty(value = "购买数量")
    private Integer buyNum;

    @ApiModelProperty(value = "购买总价")
    private BigDecimal buyTotalPrice;

    @ApiModelProperty(value = "购买总积分")
    private BigDecimal buyTotalPoint;

    @ApiModelProperty(value = "0待支付>>1待发货>>2待收货>>3已完成>>4已取消>>5退款")
    private Integer status;

    @ApiModelProperty(value = "0未退款>>1退款中>>2退款成功>>3退款驳回")
    private Integer refundStatus;

    @ApiModelProperty(value = "0未删除 1已删除")
    private Integer delInd;

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

    @ApiModelProperty(value = "物流方式")
    private String deliveryType;

    @ApiModelProperty(value = "第三方配送单号")
    private String deliveryNo;

    @ApiModelProperty(value = "超过这个时间未支付订单取消")
    private Date payEndTime;

    @ApiModelProperty(value = "自动确认收货时间")
    private Date finishExpiredTime;

    @ApiModelProperty(value = "售后截止时间")
    private Date refundExpiredTime;

    @ApiModelProperty(value = "订单备注")
    private String remark;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getMemberHeadImg() {
        return memberHeadImg;
    }

    public void setMemberHeadImg(String memberHeadImg) {
        this.memberHeadImg = memberHeadImg;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsMainImg() {
        return goodsMainImg;
    }

    public void setGoodsMainImg(String goodsMainImg) {
        this.goodsMainImg = goodsMainImg;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public BigDecimal getBuyOriPrice() {
        return buyOriPrice;
    }

    public void setBuyOriPrice(BigDecimal buyOriPrice) {
        this.buyOriPrice = buyOriPrice;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getBuyPoint() {
        return buyPoint;
    }

    public void setBuyPoint(BigDecimal buyPoint) {
        this.buyPoint = buyPoint;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public BigDecimal getBuyTotalPrice() {
        return buyTotalPrice;
    }

    public void setBuyTotalPrice(BigDecimal buyTotalPrice) {
        this.buyTotalPrice = buyTotalPrice;
    }

    public BigDecimal getBuyTotalPoint() {
        return buyTotalPoint;
    }

    public void setBuyTotalPoint(BigDecimal buyTotalPoint) {
        this.buyTotalPoint = buyTotalPoint;
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

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", memberHeadImg=").append(memberHeadImg);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsMainImg=").append(goodsMainImg);
        sb.append(", stockType=").append(stockType);
        sb.append(", buyOriPrice=").append(buyOriPrice);
        sb.append(", buyPrice=").append(buyPrice);
        sb.append(", buyPoint=").append(buyPoint);
        sb.append(", buyNum=").append(buyNum);
        sb.append(", buyTotalPrice=").append(buyTotalPrice);
        sb.append(", buyTotalPoint=").append(buyTotalPoint);
        sb.append(", status=").append(status);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", delInd=").append(delInd);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", payChannel=").append(payChannel);
        sb.append(", receiveName=").append(receiveName);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", receivePhoneX=").append(receivePhoneX);
        sb.append(", receiveAddress=").append(receiveAddress);
        sb.append(", receiveLng=").append(receiveLng);
        sb.append(", receiveLat=").append(receiveLat);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", deliveryNo=").append(deliveryNo);
        sb.append(", payEndTime=").append(payEndTime);
        sb.append(", finishExpiredTime=").append(finishExpiredTime);
        sb.append(", refundExpiredTime=").append(refundExpiredTime);
        sb.append(", remark=").append(remark);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}