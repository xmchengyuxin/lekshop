package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class PmsGoodsAttrVal implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "属性ID")
    private Integer attrKeyId;

    @ApiModelProperty(value = "属性名称")
    private String attrKeyName;

    @ApiModelProperty(value = "属性值")
    private String value;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAttrKeyId() {
        return attrKeyId;
    }

    public void setAttrKeyId(Integer attrKeyId) {
        this.attrKeyId = attrKeyId;
    }

    public String getAttrKeyName() {
        return attrKeyName;
    }

    public void setAttrKeyName(String attrKeyName) {
        this.attrKeyName = attrKeyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", attrKeyId=").append(attrKeyId);
        sb.append(", attrKeyName=").append(attrKeyName);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}