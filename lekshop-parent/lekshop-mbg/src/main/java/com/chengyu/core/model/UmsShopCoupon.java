package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsShopCoupon implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

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

    @ApiModelProperty(value = "发放数量")
    private Integer totalNum;

    @ApiModelProperty(value = "已领取数量")
    private Integer drawNum;

    @ApiModelProperty(value = "每人限领次数")
    private Integer limitNum;

    @ApiModelProperty(value = "1长期有效>>2有限天数>>3固定时间")
    private Integer validityType;

    @ApiModelProperty(value = "有效期(天)")
    private Integer validityDays;

    @ApiModelProperty(value = "固定开始日期")
    private Date fixedBeginDate;

    @ApiModelProperty(value = "固定结束日期")
    private Date fixedEndDate;

    @ApiModelProperty(value = "活动开始日期")
    private Date beginDate;

    @ApiModelProperty(value = "活动结束日期")
    private Date endDate;

    @ApiModelProperty(value = "使用规则")
    private String rules;

    @ApiModelProperty(value = "0发放中  1已领完  2已过期")
    private Integer status;

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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(Integer drawNum) {
        this.drawNum = drawNum;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getValidityType() {
        return validityType;
    }

    public void setValidityType(Integer validityType) {
        this.validityType = validityType;
    }

    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public Date getFixedBeginDate() {
        return fixedBeginDate;
    }

    public void setFixedBeginDate(Date fixedBeginDate) {
        this.fixedBeginDate = fixedBeginDate;
    }

    public Date getFixedEndDate() {
        return fixedEndDate;
    }

    public void setFixedEndDate(Date fixedEndDate) {
        this.fixedEndDate = fixedEndDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", useType=").append(useType);
        sb.append(", useGoodsIds=").append(useGoodsIds);
        sb.append(", useGoodsCateIds=").append(useGoodsCateIds);
        sb.append(", amount=").append(amount);
        sb.append(", fullAmount=").append(fullAmount);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", drawNum=").append(drawNum);
        sb.append(", limitNum=").append(limitNum);
        sb.append(", validityType=").append(validityType);
        sb.append(", validityDays=").append(validityDays);
        sb.append(", fixedBeginDate=").append(fixedBeginDate);
        sb.append(", fixedEndDate=").append(fixedEndDate);
        sb.append(", beginDate=").append(beginDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", rules=").append(rules);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}