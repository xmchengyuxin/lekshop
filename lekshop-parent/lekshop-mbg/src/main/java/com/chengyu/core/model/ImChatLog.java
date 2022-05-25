package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ImChatLog implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "会话ID")
    private Long sessionId;

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

    @ApiModelProperty(value = "1发送>>2接收")
    private Integer sendType;

    @ApiModelProperty(value = "消息类型>>1文本>>2图片>>3视频>>4商品>>5订单")
    private Integer msgType;

    @ApiModelProperty(value = "0未读 1已读")
    private Integer readStatus;

    @ApiModelProperty(value = "0未发送 1已发送")
    private Integer sendStatus;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "操作时间")
    private Date updTime;

    @ApiModelProperty(value = "内容")
    private String msgContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberNickname=").append(memberNickname);
        sb.append(", memberHeadImg=").append(memberHeadImg);
        sb.append(", targetId=").append(targetId);
        sb.append(", targetNickname=").append(targetNickname);
        sb.append(", targetHeadImg=").append(targetHeadImg);
        sb.append(", sendType=").append(sendType);
        sb.append(", msgType=").append(msgType);
        sb.append(", readStatus=").append(readStatus);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", updTime=").append(updTime);
        sb.append(", msgContent=").append(msgContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}