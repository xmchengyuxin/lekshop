package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class UmsShopFreightTemplateArea implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "运费模板ID")
    private Integer templateId;

    @ApiModelProperty(value = "首件(几件内运费)")
    private BigDecimal firstNum;

    @ApiModelProperty(value = "首件价格(几件内运费为多少)")
    private BigDecimal firstPrice;

    @ApiModelProperty(value = "续件(每续几件运费)")
    private BigDecimal secondNum;

    @ApiModelProperty(value = "续件价格(每续几件内运费为多少)")
    private BigDecimal secondPrice;

    @ApiModelProperty(value = "省")
    private String province;

    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String city;

    private String cityCode;

    @ApiModelProperty(value = "区")
    private String area;

    private String areaCode;

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

    public BigDecimal getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(BigDecimal firstNum) {
        this.firstNum = firstNum;
    }

    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    public BigDecimal getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(BigDecimal secondNum) {
        this.secondNum = secondNum;
    }

    public BigDecimal getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(BigDecimal secondPrice) {
        this.secondPrice = secondPrice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateId=").append(templateId);
        sb.append(", firstNum=").append(firstNum);
        sb.append(", firstPrice=").append(firstPrice);
        sb.append(", secondNum=").append(secondNum);
        sb.append(", secondPrice=").append(secondPrice);
        sb.append(", province=").append(province);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", city=").append(city);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", area=").append(area);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}