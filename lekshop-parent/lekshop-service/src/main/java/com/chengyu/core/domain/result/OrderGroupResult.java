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
public class OrderGroupResult {

	private OmsOrderGroup orderGroup;

	private List<OmsOrderGroupMember> groupMemberList;
}
