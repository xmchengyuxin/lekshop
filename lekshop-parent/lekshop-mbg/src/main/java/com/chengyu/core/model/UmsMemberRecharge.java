package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsMemberRecharge implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "单号")
    private String orderNo;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "支付方式")
    private String paymethod;

    @ApiModelProperty(value = "付款截图")
    private String img;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "打款银行")
    private String remitBank;

    @ApiModelProperty(value = "打款姓名")
    private String remitRealname;

    @ApiModelProperty(value = "打款账号(后四位)")
    private String remitAccount;

    @ApiModelProperty(value = "收款ID")
    private Integer configAccountId;

    @ApiModelProperty(value = "存款时间")
    private Date saveTime;

    @ApiModelProperty(value = "审核时间")
    private Date verifyTime;

    @ApiModelProperty(value = "审核人")
    private String verifyUser;

    @ApiModelProperty(value = "状态 0待审核 1审核通过 2审核不通过 3取消")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public String getRemitBank() {
        return remitBank;
    }

    public void setRemitBank(String remitBank) {
        this.remitBank = remitBank;
    }

    public String getRemitRealname() {
        return remitRealname;
    }

    public void setRemitRealname(String remitRealname) {
        this.remitRealname = remitRealname;
    }

    public String getRemitAccount() {
        return remitAccount;
    }

    public void setRemitAccount(String remitAccount) {
        this.remitAccount = remitAccount;
    }

    public Integer getConfigAccountId() {
        return configAccountId;
    }

    public void setConfigAccountId(Integer configAccountId) {
        this.configAccountId = configAccountId;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", realname=").append(realname);
        sb.append(", paymethod=").append(paymethod);
        sb.append(", img=").append(img);
        sb.append(", amount=").append(amount);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", remitBank=").append(remitBank);
        sb.append(", remitRealname=").append(remitRealname);
        sb.append(", remitAccount=").append(remitAccount);
        sb.append(", configAccountId=").append(configAccountId);
        sb.append(", saveTime=").append(saveTime);
        sb.append(", verifyTime=").append(verifyTime);
        sb.append(", verifyUser=").append(verifyUser);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}