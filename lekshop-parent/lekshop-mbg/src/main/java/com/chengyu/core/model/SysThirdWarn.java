package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysThirdWarn implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "第三方平台")
    private String name;

    @ApiModelProperty(value = "平台标识")
    private String nid;

    @ApiModelProperty(value = "接口标识")
    private String interfaceNid;

    @ApiModelProperty(value = "剩余金额")
    private BigDecimal leftAmount;

    @ApiModelProperty(value = "预警金额")
    private BigDecimal warnAmount;

    @ApiModelProperty(value = "预警手机, 填0不预警")
    private String warnPhone;

    @ApiModelProperty(value = "预警邮箱, 填0不预警")
    private String warnEmail;

    @ApiModelProperty(value = "状态")
    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getInterfaceNid() {
        return interfaceNid;
    }

    public void setInterfaceNid(String interfaceNid) {
        this.interfaceNid = interfaceNid;
    }

    public BigDecimal getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(BigDecimal leftAmount) {
        this.leftAmount = leftAmount;
    }

    public BigDecimal getWarnAmount() {
        return warnAmount;
    }

    public void setWarnAmount(BigDecimal warnAmount) {
        this.warnAmount = warnAmount;
    }

    public String getWarnPhone() {
        return warnPhone;
    }

    public void setWarnPhone(String warnPhone) {
        this.warnPhone = warnPhone;
    }

    public String getWarnEmail() {
        return warnEmail;
    }

    public void setWarnEmail(String warnEmail) {
        this.warnEmail = warnEmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", name=").append(name);
        sb.append(", nid=").append(nid);
        sb.append(", interfaceNid=").append(interfaceNid);
        sb.append(", leftAmount=").append(leftAmount);
        sb.append(", warnAmount=").append(warnAmount);
        sb.append(", warnPhone=").append(warnPhone);
        sb.append(", warnEmail=").append(warnEmail);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}