package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsMemberNews implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "消息类型")
    private Integer type;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "简介")
    private String produce;

    @ApiModelProperty(value = "跳转模块")
    private String turnPage;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "发送状态(0:未发送 1:已发送 2:发送失败)")
    private String sendStatus;

    @ApiModelProperty(value = "0未读  1已读")
    private String readStatus;

    private String addBy;

    private Date addTime;

    private String updBy;

    private Date updTime;

    @ApiModelProperty(value = "用户ID")
    private Integer fromId;

    @ApiModelProperty(value = "用户名称")
    private String fromName;

    @ApiModelProperty(value = "内容")
    private String content;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getTurnPage() {
        return turnPage;
    }

    public void setTurnPage(String turnPage) {
        this.turnPage = turnPage;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdBy() {
        return updBy;
    }

    public void setUpdBy(String updBy) {
        this.updBy = updBy;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
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
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", title=").append(title);
        sb.append(", type=").append(type);
        sb.append(", img=").append(img);
        sb.append(", produce=").append(produce);
        sb.append(", turnPage=").append(turnPage);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", readStatus=").append(readStatus);
        sb.append(", addBy=").append(addBy);
        sb.append(", addTime=").append(addTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", fromId=").append(fromId);
        sb.append(", fromName=").append(fromName);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}