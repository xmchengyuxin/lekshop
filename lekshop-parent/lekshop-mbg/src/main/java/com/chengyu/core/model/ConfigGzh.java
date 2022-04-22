package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ConfigGzh implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "公众号名称")
    private String gzhName;

    @ApiModelProperty(value = "公众号二维码")
    private String gzhImg;

    @ApiModelProperty(value = "0关闭>>1开启")
    private Integer sellerStatus;

    @ApiModelProperty(value = "0关闭>>1开启")
    private Integer buyerStatus;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "短信通知>>0关闭>>1开启")
    private Integer smsNoticeStatus;

    @ApiModelProperty(value = "派单通知>>0关闭>>1开启")
    private Integer smsNewTask;

    @ApiModelProperty(value = "评价通知>>0关闭>>1开启")
    private Integer smsNeedComment;

    @ApiModelProperty(value = "订单评价驳回>>0关闭>>1开启")
    private Integer smsCommentReject;

    @ApiModelProperty(value = "订单超时提醒>>0关闭>>1开启")
    private Integer smsOrderWillQuit;

    @ApiModelProperty(value = "账号禁用>>0关闭>>1开启")
    private Integer smsCloseAccount;

    @ApiModelProperty(value = "提现成功通知>>0关闭>>1开启")
    private Integer smsWithdrawSus;

    @ApiModelProperty(value = "提现失败通知>>0关闭>>1开启")
    private Integer smsWithdrawFail;

    @ApiModelProperty(value = "订单驳回通知>>0关闭>>1开启")
    private Integer smsOrderReject;

    @ApiModelProperty(value = "确认收货通知>>0关闭>>1开启")
    private Integer smsConfirmOrder;

    @ApiModelProperty(value = "公众号内容")
    private String gzhContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGzhName() {
        return gzhName;
    }

    public void setGzhName(String gzhName) {
        this.gzhName = gzhName;
    }

    public String getGzhImg() {
        return gzhImg;
    }

    public void setGzhImg(String gzhImg) {
        this.gzhImg = gzhImg;
    }

    public Integer getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(Integer sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    public Integer getBuyerStatus() {
        return buyerStatus;
    }

    public void setBuyerStatus(Integer buyerStatus) {
        this.buyerStatus = buyerStatus;
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

    public Integer getSmsNoticeStatus() {
        return smsNoticeStatus;
    }

    public void setSmsNoticeStatus(Integer smsNoticeStatus) {
        this.smsNoticeStatus = smsNoticeStatus;
    }

    public Integer getSmsNewTask() {
        return smsNewTask;
    }

    public void setSmsNewTask(Integer smsNewTask) {
        this.smsNewTask = smsNewTask;
    }

    public Integer getSmsNeedComment() {
        return smsNeedComment;
    }

    public void setSmsNeedComment(Integer smsNeedComment) {
        this.smsNeedComment = smsNeedComment;
    }

    public Integer getSmsCommentReject() {
        return smsCommentReject;
    }

    public void setSmsCommentReject(Integer smsCommentReject) {
        this.smsCommentReject = smsCommentReject;
    }

    public Integer getSmsOrderWillQuit() {
        return smsOrderWillQuit;
    }

    public void setSmsOrderWillQuit(Integer smsOrderWillQuit) {
        this.smsOrderWillQuit = smsOrderWillQuit;
    }

    public Integer getSmsCloseAccount() {
        return smsCloseAccount;
    }

    public void setSmsCloseAccount(Integer smsCloseAccount) {
        this.smsCloseAccount = smsCloseAccount;
    }

    public Integer getSmsWithdrawSus() {
        return smsWithdrawSus;
    }

    public void setSmsWithdrawSus(Integer smsWithdrawSus) {
        this.smsWithdrawSus = smsWithdrawSus;
    }

    public Integer getSmsWithdrawFail() {
        return smsWithdrawFail;
    }

    public void setSmsWithdrawFail(Integer smsWithdrawFail) {
        this.smsWithdrawFail = smsWithdrawFail;
    }

    public Integer getSmsOrderReject() {
        return smsOrderReject;
    }

    public void setSmsOrderReject(Integer smsOrderReject) {
        this.smsOrderReject = smsOrderReject;
    }

    public Integer getSmsConfirmOrder() {
        return smsConfirmOrder;
    }

    public void setSmsConfirmOrder(Integer smsConfirmOrder) {
        this.smsConfirmOrder = smsConfirmOrder;
    }

    public String getGzhContent() {
        return gzhContent;
    }

    public void setGzhContent(String gzhContent) {
        this.gzhContent = gzhContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gzhName=").append(gzhName);
        sb.append(", gzhImg=").append(gzhImg);
        sb.append(", sellerStatus=").append(sellerStatus);
        sb.append(", buyerStatus=").append(buyerStatus);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", smsNoticeStatus=").append(smsNoticeStatus);
        sb.append(", smsNewTask=").append(smsNewTask);
        sb.append(", smsNeedComment=").append(smsNeedComment);
        sb.append(", smsCommentReject=").append(smsCommentReject);
        sb.append(", smsOrderWillQuit=").append(smsOrderWillQuit);
        sb.append(", smsCloseAccount=").append(smsCloseAccount);
        sb.append(", smsWithdrawSus=").append(smsWithdrawSus);
        sb.append(", smsWithdrawFail=").append(smsWithdrawFail);
        sb.append(", smsOrderReject=").append(smsOrderReject);
        sb.append(", smsConfirmOrder=").append(smsConfirmOrder);
        sb.append(", gzhContent=").append(gzhContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}