package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PointConfig implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "积分商城入口开关")
    private Integer pointStatus;

    @ApiModelProperty(value = "签到积分")
    private BigDecimal signPoint;

    @ApiModelProperty(value = "消费积分比例(一元=几积分)")
    private BigDecimal consumePoint;

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

    public Integer getPointStatus() {
        return pointStatus;
    }

    public void setPointStatus(Integer pointStatus) {
        this.pointStatus = pointStatus;
    }

    public BigDecimal getSignPoint() {
        return signPoint;
    }

    public void setSignPoint(BigDecimal signPoint) {
        this.signPoint = signPoint;
    }

    public BigDecimal getConsumePoint() {
        return consumePoint;
    }

    public void setConsumePoint(BigDecimal consumePoint) {
        this.consumePoint = consumePoint;
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
        sb.append(", pointStatus=").append(pointStatus);
        sb.append(", signPoint=").append(signPoint);
        sb.append(", consumePoint=").append(consumePoint);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}