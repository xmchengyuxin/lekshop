package com.chengyu.core.controller.order;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.OrderCommentForm;
import com.chengyu.core.domain.form.OrderCommentSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderComment;
import com.chengyu.core.service.order.OrderCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "评价管理")
@Controller
@RequestMapping("/member")
public class MemberCommentController extends UserBaseController {

	@Autowired
	private OrderCommentService orderCommentService;

	@ApiOperation(value = "获取评价列表")
	@ResponseBody
	@RequestMapping(value={"/comment/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderComment>> getList(OrderCommentSearchForm form, Integer page, Integer pageSize) throws ServiceException {
		form.setMemberId(getCurrentMemberId());
		form.setStatusList(CollectionUtil.newArrayList(OrderEnums.CommentStatus.COMMENTED.getValue(), OrderEnums.CommentStatus.ADD_COMMENTED.getValue()));
		List<OmsOrderComment> list = orderCommentService.getCommentList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "评价")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/comment/comment"}, method=RequestMethod.POST)
	public CommonResult<String> cancel(OrderCommentForm form) throws ServiceException {
		orderCommentService.addComment(getCurrentMember(), form);
		return CommonResult.success(null);
	}


}