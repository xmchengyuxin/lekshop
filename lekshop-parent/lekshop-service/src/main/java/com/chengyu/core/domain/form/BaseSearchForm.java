package com.chengyu.core.domain.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BaseSearchForm {

	private String keyword;

	private Integer status;

	private Integer memberId;

}