package com.chengyu.core.controller.trends;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.TrendsEnums;
import com.chengyu.core.domain.form.TrendsForm;
import com.chengyu.core.domain.result.WalkTrendsCommentResult;
import com.chengyu.core.domain.result.WalkTrendsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.walk.WalkTrendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "逛逛")
@Controller
public class TrendsController extends UserBaseController {
	
	@Autowired
	private WalkTrendsService walkTrendsService;

	@ApiOperation(value = "逛逛首页")
	@ResponseBody
	@RequestMapping(value="/common/trends/search", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrendsResult>> search(TrendsForm form, Integer page, Integer pageSize) {
		form.setStatus(CommonConstant.SUS_INT);
		form.setTypeList(CollectionUtil.newArrayList(TrendsEnums.TrendsType.VIDEO.getValue(), TrendsEnums.TrendsType.DISCOVERY.getValue()));
		CommonPage<WalkTrendsResult> list = walkTrendsService.getTrendsList(form, page, pageSize);
		return CommonResult.success(list);
	}

	@ApiOperation(value = "动态详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trendsId", value = "动态ID")
	})
	@ResponseBody
	@RequestMapping(value="/common/trends/get", method=RequestMethod.GET)
	public CommonResult<WalkTrendsResult> get(Integer trendsId) {
		WalkTrendsResult result = walkTrendsService.getTrendsDetail(trendsId);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "关注取关动态")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trendsId", value = "动态ID")
	})
	@ResponseBody
	@RequestMapping(value={"/member/trends/collectOrNo"}, method=RequestMethod.POST)
	public CommonResult<String> collectOrNoGoods(Integer trendsId) {
		walkTrendsService.collectOrCancelTrends(getCurrentWalkMember(), trendsId);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "动态评论列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trendsId", value = "动态ID")
	})
	@ResponseBody
	@RequestMapping(value="/common/trends/search", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrendsCommentResult>> search(Integer trendsId, Integer page, Integer pageSize) {
		CommonPage<WalkTrendsCommentResult> list = walkTrendsService.getTrendsCommentList(trendsId, null, null, page, pageSize);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "评论动态")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "trendsId", value = "动态ID"),
		@ApiImplicitParam(name = "commentId", value = "针对某条评论ID"),
		@ApiImplicitParam(name = "content", value = "内容")
	})
	@ResponseBody
	@RequestMapping(value={"/member/trends/comment"}, method=RequestMethod.POST)
	public CommonResult<String> comment(Integer trendsId, Integer commentId, String content) {
		walkTrendsService.addComment(getCurrentWalkMember(), trendsId, commentId, content);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "点赞取消点赞评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "commentId", value = "评论ID")
	})
	@ResponseBody
	@RequestMapping(value={"/member/comment/likeOrNo"}, method=RequestMethod.POST)
	public CommonResult<String> collectOrNoComment(Integer commentId) {
		walkTrendsService.likeOrCancelComment(getCurrentWalkMember(), commentId);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "浏览动态")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "trendsId", value = "动态ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/common/trends/addViewNums"}, method=RequestMethod.POST)
	public CommonResult<String> addViewNums(Integer trendsId) {
		walkTrendsService.addViewNums(trendsId, 1);
		return CommonResult.success(null);
	}
}