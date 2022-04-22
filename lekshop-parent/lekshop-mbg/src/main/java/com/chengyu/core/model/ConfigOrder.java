package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ConfigOrder implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "未支付订单自动取消时间")
    private Integer autoCancelMinutes;

    @ApiModelProperty(value = "自动收货时间")
    private Integer autoReceiveDay;

    @ApiModelProperty(value = "自动评论时间")
    private Integer autoCommentDay;

    @ApiModelProperty(value = "最后售后时间")
    private Integer lastRefundDay;

    @ApiModelProperty(value = "自动同意售后时间")
    private Integer autoAgreeRefundDay;

    @ApiModelProperty(value = "买家退货时间")
    private Integer buyerRefundDay;

    @ApiModelProperty(value = "卖家收货时间")
    private Integer sellerReceiveDay;

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

    public Integer getAutoCancelMinutes() {
        return autoCancelMinutes;
    }

    public void setAutoCancelMinutes(Integer autoCancelMinutes) {
        this.autoCancelMinutes = autoCancelMinutes;
    }

    public Integer getAutoReceiveDay() {
        return autoReceiveDay;
    }

    public void setAutoReceiveDay(Integer autoReceiveDay) {
        this.autoReceiveDay = autoReceiveDay;
    }

    public Integer getAutoCommentDay() {
        return autoCommentDay;
    }

    public void setAutoCommentDay(Integer autoCommentDay) {
        this.autoCommentDay = autoCommentDay;
    }

    public Integer getLastRefundDay() {
        return lastRefundDay;
    }

    public void setLastRefundDay(Integer lastRefundDay) {
        this.lastRefundDay = lastRefundDay;
    }

    public Integer getAutoAgreeRefundDay() {
        return autoAgreeRefundDay;
    }

    public void setAutoAgreeRefundDay(Integer autoAgreeRefundDay) {
        this.autoAgreeRefundDay = autoAgreeRefundDay;
    }

    public Integer getBuyerRefundDay() {
        return buyerRefundDay;
    }

    public void setBuyerRefundDay(Integer buyerRefundDay) {
        this.buyerRefundDay = buyerRefundDay;
    }

    public Integer getSellerReceiveDay() {
        return sellerReceiveDay;
    }

    public void setSellerReceiveDay(Integer sellerReceiveDay) {
        this.sellerReceiveDay = sellerReceiveDay;
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
        sb.append(", autoCancelMinutes=").append(autoCancelMinutes);
        sb.append(", autoReceiveDay=").append(autoReceiveDay);
        sb.append(", autoCommentDay=").append(autoCommentDay);
        sb.append(", lastRefundDay=").append(lastRefundDay);
        sb.append(", autoAgreeRefundDay=").append(autoAgreeRefundDay);
        sb.append(", buyerRefundDay=").append(buyerRefundDay);
        sb.append(", sellerReceiveDay=").append(sellerReceiveDay);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}