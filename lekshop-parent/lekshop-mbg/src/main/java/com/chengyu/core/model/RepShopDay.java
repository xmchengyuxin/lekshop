package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepShopDay implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "统计日期")
    private Date day;

    @ApiModelProperty(value = "营业额")
    private BigDecimal businessAmount;

    @ApiModelProperty(value = "运费支出")
    private BigDecimal deliveryAmount;

    @ApiModelProperty(value = "有效订单数量")
    private Integer orderNum;

    @ApiModelProperty(value = "下单人数")
    private Integer memberNum;

    @ApiModelProperty(value = "取消数量")
    private Integer cancelNum;

    @ApiModelProperty(value = "退单数量")
    private Integer refundNum;

    @ApiModelProperty(value = "好评数量")
    private Integer goodCommentNum;

    @ApiModelProperty(value = "中评数量")
    private Integer mediumCommentNum;

    @ApiModelProperty(value = "差评数量")
    private Integer badCommentNum;

    @ApiModelProperty(value = "商品销售额")
    private BigDecimal goodsAmount;

    @ApiModelProperty(value = "优惠券支出")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "退单率")
    private BigDecimal refundRate;

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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public BigDecimal getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(BigDecimal businessAmount) {
        this.businessAmount = businessAmount;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(Integer cancelNum) {
        this.cancelNum = cancelNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getGoodCommentNum() {
        return goodCommentNum;
    }

    public void setGoodCommentNum(Integer goodCommentNum) {
        this.goodCommentNum = goodCommentNum;
    }

    public Integer getMediumCommentNum() {
        return mediumCommentNum;
    }

    public void setMediumCommentNum(Integer mediumCommentNum) {
        this.mediumCommentNum = mediumCommentNum;
    }

    public Integer getBadCommentNum() {
        return badCommentNum;
    }

    public void setBadCommentNum(Integer badCommentNum) {
        this.badCommentNum = badCommentNum;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getRefundRate() {
        return refundRate;
    }

    public void setRefundRate(BigDecimal refundRate) {
        this.refundRate = refundRate;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", day=").append(day);
        sb.append(", businessAmount=").append(businessAmount);
        sb.append(", deliveryAmount=").append(deliveryAmount);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", memberNum=").append(memberNum);
        sb.append(", cancelNum=").append(cancelNum);
        sb.append(", refundNum=").append(refundNum);
        sb.append(", goodCommentNum=").append(goodCommentNum);
        sb.append(", mediumCommentNum=").append(mediumCommentNum);
        sb.append(", badCommentNum=").append(badCommentNum);
        sb.append(", goodsAmount=").append(goodsAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", refundRate=").append(refundRate);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}