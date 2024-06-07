package com.chengyu.core.domain.form;

import com.chengyu.core.model.PmsGoodsGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoodsPublishGroupForm {

	@ApiModelProperty(value = "主键ID")
	private Integer id;

	@ApiModelProperty(value = "1普通拼团>>2阶梯拼团")
	private Integer groupType;

	@ApiModelProperty(value = "成团人数")
	private Integer groupNum;

	@ApiModelProperty(value = "成团限时")
	private Integer groupLimitHours;

	@ApiModelProperty(value = "个人限购次数")
	private Integer groupLimitBuy;

	@ApiModelProperty(value = "0不支持单买>>1支持单买")
	private Integer groupSingleBuy;

	@ApiModelProperty(value = "阶梯拼团设置")
	private List<PmsGoodsGroup> groupList;
}