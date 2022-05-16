package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

public class UmsShopFreightTemplate implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "商家ID(0为系统自营）")
    private Integer shopId;

    @ApiModelProperty(value = "商家名称")
    private String shopName;

    @ApiModelProperty(value = "模板名称")
    private String name;

    @ApiModelProperty(value = "模板类型（1买家承担运费 2卖家包邮）")
    private Integer type;

    @ApiModelProperty(value = "计价方式(1按件数 2按重量 3按体积)")
    private Integer valuteType;

    @ApiModelProperty(value = "首件(几件内运费)")
    private BigDecimal defaultFirstNum;

    @ApiModelProperty(value = "首件价格(几件内运费为多少)")
    private BigDecimal defaultFirstPrice;

    @ApiModelProperty(value = "续件(每续几件运费)")
    private BigDecimal defaultSecondNum;

    @ApiModelProperty(value = "续件价格(每续几件内运费为多少)")
    private BigDecimal defaultSecondPrice;

    @ApiModelProperty(value = "发货时间")
    private String deliveryTime;

    @ApiModelProperty(value = "省代码")
    private String provinceCode;

    @ApiModelProperty(value = "市代码")
    private String cityCode;

    @ApiModelProperty(value = "区代码")
    private String areaCode;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getValuteType() {
        return valuteType;
    }

    public void setValuteType(Integer valuteType) {
        this.valuteType = valuteType;
    }

    public BigDecimal getDefaultFirstNum() {
        return defaultFirstNum;
    }

    public void setDefaultFirstNum(BigDecimal defaultFirstNum) {
        this.defaultFirstNum = defaultFirstNum;
    }

    public BigDecimal getDefaultFirstPrice() {
        return defaultFirstPrice;
    }

    public void setDefaultFirstPrice(BigDecimal defaultFirstPrice) {
        this.defaultFirstPrice = defaultFirstPrice;
    }

    public BigDecimal getDefaultSecondNum() {
        return defaultSecondNum;
    }

    public void setDefaultSecondNum(BigDecimal defaultSecondNum) {
        this.defaultSecondNum = defaultSecondNum;
    }

    public BigDecimal getDefaultSecondPrice() {
        return defaultSecondPrice;
    }

    public void setDefaultSecondPrice(BigDecimal defaultSecondPrice) {
        this.defaultSecondPrice = defaultSecondPrice;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", valuteType=").append(valuteType);
        sb.append(", defaultFirstNum=").append(defaultFirstNum);
        sb.append(", defaultFirstPrice=").append(defaultFirstPrice);
        sb.append(", defaultSecondNum=").append(defaultSecondNum);
        sb.append(", defaultSecondPrice=").append(defaultSecondPrice);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}