package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConfigRealname implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "会员组别")
    private Integer groupId;

    @ApiModelProperty(value = "会员组别")
    private String groupName;

    @ApiModelProperty(value = "是否开启实名认证>>0否>>1是")
    private Integer realnameStatus;

    @ApiModelProperty(value = "实名时是否需要手机短信验证>>0否>>1是")
    private Integer realnameNeedPhoneCode;

    @ApiModelProperty(value = "实名时是否需要上传手持相片>>0否>>1是")
    private Integer needHandImg;

    @ApiModelProperty(value = "是否开启银行认证>>0否>>1是")
    private Integer bankStatus;

    @ApiModelProperty(value = "认证时是否需要手机短信验证>>0否>>1是")
    private Integer bankNeedPhoneCode;

    @ApiModelProperty(value = "银行认证通过也同步实名通过>>0否>>1是")
    private Integer synRealname;

    @ApiModelProperty(value = "第三方接口实时实名认证>>0否>>1是")
    private Integer thirdRealnameStatus;

    @ApiModelProperty(value = "第三方接口实时银行认证>>0否>>1是")
    private Integer thirdBankStatus;

    @ApiModelProperty(value = "是否验证银行预留手机号>>0否>>1是")
    private Integer bankPhoneStatus;

    @ApiModelProperty(value = "第几次起收费")
    private Integer chargeNum;

    @ApiModelProperty(value = "收费金额/元/次")
    private BigDecimal chargeFee;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "微信账号>>0非必填>>1必填")
    private Integer weixinNeed;

    @ApiModelProperty(value = "支付宝账号>>0非必填>>1必填")
    private Integer zfbNeed;

    @ApiModelProperty(value = "实名和银行卡户名是否要一致>>0否>>1是")
    private Integer realnameBankSame;

    @ApiModelProperty(value = "微信收款码>>0非必填>>1必填")
    private Integer weixinImgNeed;

    @ApiModelProperty(value = "支付宝收款码>>0非必填>>1必填")
    private Integer zfbImgNeed;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getRealnameStatus() {
        return realnameStatus;
    }

    public void setRealnameStatus(Integer realnameStatus) {
        this.realnameStatus = realnameStatus;
    }

    public Integer getRealnameNeedPhoneCode() {
        return realnameNeedPhoneCode;
    }

    public void setRealnameNeedPhoneCode(Integer realnameNeedPhoneCode) {
        this.realnameNeedPhoneCode = realnameNeedPhoneCode;
    }

    public Integer getNeedHandImg() {
        return needHandImg;
    }

    public void setNeedHandImg(Integer needHandImg) {
        this.needHandImg = needHandImg;
    }

    public Integer getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(Integer bankStatus) {
        this.bankStatus = bankStatus;
    }

    public Integer getBankNeedPhoneCode() {
        return bankNeedPhoneCode;
    }

    public void setBankNeedPhoneCode(Integer bankNeedPhoneCode) {
        this.bankNeedPhoneCode = bankNeedPhoneCode;
    }

    public Integer getSynRealname() {
        return synRealname;
    }

    public void setSynRealname(Integer synRealname) {
        this.synRealname = synRealname;
    }

    public Integer getThirdRealnameStatus() {
        return thirdRealnameStatus;
    }

    public void setThirdRealnameStatus(Integer thirdRealnameStatus) {
        this.thirdRealnameStatus = thirdRealnameStatus;
    }

    public Integer getThirdBankStatus() {
        return thirdBankStatus;
    }

    public void setThirdBankStatus(Integer thirdBankStatus) {
        this.thirdBankStatus = thirdBankStatus;
    }

    public Integer getBankPhoneStatus() {
        return bankPhoneStatus;
    }

    public void setBankPhoneStatus(Integer bankPhoneStatus) {
        this.bankPhoneStatus = bankPhoneStatus;
    }

    public Integer getChargeNum() {
        return chargeNum;
    }

    public void setChargeNum(Integer chargeNum) {
        this.chargeNum = chargeNum;
    }

    public BigDecimal getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(BigDecimal chargeFee) {
        this.chargeFee = chargeFee;
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

    public Integer getWeixinNeed() {
        return weixinNeed;
    }

    public void setWeixinNeed(Integer weixinNeed) {
        this.weixinNeed = weixinNeed;
    }

    public Integer getZfbNeed() {
        return zfbNeed;
    }

    public void setZfbNeed(Integer zfbNeed) {
        this.zfbNeed = zfbNeed;
    }

    public Integer getRealnameBankSame() {
        return realnameBankSame;
    }

    public void setRealnameBankSame(Integer realnameBankSame) {
        this.realnameBankSame = realnameBankSame;
    }

    public Integer getWeixinImgNeed() {
        return weixinImgNeed;
    }

    public void setWeixinImgNeed(Integer weixinImgNeed) {
        this.weixinImgNeed = weixinImgNeed;
    }

    public Integer getZfbImgNeed() {
        return zfbImgNeed;
    }

    public void setZfbImgNeed(Integer zfbImgNeed) {
        this.zfbImgNeed = zfbImgNeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", realnameStatus=").append(realnameStatus);
        sb.append(", realnameNeedPhoneCode=").append(realnameNeedPhoneCode);
        sb.append(", needHandImg=").append(needHandImg);
        sb.append(", bankStatus=").append(bankStatus);
        sb.append(", bankNeedPhoneCode=").append(bankNeedPhoneCode);
        sb.append(", synRealname=").append(synRealname);
        sb.append(", thirdRealnameStatus=").append(thirdRealnameStatus);
        sb.append(", thirdBankStatus=").append(thirdBankStatus);
        sb.append(", bankPhoneStatus=").append(bankPhoneStatus);
        sb.append(", chargeNum=").append(chargeNum);
        sb.append(", chargeFee=").append(chargeFee);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", weixinNeed=").append(weixinNeed);
        sb.append(", zfbNeed=").append(zfbNeed);
        sb.append(", realnameBankSame=").append(realnameBankSame);
        sb.append(", weixinImgNeed=").append(weixinImgNeed);
        sb.append(", zfbImgNeed=").append(zfbImgNeed);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}