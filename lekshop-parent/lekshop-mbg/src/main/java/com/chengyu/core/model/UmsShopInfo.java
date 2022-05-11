package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsShopInfo implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "店铺类型>>1个人>>2企业>>3旗舰")
    private Integer type;

    @ApiModelProperty(value = "企业名/姓名")
    private String realname;

    @ApiModelProperty(value = "身份证/统一信用")
    private String idcard;

    @ApiModelProperty(value = "证件图片")
    private String cardImg;

    @ApiModelProperty(value = "商标证书")
    private String brandImg;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String weixin;

    @ApiModelProperty(value = "店铺名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String produce;

    @ApiModelProperty(value = "LOGO")
    private String logo;

    @ApiModelProperty(value = "省")
    private String province;

    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String city;

    private String cityCode;

    @ApiModelProperty(value = "区")
    private String area;

    private String areaCode;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "状态(0已提交资质>>1完善店铺>>2审核中>>3通过>>4驳回)")
    private Integer status;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "描述")
    private String remark;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", type=").append(type);
        sb.append(", realname=").append(realname);
        sb.append(", idcard=").append(idcard);
        sb.append(", cardImg=").append(cardImg);
        sb.append(", brandImg=").append(brandImg);
        sb.append(", phone=").append(phone);
        sb.append(", weixin=").append(weixin);
        sb.append(", name=").append(name);
        sb.append(", produce=").append(produce);
        sb.append(", logo=").append(logo);
        sb.append(", province=").append(province);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", city=").append(city);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", area=").append(area);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append(", reason=").append(reason);
        sb.append(", remark=").append(remark);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}