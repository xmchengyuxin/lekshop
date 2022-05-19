package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class WalkTrendsCollection implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer viewMemberId;

    @ApiModelProperty(value = "用户名")
    private String viewMemberName;

    @ApiModelProperty(value = "头像")
    private String viewMemberHeadImg;

    @ApiModelProperty(value = "创作人ID")
    private Integer walkMemberId;

    @ApiModelProperty(value = "创作人")
    private String walkMemberName;

    @ApiModelProperty(value = "创作人头像")
    private String walkMemberHeadImg;

    @ApiModelProperty(value = "动态ID")
    private Integer trendsId;

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

    @ApiModelProperty(value = "动态内容")
    private String trendsContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewMemberId() {
        return viewMemberId;
    }

    public void setViewMemberId(Integer viewMemberId) {
        this.viewMemberId = viewMemberId;
    }

    public String getViewMemberName() {
        return viewMemberName;
    }

    public void setViewMemberName(String viewMemberName) {
        this.viewMemberName = viewMemberName;
    }

    public String getViewMemberHeadImg() {
        return viewMemberHeadImg;
    }

    public void setViewMemberHeadImg(String viewMemberHeadImg) {
        this.viewMemberHeadImg = viewMemberHeadImg;
    }

    public Integer getWalkMemberId() {
        return walkMemberId;
    }

    public void setWalkMemberId(Integer walkMemberId) {
        this.walkMemberId = walkMemberId;
    }

    public String getWalkMemberName() {
        return walkMemberName;
    }

    public void setWalkMemberName(String walkMemberName) {
        this.walkMemberName = walkMemberName;
    }

    public String getWalkMemberHeadImg() {
        return walkMemberHeadImg;
    }

    public void setWalkMemberHeadImg(String walkMemberHeadImg) {
        this.walkMemberHeadImg = walkMemberHeadImg;
    }

    public Integer getTrendsId() {
        return trendsId;
    }

    public void setTrendsId(Integer trendsId) {
        this.trendsId = trendsId;
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

    public String getTrendsContent() {
        return trendsContent;
    }

    public void setTrendsContent(String trendsContent) {
        this.trendsContent = trendsContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", viewMemberId=").append(viewMemberId);
        sb.append(", viewMemberName=").append(viewMemberName);
        sb.append(", viewMemberHeadImg=").append(viewMemberHeadImg);
        sb.append(", walkMemberId=").append(walkMemberId);
        sb.append(", walkMemberName=").append(walkMemberName);
        sb.append(", walkMemberHeadImg=").append(walkMemberHeadImg);
        sb.append(", trendsId=").append(trendsId);
        sb.append(", trendsImages=").append(trendsImages);
        sb.append(", trendsVideoUrl=").append(trendsVideoUrl);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", trendsContent=").append(trendsContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}