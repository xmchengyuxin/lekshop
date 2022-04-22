package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsMemberCoupon implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名")
    private String memberName;

    @ApiModelProperty(value = "来自商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "来自商家名称")
    private String shopName;

    @ApiModelProperty(value = "优惠券配置ID")
    private Integer couponConfigId;

    @ApiModelProperty(value = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "1满减券>>2折扣券")
    private Integer type;

    @ApiModelProperty(value = "可用范围>>1全部商品>>2指定商品>>3指定分类")
    private Integer useType;

    @ApiModelProperty(value = "可使用商品")
    private String useGoodsIds;

    @ApiModelProperty(value = "可使用商品类别")
    private String useGoodsCateIds;

    @ApiModelProperty(value = "金额/折扣")
    private BigDecimal amount;

    @ApiModelProperty(value = "满减金额")
    private BigDecimal fullAmount;

    @ApiModelProperty(value = "状态(0:未使用 1:已使用 2:已过期)")
    private Integer status;

    @ApiModelProperty(value = "有效开始时间")
    private Date validityBeginTime;

    @ApiModelProperty(value = "有效结束时间")
    private Date validityEndTime;

    @ApiModelProperty(value = "使用规则")
    private String rules;

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

    public Integer getCouponConfigId() {
        return couponConfigId;
    }

    public void setCouponConfigId(Integer couponConfigId) {
        this.couponConfigId = couponConfigId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getUseGoodsIds() {
        return useGoodsIds;
    }

    public void setUseGoodsIds(String useGoodsIds) {
        this.useGoodsIds = useGoodsIds;
    }

    public String getUseGoodsCateIds() {
        return useGoodsCateIds;
    }

    public void setUseGoodsCateIds(String useGoodsCateIds) {
        this.useGoodsCateIds = useGoodsCateIds;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(BigDecimal fullAmount) {
        this.fullAmount = fullAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getValidityBeginTime() {
        return validityBeginTime;
    }

    public void setValidityBeginTime(Date validityBeginTime) {
        this.validityBeginTime = validityBeginTime;
    }

    public Date getValidityEndTime() {
        return validityEndTime;
    }

    public void setValidityEndTime(Date validityEndTime) {
        this.validityEndTime = validityEndTime;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", couponConfigId=").append(couponConfigId);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", useType=").append(useType);
        sb.append(", useGoodsIds=").append(useGoodsIds);
        sb.append(", useGoodsCateIds=").append(useGoodsCateIds);
        sb.append(", amount=").append(amount);
        sb.append(", fullAmount=").append(fullAmount);
        sb.append(", status=").append(status);
        sb.append(", validityBeginTime=").append(validityBeginTime);
        sb.append(", validityEndTime=").append(validityEndTime);
        sb.append(", rules=").append(rules);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}