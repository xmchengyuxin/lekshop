package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ImChatSession implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户昵称")
    private String memberNickname;

    @ApiModelProperty(value = "头像")
    private String memberHeadImg;

    @ApiModelProperty(value = "用户ID")
    private Integer targetId;

    @ApiModelProperty(value = "用户昵称")
    private String targetNickname;

    @ApiModelProperty(value = "头像")
    private String targetHeadImg;

    @ApiModelProperty(value = "未读数量")
    private Integer unReadNum;

    @ApiModelProperty(value = "最后一条信息")
    private String lastMsg;

    @ApiModelProperty(value = "会话时间")
    private Date addTime;

    @ApiModelProperty(value = "最新会话时间")
    private Date updTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberHeadImg() {
        return memberHeadImg;
    }

    public void setMemberHeadImg(String memberHeadImg) {
        this.memberHeadImg = memberHeadImg;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getTargetNickname() {
        return targetNickname;
    }

    public void setTargetNickname(String targetNickname) {
        this.targetNickname = targetNickname;
    }

    public String getTargetHeadImg() {
        return targetHeadImg;
    }

    public void setTargetHeadImg(String targetHeadImg) {
        this.targetHeadImg = targetHeadImg;
    }

    public Integer getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(Integer unReadNum) {
        this.unReadNum = unReadNum;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberNickname=").append(memberNickname);
        sb.append(", memberHeadImg=").append(memberHeadImg);
        sb.append(", targetId=").append(targetId);
        sb.append(", targetNickname=").append(targetNickname);
        sb.append(", targetHeadImg=").append(targetHeadImg);
        sb.append(", unReadNum=").append(unReadNum);
        sb.append(", lastMsg=").append(lastMsg);
        sb.append(", addTime=").append(addTime);
        sb.append(", updTime=").append(updTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}