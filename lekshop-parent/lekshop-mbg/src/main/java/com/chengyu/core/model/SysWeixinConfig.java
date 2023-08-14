package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysWeixinConfig implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "开放平台APPID")
    private String kfAppId;

    @ApiModelProperty(value = "开放平台密钥")
    private String kfSecret;

    @ApiModelProperty(value = "小程序APPID")
    private String xcxAppId;

    @ApiModelProperty(value = "小程序密钥")
    private String xcxSecret;

    @ApiModelProperty(value = "公众号APPID")
    private String gzhAppId;

    @ApiModelProperty(value = "公众号密钥")
    private String gzhSecret;

    @ApiModelProperty(value = "微信商户号")
    private String mchId;

    @ApiModelProperty(value = "微信商户密钥")
    private String mchKey;

    @ApiModelProperty(value = "支付回调")
    private String payNotifyUrl;

    @ApiModelProperty(value = "商户证书地址")
    private String certPath;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "同步回调")
    private String notifyUrl;

    @ApiModelProperty(value = "商户证书序列号")
    private String merchantSerialNumber;

    @ApiModelProperty(value = "商户APIV3密钥")
    private String apiV3Key;

    @ApiModelProperty(value = "商户API私钥路径")
    private String privateKeyPath;

    @ApiModelProperty(value = "证书路径")
    private String wechatPayCertificatePath;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKfAppId() {
        return kfAppId;
    }

    public void setKfAppId(String kfAppId) {
        this.kfAppId = kfAppId;
    }

    public String getKfSecret() {
        return kfSecret;
    }

    public void setKfSecret(String kfSecret) {
        this.kfSecret = kfSecret;
    }

    public String getXcxAppId() {
        return xcxAppId;
    }

    public void setXcxAppId(String xcxAppId) {
        this.xcxAppId = xcxAppId;
    }

    public String getXcxSecret() {
        return xcxSecret;
    }

    public void setXcxSecret(String xcxSecret) {
        this.xcxSecret = xcxSecret;
    }

    public String getGzhAppId() {
        return gzhAppId;
    }

    public void setGzhAppId(String gzhAppId) {
        this.gzhAppId = gzhAppId;
    }

    public String getGzhSecret() {
        return gzhSecret;
    }

    public void setGzhSecret(String gzhSecret) {
        this.gzhSecret = gzhSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    public void setMerchantSerialNumber(String merchantSerialNumber) {
        this.merchantSerialNumber = merchantSerialNumber;
    }

    public String getApiV3Key() {
        return apiV3Key;
    }

    public void setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getWechatPayCertificatePath() {
        return wechatPayCertificatePath;
    }

    public void setWechatPayCertificatePath(String wechatPayCertificatePath) {
        this.wechatPayCertificatePath = wechatPayCertificatePath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", kfAppId=").append(kfAppId);
        sb.append(", kfSecret=").append(kfSecret);
        sb.append(", xcxAppId=").append(xcxAppId);
        sb.append(", xcxSecret=").append(xcxSecret);
        sb.append(", gzhAppId=").append(gzhAppId);
        sb.append(", gzhSecret=").append(gzhSecret);
        sb.append(", mchId=").append(mchId);
        sb.append(", mchKey=").append(mchKey);
        sb.append(", payNotifyUrl=").append(payNotifyUrl);
        sb.append(", certPath=").append(certPath);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", notifyUrl=").append(notifyUrl);
        sb.append(", merchantSerialNumber=").append(merchantSerialNumber);
        sb.append(", apiV3Key=").append(apiV3Key);
        sb.append(", privateKeyPath=").append(privateKeyPath);
        sb.append(", wechatPayCertificatePath=").append(wechatPayCertificatePath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}