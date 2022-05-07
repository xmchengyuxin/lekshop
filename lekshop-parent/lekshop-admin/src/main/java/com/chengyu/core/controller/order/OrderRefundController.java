package com.chengyu.core.controller.order;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.OrderRefundSearchForm;
import com.chengyu.core.domain.result.OrderRefundResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.service.order.OrderRefundService;
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
 * @title  退款
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "退款")
@Controller
@RequestMapping("/system")
public class OrderRefundController extends AdminBaseController {
	
	@Autowired
	private OrderRefundService refundService;
	
	@ApiOperation(value = "退款列表")
	@ResponseBody
	@RequestMapping(value="/refund/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderRefund>> getList(OrderRefundSearchForm form,
															@RequestParam(value = "page", defaultValue = "1") Integer page,
															@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<OmsOrderRefund> list = refundService.getRefundList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "退款详情")
	@ResponseBody
	@RequestMapping(value="/refund/get", method=RequestMethod.GET)
	public CommonResult<OrderRefundResult> get(Integer refundId) throws ServiceException {
		OrderRefundResult result = refundService.getOrderRefund(refundId);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "平台同意退款")
	@ResponseBody
	@RequestMapping(value="/refund/agree", method=RequestMethod.POST)
	public CommonResult<String> agree(Integer refundId) throws ServiceException {
		refundService.agreeRefundByCustomer(getSessionAdmin(), refundId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "平台拒绝退款")
	@ResponseBody
	@RequestMapping(value="/refund/refuse", method=RequestMethod.POST)
	public CommonResult<String> refuse(Integer refundId, String reason) throws ServiceException {
		refundService.refuseRefundByCustomer(getSessionAdmin(), refundId, reason);
		return CommonResult.success(null);
	}
}