package com.chengyu.core.domain.result;

import com.chengyu.core.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCommentResult {

	private OmsOrderComment comment;
	
	private List<OmsOrderCommentLeft> leftCommentList;
}
