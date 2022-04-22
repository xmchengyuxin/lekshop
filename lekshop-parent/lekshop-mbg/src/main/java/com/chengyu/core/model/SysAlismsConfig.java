package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysAlismsConfig implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "请求链接")
    private String sendUrl;

    @ApiModelProperty(value = "请求端口")
    private String serverPort;

    @ApiModelProperty(value = "应用ID")
    private String signName;

    @ApiModelProperty(value = "注册模板ID")
    private String registerTemplateId;

    @ApiModelProperty(value = "绑定模板ID")
    private String bindTemplateId;

    @ApiModelProperty(value = "忘记密码模板ID")
    private String forgetTemplateId;

    @ApiModelProperty(value = "通用模板ID")
    private String commonTemplateId;

    @ApiModelProperty(value = "员工密码通知模板")
    private String passwordTemplateId;

    @ApiModelProperty(value = "配送通知")
    private String deliveryTemplateId;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "短信类型")
    private String smsType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getRegisterTemplateId() {
        return registerTemplateId;
    }

    public void setRegisterTemplateId(String registerTemplateId) {
        this.registerTemplateId = registerTemplateId;
    }

    public String getBindTemplateId() {
        return bindTemplateId;
    }

    public void setBindTemplateId(String bindTemplateId) {
        this.bindTemplateId = bindTemplateId;
    }

    public String getForgetTemplateId() {
        return forgetTemplateId;
    }

    public void setForgetTemplateId(String forgetTemplateId) {
        this.forgetTemplateId = forgetTemplateId;
    }

    public String getCommonTemplateId() {
        return commonTemplateId;
    }

    public void setCommonTemplateId(String commonTemplateId) {
        this.commonTemplateId = commonTemplateId;
    }

    public String getPasswordTemplateId() {
        return passwordTemplateId;
    }

    public void setPasswordTemplateId(String passwordTemplateId) {
        this.passwordTemplateId = passwordTemplateId;
    }

    public String getDeliveryTemplateId() {
        return deliveryTemplateId;
    }

    public void setDeliveryTemplateId(String deliveryTemplateId) {
        this.deliveryTemplateId = deliveryTemplateId;
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

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", sendUrl=").append(sendUrl);
        sb.append(", serverPort=").append(serverPort);
        sb.append(", signName=").append(signName);
        sb.append(", registerTemplateId=").append(registerTemplateId);
        sb.append(", bindTemplateId=").append(bindTemplateId);
        sb.append(", forgetTemplateId=").append(forgetTemplateId);
        sb.append(", commonTemplateId=").append(commonTemplateId);
        sb.append(", passwordTemplateId=").append(passwordTemplateId);
        sb.append(", deliveryTemplateId=").append(deliveryTemplateId);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", smsType=").append(smsType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}