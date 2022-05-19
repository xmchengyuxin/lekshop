package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class WalkTrends implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "1短视频>>2宝贝上新>>3种草>>4买家秀")
    private Integer type;

    @ApiModelProperty(value = "用户ID")
    private Integer walkMemberId;

    @ApiModelProperty(value = "用户名")
    private String walkMemberName;

    @ApiModelProperty(value = "头像")
    private String walkMemberHeadImg;

    @ApiModelProperty(value = "创作号")
    private Integer walkMemberUid;

    @ApiModelProperty(value = "图片合集")
    private String images;

    @ApiModelProperty(value = "视频链接")
    private String videoUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "浏览量")
    private Integer viewNum;

    @ApiModelProperty(value = "收藏量")
    private Integer collectionNum;

    @ApiModelProperty(value = "评论数量")
    private Integer commentNum;

    @ApiModelProperty(value = "0:待审核 1:通过 2不通过 3草稿")
    private Integer status;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "修改时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "审核时间")
    private Date verifyTime;

    @ApiModelProperty(value = "审核人")
    private String verifyName;

    @ApiModelProperty(value = "文案")
    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getWalkMemberUid() {
        return walkMemberUid;
    }

    public void setWalkMemberUid(Integer walkMemberUid) {
        this.walkMemberUid = walkMemberUid;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", walkMemberId=").append(walkMemberId);
        sb.append(", walkMemberName=").append(walkMemberName);
        sb.append(", walkMemberHeadImg=").append(walkMemberHeadImg);
        sb.append(", walkMemberUid=").append(walkMemberUid);
        sb.append(", images=").append(images);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", sort=").append(sort);
        sb.append(", weight=").append(weight);
        sb.append(", viewNum=").append(viewNum);
        sb.append(", collectionNum=").append(collectionNum);
        sb.append(", commentNum=").append(commentNum);
        sb.append(", status=").append(status);
        sb.append(", reason=").append(reason);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", verifyTime=").append(verifyTime);
        sb.append(", verifyName=").append(verifyName);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}