package com.chengyu.core.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ShopSearchForm {

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

	@ApiModelProperty(value = "手机号")
	private String phone;

	@ApiModelProperty(value = "微信号")
	private String weixin;

	@ApiModelProperty(value = "店铺名称")
	private String name;

	@ApiModelProperty(value = "状态(0已提交资质>>1完善店铺>>2审核中>>3通过>>4驳回)")
	private Integer status;

}