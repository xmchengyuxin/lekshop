package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsMemberRealname implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "联系手机")
    private String linkPhone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "身份证正面")
    private String idcardFace;

    @ApiModelProperty(value = "手持身份证正面")
    private String idcardFaceHand;

    @ApiModelProperty(value = "身份证反面")
    private String idcardBack;

    @ApiModelProperty(value = "手持身份证反面")
    private String idcardBackHand;

    @ApiModelProperty(value = "身份证有效期")
    private Date idcardBeginTime;

    @ApiModelProperty(value = "身份证有效期")
    private Date idcardEndTime;

    @ApiModelProperty(value = "认证次数")
    private Integer num;

    @ApiModelProperty(value = "0待审核 1审核通过 2审核失败")
    private Integer status;

    @ApiModelProperty(value = "原因")
    private String reason;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdcardFace() {
        return idcardFace;
    }

    public void setIdcardFace(String idcardFace) {
        this.idcardFace = idcardFace;
    }

    public String getIdcardFaceHand() {
        return idcardFaceHand;
    }

    public void setIdcardFaceHand(String idcardFaceHand) {
        this.idcardFaceHand = idcardFaceHand;
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack;
    }

    public String getIdcardBackHand() {
        return idcardBackHand;
    }

    public void setIdcardBackHand(String idcardBackHand) {
        this.idcardBackHand = idcardBackHand;
    }

    public Date getIdcardBeginTime() {
        return idcardBeginTime;
    }

    public void setIdcardBeginTime(Date idcardBeginTime) {
        this.idcardBeginTime = idcardBeginTime;
    }

    public Date getIdcardEndTime() {
        return idcardEndTime;
    }

    public void setIdcardEndTime(Date idcardEndTime) {
        this.idcardEndTime = idcardEndTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", realname=").append(realname);
        sb.append(", idCard=").append(idCard);
        sb.append(", linkPhone=").append(linkPhone);
        sb.append(", email=").append(email);
        sb.append(", idcardFace=").append(idcardFace);
        sb.append(", idcardFaceHand=").append(idcardFaceHand);
        sb.append(", idcardBack=").append(idcardBack);
        sb.append(", idcardBackHand=").append(idcardBackHand);
        sb.append(", idcardBeginTime=").append(idcardBeginTime);
        sb.append(", idcardEndTime=").append(idcardEndTime);
        sb.append(", num=").append(num);
        sb.append(", status=").append(status);
        sb.append(", reason=").append(reason);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}