package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsShopConfig implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "店铺ID")
    private Integer shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "类目样式>>1一级类目-大图>>2一级类目-九宫格>>3二级类目>>4多级类目样式")
    private Integer cateStyle;

    @ApiModelProperty(value = "IM欢迎消息")
    private String welcome;

    @ApiModelProperty(value = "是否开启云打印")
    private Integer kdPrint;

    @ApiModelProperty(value = "是否云打印自动发货")
    private Integer kdDelivery;

    @ApiModelProperty(value = "密钥")
    private String kdSecret;

    @ApiModelProperty(value = "授权KEY")
    private String kdKey;

    @ApiModelProperty(value = "面单账号")
    private String kdAccount;

    @ApiModelProperty(value = "面单密码")
    private String kdPassword;

    @ApiModelProperty(value = "打印设备码")
    private String kdEquipmentId;

    @ApiModelProperty(value = "模板ID")
    private String kdTemplateId;

    @ApiModelProperty(value = "寄件人")
    private String sendName;

    @ApiModelProperty(value = "寄件电话")
    private String sendPhone;

    @ApiModelProperty(value = "寄件地址")
    private String sendAddress;

    @ApiModelProperty(value = "退货收件人")
    private String returnName;

    @ApiModelProperty(value = "退货电话")
    private String returnPhone;

    @ApiModelProperty(value = "退货地址")
    private String returnAddress;

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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getCateStyle() {
        return cateStyle;
    }

    public void setCateStyle(Integer cateStyle) {
        this.cateStyle = cateStyle;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public Integer getKdPrint() {
        return kdPrint;
    }

    public void setKdPrint(Integer kdPrint) {
        this.kdPrint = kdPrint;
    }

    public Integer getKdDelivery() {
        return kdDelivery;
    }

    public void setKdDelivery(Integer kdDelivery) {
        this.kdDelivery = kdDelivery;
    }

    public String getKdSecret() {
        return kdSecret;
    }

    public void setKdSecret(String kdSecret) {
        this.kdSecret = kdSecret;
    }

    public String getKdKey() {
        return kdKey;
    }

    public void setKdKey(String kdKey) {
        this.kdKey = kdKey;
    }

    public String getKdAccount() {
        return kdAccount;
    }

    public void setKdAccount(String kdAccount) {
        this.kdAccount = kdAccount;
    }

    public String getKdPassword() {
        return kdPassword;
    }

    public void setKdPassword(String kdPassword) {
        this.kdPassword = kdPassword;
    }

    public String getKdEquipmentId() {
        return kdEquipmentId;
    }

    public void setKdEquipmentId(String kdEquipmentId) {
        this.kdEquipmentId = kdEquipmentId;
    }

    public String getKdTemplateId() {
        return kdTemplateId;
    }

    public void setKdTemplateId(String kdTemplateId) {
        this.kdTemplateId = kdTemplateId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public String getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(String returnAddress) {
        this.returnAddress = returnAddress;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", cateStyle=").append(cateStyle);
        sb.append(", welcome=").append(welcome);
        sb.append(", kdPrint=").append(kdPrint);
        sb.append(", kdDelivery=").append(kdDelivery);
        sb.append(", kdSecret=").append(kdSecret);
        sb.append(", kdKey=").append(kdKey);
        sb.append(", kdAccount=").append(kdAccount);
        sb.append(", kdPassword=").append(kdPassword);
        sb.append(", kdEquipmentId=").append(kdEquipmentId);
        sb.append(", kdTemplateId=").append(kdTemplateId);
        sb.append(", sendName=").append(sendName);
        sb.append(", sendPhone=").append(sendPhone);
        sb.append(", sendAddress=").append(sendAddress);
        sb.append(", returnName=").append(returnName);
        sb.append(", returnPhone=").append(returnPhone);
        sb.append(", returnAddress=").append(returnAddress);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}