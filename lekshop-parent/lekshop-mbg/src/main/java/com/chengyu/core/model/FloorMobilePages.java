package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class FloorMobilePages implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "模板Id")
    private Integer templateId;

    @ApiModelProperty(value = "模块标识")
    private String moduleKey;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    @ApiModelProperty(value = "模块数据")
    private String moduleData;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getModuleData() {
        return moduleData;
    }

    public void setModuleData(String moduleData) {
        this.moduleData = moduleData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateId=").append(templateId);
        sb.append(", moduleKey=").append(moduleKey);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", sort=").append(sort);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", moduleData=").append(moduleData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}