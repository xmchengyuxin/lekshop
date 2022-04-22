package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepConversionDay implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "统计日期")
    private Date day;

    @ApiModelProperty(value = "小程序打开次数")
    private Integer xcxOpenNum;

    @ApiModelProperty(value = "APP打开次数")
    private Integer appOpenNum;

    @ApiModelProperty(value = "H5打开次数")
    private Integer linkOpenNum;

    @ApiModelProperty(value = "转化率(打开次数/成交单数)")
    private BigDecimal transRate;

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

    public Integer getXcxOpenNum() {
        return xcxOpenNum;
    }

    public void setXcxOpenNum(Integer xcxOpenNum) {
        this.xcxOpenNum = xcxOpenNum;
    }

    public Integer getAppOpenNum() {
        return appOpenNum;
    }

    public void setAppOpenNum(Integer appOpenNum) {
        this.appOpenNum = appOpenNum;
    }

    public Integer getLinkOpenNum() {
        return linkOpenNum;
    }

    public void setLinkOpenNum(Integer linkOpenNum) {
        this.linkOpenNum = linkOpenNum;
    }

    public BigDecimal getTransRate() {
        return transRate;
    }

    public void setTransRate(BigDecimal transRate) {
        this.transRate = transRate;
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
        sb.append(", xcxOpenNum=").append(xcxOpenNum);
        sb.append(", appOpenNum=").append(appOpenNum);
        sb.append(", linkOpenNum=").append(linkOpenNum);
        sb.append(", transRate=").append(transRate);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}