package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsMemberRel implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "层级关系")
    private Integer type;

    @ApiModelProperty(value = "上级")
    private Integer upMemberId;

    @ApiModelProperty(value = "下级")
    private Integer downMemberId;

    private Date addTime;

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

    public Integer getUpMemberId() {
        return upMemberId;
    }

    public void setUpMemberId(Integer upMemberId) {
        this.upMemberId = upMemberId;
    }

    public Integer getDownMemberId() {
        return downMemberId;
    }

    public void setDownMemberId(Integer downMemberId) {
        this.downMemberId = downMemberId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", upMemberId=").append(upMemberId);
        sb.append(", downMemberId=").append(downMemberId);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}