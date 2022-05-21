package com.chengyu.core.controller.order;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.OrderCommentSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderComment;
import com.chengyu.core.model.OmsOrderCommentLeft;
import com.chengyu.core.service.order.OrderCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  评论
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "评论")
@Controller
@RequestMapping("/member")
public class OrderCommentController extends ShopBaseController {
	
	@Autowired
	private OrderCommentService orderCommentService;
	
	@ApiOperation(value = "评论列表")
	@ResponseBody
	@RequestMapping(value="/comment/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderComment>> getList(OrderCommentSearchForm form,
															@RequestParam(value = "page", defaultValue = "1") Integer page,
															@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		form.setShopId(getCurrentShop().getId());
		form.setStatusList(CollectionUtil.newArrayList(OrderEnums.CommentStatus.COMMENTED.getValue(), OrderEnums.CommentStatus.ADD_COMMENTED.getValue()));
		List<OmsOrderComment> list = orderCommentService.getCommentList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "获取子评论列表")
	@ResponseBody
	@RequestMapping(value="/comment/getLeftList", method=RequestMethod.GET)
	public CommonResult<List<OmsOrderCommentLeft>> getLeftList(Integer commentId) {
		List<OmsOrderCommentLeft> list = orderCommentService.getLeftCommentList(commentId);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "回复评论")
	@ResponseBody
	@RequestMapping(value="/comment/answer", method=RequestMethod.POST)
	public CommonResult<String> answer(Integer commentId, String content, String img) throws ServiceException {
		orderCommentService.addLeftComment(getCurrentMember(), commentId, 1, content, img);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除回复")
	@ResponseBody
	@RequestMapping(value="/comment/deleteLeftComment", method=RequestMethod.POST)
	public CommonResult<String> deleteLeftComment(Integer leftCommentId) {
		orderCommentService.deleteLeftComment(leftCommentId);
		return CommonResult.success(null);
	}
}