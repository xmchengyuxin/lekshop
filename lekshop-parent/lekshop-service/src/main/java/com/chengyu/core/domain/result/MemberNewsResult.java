package com.chengyu.core.domain.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MemberNewsResult {

	@ApiModelProperty(value = "消息类型名称")
	private String typeName;
	@ApiModelProperty(value = "消息类型")
	private Integer type;
	@ApiModelProperty(value = "展示ICON")
	private String img;
	@ApiModelProperty(value = "未读数量")
	private Long unReadNums;
	@ApiModelProperty(value = "最近一条消息")
	private String lastNews;
	@ApiModelProperty(value = "最近一条消息时间")
	private Date lastNewsTime;
}
