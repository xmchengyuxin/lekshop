package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConfigMissionDetail implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "佣金组别ID")
    private Integer missionConfigId;

    @ApiModelProperty(value = "佣金组别")
    private String missionConfigName;

    @ApiModelProperty(value = "会员组别")
    private Integer groupId;

    @ApiModelProperty(value = "会员组别")
    private String groupName;

    @ApiModelProperty(value = "买家>>一级推荐人佣金百分比")
    private BigDecimal buyerFinishRateOne;

    @ApiModelProperty(value = "买家>>二级推荐人佣金百分比")
    private BigDecimal buyerFinishRateTwo;

    @ApiModelProperty(value = "买家>>三级推荐人佣金百分比")
    private BigDecimal buyerFinishRateThree;

    @ApiModelProperty(value = "买家有效天数")
    private Integer buyerValidDay;

    @ApiModelProperty(value = "卖家>>一级推荐人佣金百分比")
    private BigDecimal sellerFinishRateOne;

    @ApiModelProperty(value = "卖家>>二级推荐人佣金百分比")
    private BigDecimal sellerFinishRateTwo;

    @ApiModelProperty(value = "卖家>>三级推荐人佣金百分比")
    private BigDecimal sellerFinishRateThree;

    @ApiModelProperty(value = "卖家有效天数")
    private Integer sellerValidDay;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "佣金形式>>1按百分比>>2固定金额")
    private Integer missionType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMissionConfigId() {
        return missionConfigId;
    }

    public void setMissionConfigId(Integer missionConfigId) {
        this.missionConfigId = missionConfigId;
    }

    public String getMissionConfigName() {
        return missionConfigName;
    }

    public void setMissionConfigName(String missionConfigName) {
        this.missionConfigName = missionConfigName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public BigDecimal getBuyerFinishRateOne() {
        return buyerFinishRateOne;
    }

    public void setBuyerFinishRateOne(BigDecimal buyerFinishRateOne) {
        this.buyerFinishRateOne = buyerFinishRateOne;
    }

    public BigDecimal getBuyerFinishRateTwo() {
        return buyerFinishRateTwo;
    }

    public void setBuyerFinishRateTwo(BigDecimal buyerFinishRateTwo) {
        this.buyerFinishRateTwo = buyerFinishRateTwo;
    }

    public BigDecimal getBuyerFinishRateThree() {
        return buyerFinishRateThree;
    }

    public void setBuyerFinishRateThree(BigDecimal buyerFinishRateThree) {
        this.buyerFinishRateThree = buyerFinishRateThree;
    }

    public Integer getBuyerValidDay() {
        return buyerValidDay;
    }

    public void setBuyerValidDay(Integer buyerValidDay) {
        this.buyerValidDay = buyerValidDay;
    }

    public BigDecimal getSellerFinishRateOne() {
        return sellerFinishRateOne;
    }

    public void setSellerFinishRateOne(BigDecimal sellerFinishRateOne) {
        this.sellerFinishRateOne = sellerFinishRateOne;
    }

    public BigDecimal getSellerFinishRateTwo() {
        return sellerFinishRateTwo;
    }

    public void setSellerFinishRateTwo(BigDecimal sellerFinishRateTwo) {
        this.sellerFinishRateTwo = sellerFinishRateTwo;
    }

    public BigDecimal getSellerFinishRateThree() {
        return sellerFinishRateThree;
    }

    public void setSellerFinishRateThree(BigDecimal sellerFinishRateThree) {
        this.sellerFinishRateThree = sellerFinishRateThree;
    }

    public Integer getSellerValidDay() {
        return sellerValidDay;
    }

    public void setSellerValidDay(Integer sellerValidDay) {
        this.sellerValidDay = sellerValidDay;
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

    public Integer getMissionType() {
        return missionType;
    }

    public void setMissionType(Integer missionType) {
        this.missionType = missionType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", missionConfigId=").append(missionConfigId);
        sb.append(", missionConfigName=").append(missionConfigName);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", buyerFinishRateOne=").append(buyerFinishRateOne);
        sb.append(", buyerFinishRateTwo=").append(buyerFinishRateTwo);
        sb.append(", buyerFinishRateThree=").append(buyerFinishRateThree);
        sb.append(", buyerValidDay=").append(buyerValidDay);
        sb.append(", sellerFinishRateOne=").append(sellerFinishRateOne);
        sb.append(", sellerFinishRateTwo=").append(sellerFinishRateTwo);
        sb.append(", sellerFinishRateThree=").append(sellerFinishRateThree);
        sb.append(", sellerValidDay=").append(sellerValidDay);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", missionType=").append(missionType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}