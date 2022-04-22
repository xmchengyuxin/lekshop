package com.chengyu.core.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsMember implements Serializable {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户唯一id")
    private Integer uid;

    @ApiModelProperty(value = "会员组别")
    private Integer groupId;

    @ApiModelProperty(value = "会员组别")
    private String groupName;

    @ApiModelProperty(value = "组别ICON")
    private String groupIcon;

    @ApiModelProperty(value = "1买家>>2商家")
    private Integer type;

    @ApiModelProperty(value = "用户名")
    private String code;

    @ApiModelProperty(value = "开放平台openID")
    private String kfOpenId;

    @ApiModelProperty(value = "小程序openid")
    private String xcxOpenId;

    @ApiModelProperty(value = "公众号openId")
    private String gzhOpenId;

    @ApiModelProperty(value = "用户唯一标识")
    private String unionid;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "微信名称")
    private String weixin;

    @ApiModelProperty(value = "1男 2女")
    private String gender;

    @ApiModelProperty(value = "姓名")
    private String realname;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "年齡")
    private Integer age;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "支付密码")
    private String payPassword;

    @ApiModelProperty(value = "省")
    private String province;

    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String city;

    private String cityCode;

    @ApiModelProperty(value = "区")
    private String area;

    private String areaCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "个人签名")
    private String personSign;

    @ApiModelProperty(value = "注册方式(1网页端 2APP 3邀请链接 4未知)")
    private Integer method;

    @ApiModelProperty(value = "用户状态(-1:停用 1:正常)")
    private Integer status;

    @ApiModelProperty(value = "手机认证状态(0:未认证 1:已认证)")
    private Integer phoneStatus;

    @ApiModelProperty(value = "实名认证状态(0:未认证 1:已认证)")
    private Integer realnameStatus;

    @ApiModelProperty(value = "银行认证状态(0:未认证 1:已认证)")
    private Integer bankStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "推荐人ID")
    private Integer tjrId;

    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

    @ApiModelProperty(value = "注册IP")
    private String registerIp;

    @ApiModelProperty(value = "注册IP实际地址")
    private String registerIpAddress;

    @ApiModelProperty(value = "注册链接")
    private String registerUrl;

    @ApiModelProperty(value = "佣金组ID")
    private Integer missionConfigId;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "添加人")
    private String addBy;

    @ApiModelProperty(value = "修改时间")
    private Date updTime;

    @ApiModelProperty(value = "修改人")
    private String updBy;

    private String registrationId;

    @ApiModelProperty(value = "密保问题")
    private String secret;

    @ApiModelProperty(value = "密保问题答案")
    private String secretAnswer;

    @ApiModelProperty(value = "处罚状态>>0未处罚>>1已处罚")
    private Integer punishmentStatus;

    @ApiModelProperty(value = "推荐人Uid")
    private Integer tjrUid;

    @ApiModelProperty(value = "设备ID")
    private String imeId;

    @ApiModelProperty(value = "0不可邀请>>1可邀请")
    private Integer inviteStatus;

    @ApiModelProperty(value = "冻结理由")
    private String freezeReason;

    @ApiModelProperty(value = "0未限制>>1已限制提现")
    private Integer withdrawLimit;

    @ApiModelProperty(value = "0不播放>>1播放")
    private Integer remindVoice;

    @ApiModelProperty(value = "是否关注公众号>>0否>>1是")
    private Integer gzhFocusStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKfOpenId() {
        return kfOpenId;
    }

    public void setKfOpenId(String kfOpenId) {
        this.kfOpenId = kfOpenId;
    }

    public String getXcxOpenId() {
        return xcxOpenId;
    }

    public void setXcxOpenId(String xcxOpenId) {
        this.xcxOpenId = xcxOpenId;
    }

    public String getGzhOpenId() {
        return gzhOpenId;
    }

    public void setGzhOpenId(String gzhOpenId) {
        this.gzhOpenId = gzhOpenId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
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

    public String getPersonSign() {
        return personSign;
    }

    public void setPersonSign(String personSign) {
        this.personSign = personSign;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(Integer phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public Integer getRealnameStatus() {
        return realnameStatus;
    }

    public void setRealnameStatus(Integer realnameStatus) {
        this.realnameStatus = realnameStatus;
    }

    public Integer getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(Integer bankStatus) {
        this.bankStatus = bankStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTjrId() {
        return tjrId;
    }

    public void setTjrId(Integer tjrId) {
        this.tjrId = tjrId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterIpAddress() {
        return registerIpAddress;
    }

    public void setRegisterIpAddress(String registerIpAddress) {
        this.registerIpAddress = registerIpAddress;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public Integer getMissionConfigId() {
        return missionConfigId;
    }

    public void setMissionConfigId(Integer missionConfigId) {
        this.missionConfigId = missionConfigId;
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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public Integer getPunishmentStatus() {
        return punishmentStatus;
    }

    public void setPunishmentStatus(Integer punishmentStatus) {
        this.punishmentStatus = punishmentStatus;
    }

    public Integer getTjrUid() {
        return tjrUid;
    }

    public void setTjrUid(Integer tjrUid) {
        this.tjrUid = tjrUid;
    }

    public String getImeId() {
        return imeId;
    }

    public void setImeId(String imeId) {
        this.imeId = imeId;
    }

    public Integer getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Integer inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }

    public Integer getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(Integer withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public Integer getRemindVoice() {
        return remindVoice;
    }

    public void setRemindVoice(Integer remindVoice) {
        this.remindVoice = remindVoice;
    }

    public Integer getGzhFocusStatus() {
        return gzhFocusStatus;
    }

    public void setGzhFocusStatus(Integer gzhFocusStatus) {
        this.gzhFocusStatus = gzhFocusStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", groupIcon=").append(groupIcon);
        sb.append(", type=").append(type);
        sb.append(", code=").append(code);
        sb.append(", kfOpenId=").append(kfOpenId);
        sb.append(", xcxOpenId=").append(xcxOpenId);
        sb.append(", gzhOpenId=").append(gzhOpenId);
        sb.append(", unionid=").append(unionid);
        sb.append(", headImg=").append(headImg);
        sb.append(", weixin=").append(weixin);
        sb.append(", gender=").append(gender);
        sb.append(", realname=").append(realname);
        sb.append(", nickname=").append(nickname);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", qq=").append(qq);
        sb.append(", birthday=").append(birthday);
        sb.append(", age=").append(age);
        sb.append(", password=").append(password);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", province=").append(province);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", city=").append(city);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", area=").append(area);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", address=").append(address);
        sb.append(", personSign=").append(personSign);
        sb.append(", method=").append(method);
        sb.append(", status=").append(status);
        sb.append(", phoneStatus=").append(phoneStatus);
        sb.append(", realnameStatus=").append(realnameStatus);
        sb.append(", bankStatus=").append(bankStatus);
        sb.append(", remark=").append(remark);
        sb.append(", tjrId=").append(tjrId);
        sb.append(", inviteCode=").append(inviteCode);
        sb.append(", registerIp=").append(registerIp);
        sb.append(", registerIpAddress=").append(registerIpAddress);
        sb.append(", registerUrl=").append(registerUrl);
        sb.append(", missionConfigId=").append(missionConfigId);
        sb.append(", addTime=").append(addTime);
        sb.append(", addBy=").append(addBy);
        sb.append(", updTime=").append(updTime);
        sb.append(", updBy=").append(updBy);
        sb.append(", registrationId=").append(registrationId);
        sb.append(", secret=").append(secret);
        sb.append(", secretAnswer=").append(secretAnswer);
        sb.append(", punishmentStatus=").append(punishmentStatus);
        sb.append(", tjrUid=").append(tjrUid);
        sb.append(", imeId=").append(imeId);
        sb.append(", inviteStatus=").append(inviteStatus);
        sb.append(", freezeReason=").append(freezeReason);
        sb.append(", withdrawLimit=").append(withdrawLimit);
        sb.append(", remindVoice=").append(remindVoice);
        sb.append(", gzhFocusStatus=").append(gzhFocusStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}