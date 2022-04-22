package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConfigWithdraw implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "会员组别")
    private Integer groupId;

    @ApiModelProperty(value = "会员组别")
    private String groupName;

    @ApiModelProperty(value = "是否开启提现功能>>0否>>1是")
    private Integer withdrawStatus;

    @ApiModelProperty(value = "是否允许佣金转入账户余额>>0否>>1是")
    private Integer missionToBalance;

    @ApiModelProperty(value = "允许提现资金类型>>0全部>>1账户余额>>2账户佣金")
    private Integer withdrawType;

    @ApiModelProperty(value = "允许提现付款方式>>1支付宝>>2微信>>3银行")
    private String withdrawMethod;

    @ApiModelProperty(value = "是否实名认证才可提现>>0否>>1是")
    private Integer needRealname;

    @ApiModelProperty(value = "是否银行认证才可提现>>0否>>1是")
    private Integer needBank;

    @ApiModelProperty(value = "是否要接收手机验证码>>0否>>1是")
    private Integer needPhoneCode;

    @ApiModelProperty(value = "账户余额提现最低金额")
    private BigDecimal minBalanceWithdrawAmount;

    @ApiModelProperty(value = "账户余额提现最高金额")
    private BigDecimal maxBalanceWithdrawAmount;

    @ApiModelProperty(value = "账户佣金提现最低金额")
    private BigDecimal minMissionWithdrawAmount;

    @ApiModelProperty(value = "账户佣金提现最高金额")
    private BigDecimal maxMissionWithdrawAmount;

    @ApiModelProperty(value = "每天允许提现次数")
    private Integer withdrawDayNum;

    @ApiModelProperty(value = "向你提现的支付宝账号")
    private String zfbAccount;

    @ApiModelProperty(value = "支付宝姓名")
    private String zfbRealname;

    @ApiModelProperty(value = "支付宝提现手续费率")
    private BigDecimal zfbRate;

    @ApiModelProperty(value = "单笔最低手续费")
    private BigDecimal zfbMinFee;

    @ApiModelProperty(value = "微信提现手续费率")
    private BigDecimal wxRate;

    @ApiModelProperty(value = "单笔最低手续费")
    private BigDecimal wxMinFee;

    @ApiModelProperty(value = "银行提现手续费率")
    private BigDecimal bankRate;

    @ApiModelProperty(value = "单笔最低手续费")
    private BigDecimal bankMinFee;

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

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public Integer getMissionToBalance() {
        return missionToBalance;
    }

    public void setMissionToBalance(Integer missionToBalance) {
        this.missionToBalance = missionToBalance;
    }

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getWithdrawMethod() {
        return withdrawMethod;
    }

    public void setWithdrawMethod(String withdrawMethod) {
        this.withdrawMethod = withdrawMethod;
    }

    public Integer getNeedRealname() {
        return needRealname;
    }

    public void setNeedRealname(Integer needRealname) {
        this.needRealname = needRealname;
    }

    public Integer getNeedBank() {
        return needBank;
    }

    public void setNeedBank(Integer needBank) {
        this.needBank = needBank;
    }

    public Integer getNeedPhoneCode() {
        return needPhoneCode;
    }

    public void setNeedPhoneCode(Integer needPhoneCode) {
        this.needPhoneCode = needPhoneCode;
    }

    public BigDecimal getMinBalanceWithdrawAmount() {
        return minBalanceWithdrawAmount;
    }

    public void setMinBalanceWithdrawAmount(BigDecimal minBalanceWithdrawAmount) {
        this.minBalanceWithdrawAmount = minBalanceWithdrawAmount;
    }

    public BigDecimal getMaxBalanceWithdrawAmount() {
        return maxBalanceWithdrawAmount;
    }

    public void setMaxBalanceWithdrawAmount(BigDecimal maxBalanceWithdrawAmount) {
        this.maxBalanceWithdrawAmount = maxBalanceWithdrawAmount;
    }

    public BigDecimal getMinMissionWithdrawAmount() {
        return minMissionWithdrawAmount;
    }

    public void setMinMissionWithdrawAmount(BigDecimal minMissionWithdrawAmount) {
        this.minMissionWithdrawAmount = minMissionWithdrawAmount;
    }

    public BigDecimal getMaxMissionWithdrawAmount() {
        return maxMissionWithdrawAmount;
    }

    public void setMaxMissionWithdrawAmount(BigDecimal maxMissionWithdrawAmount) {
        this.maxMissionWithdrawAmount = maxMissionWithdrawAmount;
    }

    public Integer getWithdrawDayNum() {
        return withdrawDayNum;
    }

    public void setWithdrawDayNum(Integer withdrawDayNum) {
        this.withdrawDayNum = withdrawDayNum;
    }

    public String getZfbAccount() {
        return zfbAccount;
    }

    public void setZfbAccount(String zfbAccount) {
        this.zfbAccount = zfbAccount;
    }

    public String getZfbRealname() {
        return zfbRealname;
    }

    public void setZfbRealname(String zfbRealname) {
        this.zfbRealname = zfbRealname;
    }

    public BigDecimal getZfbRate() {
        return zfbRate;
    }

    public void setZfbRate(BigDecimal zfbRate) {
        this.zfbRate = zfbRate;
    }

    public BigDecimal getZfbMinFee() {
        return zfbMinFee;
    }

    public void setZfbMinFee(BigDecimal zfbMinFee) {
        this.zfbMinFee = zfbMinFee;
    }

    public BigDecimal getWxRate() {
        return wxRate;
    }

    public void setWxRate(BigDecimal wxRate) {
        this.wxRate = wxRate;
    }

    public BigDecimal getWxMinFee() {
        return wxMinFee;
    }

    public void setWxMinFee(BigDecimal wxMinFee) {
        this.wxMinFee = wxMinFee;
    }

    public BigDecimal getBankRate() {
        return bankRate;
    }

    public void setBankRate(BigDecimal bankRate) {
        this.bankRate = bankRate;
    }

    public BigDecimal getBankMinFee() {
        return bankMinFee;
    }

    public void setBankMinFee(BigDecimal bankMinFee) {
        this.bankMinFee = bankMinFee;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", withdrawStatus=").append(withdrawStatus);
        sb.append(", missionToBalance=").append(missionToBalance);
        sb.append(", withdrawType=").append(withdrawType);
        sb.append(", withdrawMethod=").append(withdrawMethod);
        sb.append(", needRealname=").append(needRealname);
        sb.append(", needBank=").append(needBank);
        sb.append(", needPhoneCode=").append(needPhoneCode);
        sb.append(", minBalanceWithdrawAmount=").append(minBalanceWithdrawAmount);
        sb.append(", maxBalanceWithdrawAmount=").append(maxBalanceWithdrawAmount);
        sb.append(", minMissionWithdrawAmount=").append(minMissionWithdrawAmount);
        sb.append(", maxMissionWithdrawAmount=").append(maxMissionWithdrawAmount);
        sb.append(", withdrawDayNum=").append(withdrawDayNum);
        sb.append(", zfbAccount=").append(zfbAccount);
        sb.append(", zfbRealname=").append(zfbRealname);
        sb.append(", zfbRate=").append(zfbRate);
        sb.append(", zfbMinFee=").append(zfbMinFee);
        sb.append(", wxRate=").append(wxRate);
        sb.append(", wxMinFee=").append(wxMinFee);
        sb.append(", bankRate=").append(bankRate);
        sb.append(", bankMinFee=").append(bankMinFee);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}