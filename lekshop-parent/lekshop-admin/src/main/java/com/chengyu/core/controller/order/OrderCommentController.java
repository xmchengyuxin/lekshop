package com.chengyu.core.controller.order;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.OrderCommentSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderComment;
import com.chengyu.core.service.order.OrderCommentService;
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

/**
 * @title  评论
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "评论")
@Controller
@RequestMapping("/system")
public class OrderCommentController extends AdminBaseController {
	
	@Autowired
	private OrderCommentService orderCommentService;
	
	@ApiOperation(value = "评论列表")
	@ResponseBody
	@RequestMapping(value="/comment/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderComment>> getList(OrderCommentSearchForm form,
															@RequestParam(value = "page", defaultValue = "1") Integer page,
															@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<OmsOrderComment> list = orderCommentService.getCommentList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "修改评论")
	@ResponseBody
	@RequestMapping(value="/comment/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(OmsOrderComment comment) throws ServiceException {
		orderCommentService.updateComment(comment);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除评论")
	@ResponseBody
	@RequestMapping(value="/comment/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			orderCommentService.deleteComment(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}

}