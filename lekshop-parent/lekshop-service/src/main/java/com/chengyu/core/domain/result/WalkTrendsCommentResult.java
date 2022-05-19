package com.chengyu.core.domain.result;

import com.chengyu.core.model.WalkTrendsComment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WalkTrendsCommentResult {

	private WalkTrendsComment walkTrendsComment;
	
	private List<WalkTrendsComment> chilidCommentList;
}
