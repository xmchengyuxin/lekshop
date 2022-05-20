package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class WalkTrendsCommentLike implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer viewMemberId;

    @ApiModelProperty(value = "用户名")
    private String viewMemberName;

    @ApiModelProperty(value = "头像")
    private String viewMemberHeadImg;

    @ApiModelProperty(value = "评论ID")
    private Integer commentId;

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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
        sb.append(", viewMemberId=").append(viewMemberId);
        sb.append(", viewMemberName=").append(viewMemberName);
        sb.append(", viewMemberHeadImg=").append(viewMemberHeadImg);
        sb.append(", commentId=").append(commentId);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}