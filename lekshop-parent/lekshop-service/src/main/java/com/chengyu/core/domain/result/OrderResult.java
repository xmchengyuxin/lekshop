package com.chengyu.core.domain.result;

import com.chengyu.core.model.OmsOrder;
import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.model.OmsOrderGroup;
import com.chengyu.core.model.OmsOrderGroupMember;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResult {

	private Integer memberId;

	private OmsOrder order;
	
	private List<OmsOrderDetail> orderDetailList;

	private OmsOrderGroup orderGroup;

	private List<OmsOrderGroupMember> groupMemberList;

	//用于聊天
	private Integer shopMemberId;
}
