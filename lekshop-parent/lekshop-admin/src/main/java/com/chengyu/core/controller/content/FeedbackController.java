package com.chengyu.core.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsFeedback;
import com.chengyu.core.service.content.FeedbackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "FeedbackController", description = "意见反馈管理")
@Controller
@RequestMapping("/system")
public class FeedbackController extends AdminBaseController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@ApiOperation(value = "意见反馈列表")
	@ResponseBody
	@RequestMapping(value="/feedback/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsFeedback>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		List<CmsFeedback> list = feedbackService.getFeedbackList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "将意见反馈标记为已处理")
	@ResponseBody
	@RequestMapping(value="/feedback/updateStatus", method=RequestMethod.POST)
	public CommonResult<String> updateStatus(Integer id, String status) throws Exception {
		feedbackService.handleFeedback(id);
		return CommonResult.success(null);
	}
}