package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsOrderRefund implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "退款单号")
    private String refundNo;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "订单详情ID")
    private Integer detailId;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名")
    private String memberName;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "1仅退款 2退货退款")
    private Integer refundInd;

    @ApiModelProperty(value = "下单时间")
    private Date buyTime;

    @ApiModelProperty(value = "原订单金额")
    private BigDecimal originalAmount;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "退款原因")
    private String reason;

    @ApiModelProperty(value = "退款凭证")
    private String img;

    @ApiModelProperty(value = "0申请退款>>1等待买家退货>>2买家已退货>>3商家已拒绝>>4平台介入>>5退款完成>>6退款关闭")
    private Integer status;

    @ApiModelProperty(value = "支付方式")
    private String payMethod;

    @ApiModelProperty(value = "退单时间")
    private Date refundTime;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    private String goodsName;

    @ApiModelProperty(value = "主图")
    private String goodsMainImg;

    @ApiModelProperty(value = "商品规格名称")
    private String goodsParamName;

    @ApiModelProperty(value = "退货收件人")
    private String returnName;

    @ApiModelProperty(value = "退货电话")
    private String returnPhone;

    @ApiModelProperty(value = "退货地址")
    private String returnAddress;

    @ApiModelProperty(value = "物流方式")
    private String deliveryType;

    @ApiModelProperty(value = "第三方配送单号")
    private String deliveryNo;

    @ApiModelProperty(value = "自动同意售后时间")
    private Date autoAgreeTime;

    @ApiModelProperty(value = "买家寄货时间")
    private Date buyerSendGoodsTime;

    @ApiModelProperty(value = "卖家确认收货时间")
    private Date sellerConfirmTime;

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

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getRefundInd() {
        return refundInd;
    }

    public void setRefundInd(Integer refundInd) {
        this.refundInd = refundInd;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
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

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
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

    public String getGoodsParamName() {
        return goodsParamName;
    }

    public void setGoodsParamName(String goodsParamName) {
        this.goodsParamName = goodsParamName;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public String getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(String returnAddress) {
        this.returnAddress = returnAddress;
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

    public Date getAutoAgreeTime() {
        return autoAgreeTime;
    }

    public void setAutoAgreeTime(Date autoAgreeTime) {
        this.autoAgreeTime = autoAgreeTime;
    }

    public Date getBuyerSendGoodsTime() {
        return buyerSendGoodsTime;
    }

    public void setBuyerSendGoodsTime(Date buyerSendGoodsTime) {
        this.buyerSendGoodsTime = buyerSendGoodsTime;
    }

    public Date getSellerConfirmTime() {
        return sellerConfirmTime;
    }

    public void setSellerConfirmTime(Date sellerConfirmTime) {
        this.sellerConfirmTime = sellerConfirmTime;
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
        sb.append(", refundNo=").append(refundNo);
        sb.append(", orderId=").append(orderId);
        sb.append(", detailId=").append(detailId);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", headImg=").append(headImg);
        sb.append(", refundInd=").append(refundInd);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", originalAmount=").append(originalAmount);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", reason=").append(reason);
        sb.append(", img=").append(img);
        sb.append(", status=").append(status);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsMainImg=").append(goodsMainImg);
        sb.append(", goodsParamName=").append(goodsParamName);
        sb.append(", returnName=").append(returnName);
        sb.append(", returnPhone=").append(returnPhone);
        sb.append(", returnAddress=").append(returnAddress);
        sb.append(", deliveryType=").append(deliveryType);
        sb.append(", deliveryNo=").append(deliveryNo);
        sb.append(", autoAgreeTime=").append(autoAgreeTime);
        sb.append(", buyerSendGoodsTime=").append(buyerSendGoodsTime);
        sb.append(", sellerConfirmTime=").append(sellerConfirmTime);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}