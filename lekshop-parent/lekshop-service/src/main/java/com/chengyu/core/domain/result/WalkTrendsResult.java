package com.chengyu.core.domain.result;

import com.chengyu.core.model.WalkTrends;
import com.chengyu.core.model.WalkTrendsGoods;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WalkTrendsResult {

	private WalkTrends walkTrends;
	
	private List<WalkTrendsGoods> trendsGoodsList;

	private boolean isCollectTrends;

	private boolean isCollectWalkMember;
}
