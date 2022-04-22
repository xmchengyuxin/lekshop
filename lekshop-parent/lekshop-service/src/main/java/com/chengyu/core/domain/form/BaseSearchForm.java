package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BaseSearchForm {

	private Integer subId;

	private String keyword;

	private Integer agentId;

	private Integer staffId;

	private Integer status;

	private Integer memberId;

}