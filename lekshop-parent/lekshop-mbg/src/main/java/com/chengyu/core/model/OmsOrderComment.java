package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsOrderComment implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "订单详情ID")
    private Integer detailId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "主图")
    private String goodsMainImg;

    @ApiModelProperty(value = "商品规格名称")
    private String goodsParamName;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名")
    private String memberName;

    @ApiModelProperty(value = "头像")
    private String memberHeadImg;

    @ApiModelProperty(value = "是否匿名")
    private Integer anonymousStatus;

    @ApiModelProperty(value = "0待评价>>1已评价>>2已追评>>3已过期")
    private Integer status;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "评论图片")
    private String img;

    @ApiModelProperty(value = "1好评>>2中评>>3差评")
    private Integer goodsComment;

    @ApiModelProperty(value = "综合评分")
    private BigDecimal avgStarNum;

    @ApiModelProperty(value = "描述相符")
    private BigDecimal goodsStarNum;

    @ApiModelProperty(value = "发货速度")
    private BigDecimal deliveryStarNum;

    @ApiModelProperty(value = "服务态度")
    private BigDecimal shopStarNum;

    @ApiModelProperty(value = "物流服务")
    private BigDecimal logisticStarNum;

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

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsMainImg() {
        return goodsMainImg;
    }

    public void setGoodsMainImg(String goodsMainImg) {
        this.goodsMainImg = goodsMainImg;
    }

    public String getGoodsParamName() {
        return goodsParamName;
    }

    public void setGoodsParamName(String goodsParamName) {
        this.goodsParamName = goodsParamName;
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

    public String getMemberHeadImg() {
        return memberHeadImg;
    }

    public void setMemberHeadImg(String memberHeadImg) {
        this.memberHeadImg = memberHeadImg;
    }

    public Integer getAnonymousStatus() {
        return anonymousStatus;
    }

    public void setAnonymousStatus(Integer anonymousStatus) {
        this.anonymousStatus = anonymousStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(Integer goodsComment) {
        this.goodsComment = goodsComment;
    }

    public BigDecimal getAvgStarNum() {
        return avgStarNum;
    }

    public void setAvgStarNum(BigDecimal avgStarNum) {
        this.avgStarNum = avgStarNum;
    }

    public BigDecimal getGoodsStarNum() {
        return goodsStarNum;
    }

    public void setGoodsStarNum(BigDecimal goodsStarNum) {
        this.goodsStarNum = goodsStarNum;
    }

    public BigDecimal getDeliveryStarNum() {
        return deliveryStarNum;
    }

    public void setDeliveryStarNum(BigDecimal deliveryStarNum) {
        this.deliveryStarNum = deliveryStarNum;
    }

    public BigDecimal getShopStarNum() {
        return shopStarNum;
    }

    public void setShopStarNum(BigDecimal shopStarNum) {
        this.shopStarNum = shopStarNum;
    }

    public BigDecimal getLogisticStarNum() {
        return logisticStarNum;
    }

    public void setLogisticStarNum(BigDecimal logisticStarNum) {
        this.logisticStarNum = logisticStarNum;
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
        sb.append(", detailId=").append(detailId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsMainImg=").append(goodsMainImg);
        sb.append(", goodsParamName=").append(goodsParamName);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", memberHeadImg=").append(memberHeadImg);
        sb.append(", anonymousStatus=").append(anonymousStatus);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append(", img=").append(img);
        sb.append(", goodsComment=").append(goodsComment);
        sb.append(", avgStarNum=").append(avgStarNum);
        sb.append(", goodsStarNum=").append(goodsStarNum);
        sb.append(", deliveryStarNum=").append(deliveryStarNum);
        sb.append(", shopStarNum=").append(shopStarNum);
        sb.append(", logisticStarNum=").append(logisticStarNum);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}