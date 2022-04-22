package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class WalkTrendsCollection implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名")
    private String memberName;

    @ApiModelProperty(value = "头像")
    private String memberHeadImg;

    @ApiModelProperty(value = "商家ID")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "商家LOGO")
    private String shopLogo;

    @ApiModelProperty(value = "动态ID")
    private Integer trendsId;

    @ApiModelProperty(value = "动态内容")
    private String trendsContent;

    @ApiModelProperty(value = "动态主图")
    private String trendsImages;

    @ApiModelProperty(value = "动态链接")
    private String trendsVideoUrl;

    @ApiModelProperty(value = "修改时间")
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

    public String getMemberHeadImg() {
        return memberHeadImg;
    }

    public void setMemberHeadImg(String memberHeadImg) {
        this.memberHeadImg = memberHeadImg;
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

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public Integer getTrendsId() {
        return trendsId;
    }

    public void setTrendsId(Integer trendsId) {
        this.trendsId = trendsId;
    }

    public String getTrendsContent() {
        return trendsContent;
    }

    public void setTrendsContent(String trendsContent) {
        this.trendsContent = trendsContent;
    }

    public String getTrendsImages() {
        return trendsImages;
    }

    public void setTrendsImages(String trendsImages) {
        this.trendsImages = trendsImages;
    }

    public String getTrendsVideoUrl() {
        return trendsVideoUrl;
    }

    public void setTrendsVideoUrl(String trendsVideoUrl) {
        this.trendsVideoUrl = trendsVideoUrl;
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
        sb.append(", memberHeadImg=").append(memberHeadImg);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopLogo=").append(shopLogo);
        sb.append(", trendsId=").append(trendsId);
        sb.append(", trendsContent=").append(trendsContent);
        sb.append(", trendsImages=").append(trendsImages);
        sb.append(", trendsVideoUrl=").append(trendsVideoUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}