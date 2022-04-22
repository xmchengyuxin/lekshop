package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsMemberGroup implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "1买家组别>>2卖家组别")
    private Integer type;

    @ApiModelProperty(value = "组别名称")
    private String name;

    @ApiModelProperty(value = "icon")
    private String icon;

    @ApiModelProperty(value = "0不默认>>1默认")
    private Integer defaultStatus;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "升级所需积分")
    private BigDecimal upPoint;

    @ApiModelProperty(value = "初始金额")
    private BigDecimal initAmount;

    @ApiModelProperty(value = "初始佣金")
    private BigDecimal initMission;

    @ApiModelProperty(value = "初始积分")
    private BigDecimal initPoint;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "0关闭>>1开启")
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getUpPoint() {
        return upPoint;
    }

    public void setUpPoint(BigDecimal upPoint) {
        this.upPoint = upPoint;
    }

    public BigDecimal getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(BigDecimal initAmount) {
        this.initAmount = initAmount;
    }

    public BigDecimal getInitMission() {
        return initMission;
    }

    public void setInitMission(BigDecimal initMission) {
        this.initMission = initMission;
    }

    public BigDecimal getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(BigDecimal initPoint) {
        this.initPoint = initPoint;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", icon=").append(icon);
        sb.append(", defaultStatus=").append(defaultStatus);
        sb.append(", level=").append(level);
        sb.append(", upPoint=").append(upPoint);
        sb.append(", initAmount=").append(initAmount);
        sb.append(", initMission=").append(initMission);
        sb.append(", initPoint=").append(initPoint);
        sb.append(", sort=").append(sort);
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