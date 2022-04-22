package com.chengyu.core.domain.form;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberBuySellSearchForm {

	private Integer subId;

	private Integer memberId;

	private String memberName;

	private Integer shopCateId;

	private String sellerName;

	private String shopName;

	private String buyerName;

	private Integer status;

	private Integer delInd;

	private Integer tbAuthStatus;

	private Integer agentId;

	private Date dateFrom;

	private Date dateTo;

	private Integer huabeiStatus;

	private Integer addressStatus;

	private String areaCode;

	private String phone;

	private Integer huabeiBaitiao;

	private Integer staffId;
}