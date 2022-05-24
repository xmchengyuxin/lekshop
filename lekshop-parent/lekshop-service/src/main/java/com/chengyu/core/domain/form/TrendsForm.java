package com.chengyu.core.domain.form;

import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.WalkMember;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrendsForm {

	@ApiModelProperty(value = "1短视频>>2宝贝上新>>3种草>>4买家秀")
	private Integer type;

	@ApiModelProperty(value = "1短视频>>2宝贝上新>>3种草>>4买家秀")
	private List<Integer> typeList;

	@ApiModelProperty(value = "创作人")
	private Integer walkMemberId;

	@ApiModelProperty(value = "创作号")
	private Integer walkMemberUid;

	@ApiModelProperty(value = "排序")
	private String sort;

	@ApiModelProperty(value = "0:待审核 1:通过 2不通过 3草稿")
	private Integer status;

	@ApiModelProperty(value = "首条动态ID")
	private Integer firstTrendsId;

	private WalkMember walkMember;

}