package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsMemberAccount implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "余额")
    private BigDecimal amount;

    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezeAmount;

    @ApiModelProperty(value = "任务佣金")
    private BigDecimal mission;

    @ApiModelProperty(value = "冻结任务佣金")
    private BigDecimal freezeMission;

    @ApiModelProperty(value = "推广佣金")
    private BigDecimal spmission;

    @ApiModelProperty(value = "冻结推广佣金")
    private BigDecimal freezeSpmission;

    @ApiModelProperty(value = "积分")
    private BigDecimal point;

    @ApiModelProperty(value = "冻结积分")
    private BigDecimal freezePoint;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getMission() {
        return mission;
    }

    public void setMission(BigDecimal mission) {
        this.mission = mission;
    }

    public BigDecimal getFreezeMission() {
        return freezeMission;
    }

    public void setFreezeMission(BigDecimal freezeMission) {
        this.freezeMission = freezeMission;
    }

    public BigDecimal getSpmission() {
        return spmission;
    }

    public void setSpmission(BigDecimal spmission) {
        this.spmission = spmission;
    }

    public BigDecimal getFreezeSpmission() {
        return freezeSpmission;
    }

    public void setFreezeSpmission(BigDecimal freezeSpmission) {
        this.freezeSpmission = freezeSpmission;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getFreezePoint() {
        return freezePoint;
    }

    public void setFreezePoint(BigDecimal freezePoint) {
        this.freezePoint = freezePoint;
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
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", amount=").append(amount);
        sb.append(", freezeAmount=").append(freezeAmount);
        sb.append(", mission=").append(mission);
        sb.append(", freezeMission=").append(freezeMission);
        sb.append(", spmission=").append(spmission);
        sb.append(", freezeSpmission=").append(freezeSpmission);
        sb.append(", point=").append(point);
        sb.append(", freezePoint=").append(freezePoint);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}