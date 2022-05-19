package com.chengyu.core.controller.walk;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.TrendsForm;
import com.chengyu.core.domain.result.WalkTrendsCommentResult;
import com.chengyu.core.domain.result.WalkTrendsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.WalkTrends;
import com.chengyu.core.service.walk.WalkTrendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "逛逛社区")
@Controller
@RequestMapping("/member")
public class WalkTrendsController extends ShopBaseController {
	
	@Autowired
	private WalkTrendsService walkTrendsService;
	
	@ApiOperation(value = "动态列表")
	@ResponseBody
	@RequestMapping(value="/trends/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrendsResult>> getList(TrendsForm form,
													  @RequestParam(value = "page", defaultValue = "1") Integer page,
													  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize ) throws ServiceException {
		form.setWalkMemberId(getCurrentWalkMember().getId());
		CommonPage<WalkTrendsResult> list = walkTrendsService.getTrendsList(form, page, pageSize);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "发布动态")
	@ResponseBody
	@RequestMapping(value="/trends/publish", method=RequestMethod.POST)
	public CommonResult<String> add(WalkTrends trends, String goodsIds) throws ServiceException {
		List<Integer> goodsIdList = null;
		if(com.chengyu.core.utils.StringUtils.isNotBlank(goodsIds)){
			goodsIdList = Convert.convert(new TypeReference<List<Integer>>() {}, goodsIds.split(CommonConstant.DH_REGEX));
		}
		if(trends.getId() == null){
			walkTrendsService.publishTrends(getCurrentWalkMember(), trends, goodsIdList);
		}else{
			walkTrendsService.editTrends(trends, goodsIdList);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除动态")
	@ResponseBody
	@RequestMapping(value="/trends/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		walkTrendsService.deleteTrends(ids);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "动态评论列表")
	@ResponseBody
	@RequestMapping(value="/trends/getCommentList", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrendsCommentResult>> getCommentList(Integer trendsId,
																			@RequestParam(value = "page", defaultValue = "1") Integer page,
																			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize ) throws ServiceException {
		CommonPage<WalkTrendsCommentResult> list = walkTrendsService.getTrendsCommentList(trendsId, null, null, page, pageSize);
		return CommonResult.success(list);
	}
}