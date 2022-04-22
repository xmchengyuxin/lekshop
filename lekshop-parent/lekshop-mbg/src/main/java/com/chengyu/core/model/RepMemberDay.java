package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class RepMemberDay implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "统计日期")
    private Date day;

    @ApiModelProperty(value = "本日总人数")
    private Integer totalNum;

    @ApiModelProperty(value = "本日活跃人数")
    private Integer activiteNum;

    @ApiModelProperty(value = "今日增长数量")
    private Integer registerNum;

    @ApiModelProperty(value = "下单人数")
    private Integer orderNum;

    @ApiModelProperty(value = "小程序注册人数")
    private Integer xcxNum;

    @ApiModelProperty(value = "APP注册人数")
    private Integer appNum;

    @ApiModelProperty(value = "H5注册人数")
    private Integer linkNum;

    @ApiModelProperty(value = "小程序下单人数")
    private Integer xcxOrderNum;

    @ApiModelProperty(value = "APP下单人数")
    private Integer appOrderNum;

    @ApiModelProperty(value = "H5下单人数")
    private Integer linkOrderNum;

    @ApiModelProperty(value = "新客下单数")
    private Integer newOrderNum;

    @ApiModelProperty(value = "老客下单数")
    private Integer oldOrderNum;

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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getActiviteNum() {
        return activiteNum;
    }

    public void setActiviteNum(Integer activiteNum) {
        this.activiteNum = activiteNum;
    }

    public Integer getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(Integer registerNum) {
        this.registerNum = registerNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getXcxNum() {
        return xcxNum;
    }

    public void setXcxNum(Integer xcxNum) {
        this.xcxNum = xcxNum;
    }

    public Integer getAppNum() {
        return appNum;
    }

    public void setAppNum(Integer appNum) {
        this.appNum = appNum;
    }

    public Integer getLinkNum() {
        return linkNum;
    }

    public void setLinkNum(Integer linkNum) {
        this.linkNum = linkNum;
    }

    public Integer getXcxOrderNum() {
        return xcxOrderNum;
    }

    public void setXcxOrderNum(Integer xcxOrderNum) {
        this.xcxOrderNum = xcxOrderNum;
    }

    public Integer getAppOrderNum() {
        return appOrderNum;
    }

    public void setAppOrderNum(Integer appOrderNum) {
        this.appOrderNum = appOrderNum;
    }

    public Integer getLinkOrderNum() {
        return linkOrderNum;
    }

    public void setLinkOrderNum(Integer linkOrderNum) {
        this.linkOrderNum = linkOrderNum;
    }

    public Integer getNewOrderNum() {
        return newOrderNum;
    }

    public void setNewOrderNum(Integer newOrderNum) {
        this.newOrderNum = newOrderNum;
    }

    public Integer getOldOrderNum() {
        return oldOrderNum;
    }

    public void setOldOrderNum(Integer oldOrderNum) {
        this.oldOrderNum = oldOrderNum;
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
        sb.append(", day=").append(day);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", activiteNum=").append(activiteNum);
        sb.append(", registerNum=").append(registerNum);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", xcxNum=").append(xcxNum);
        sb.append(", appNum=").append(appNum);
        sb.append(", linkNum=").append(linkNum);
        sb.append(", xcxOrderNum=").append(xcxOrderNum);
        sb.append(", appOrderNum=").append(appOrderNum);
        sb.append(", linkOrderNum=").append(linkOrderNum);
        sb.append(", newOrderNum=").append(newOrderNum);
        sb.append(", oldOrderNum=").append(oldOrderNum);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}