package com.chengyu.core.controller.order;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.OrderRefundForm;
import com.chengyu.core.domain.form.OrderRefundSearchForm;
import com.chengyu.core.domain.result.OrderRefundResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderRefund;
import com.chengyu.core.service.order.OrderRefundService;
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

@Api(tags = "售后管理")
@Controller
@RequestMapping("/member")
public class MemberRefundController extends UserBaseController {
	
	@Autowired
	private OrderRefundService orderRefundService;

	@OperationLog
	@ApiOperation(value = "申请售后")
	@ResponseBody
	@RequestMapping(value={"/refund/apply"}, method=RequestMethod.POST)
	public CommonResult<String> cancel(OrderRefundForm form) throws ServiceException {
		orderRefundService.applyRefund(getCurrentMember(), form);
		return CommonResult.success(null);
	}


	@ApiOperation(value = "获取售后列表")
	@ResponseBody
	@RequestMapping(value={"/refund/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderRefund>> getList(OrderRefundSearchForm form, Integer page, Integer pageSize) throws ServiceException {
		form.setMemberId(getCurrentMemberId());
		List<OmsOrderRefund> list = orderRefundService.getRefundList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "退款详情")
	@ResponseBody
	@RequestMapping(value="/refund/get", method=RequestMethod.GET)
	public CommonResult<OrderRefundResult> get(Integer detailId, Integer refundId) throws ServiceException {
		OrderRefundResult result = null;
		if(refundId != null){
			result = orderRefundService.getOrderRefund(refundId);
		}else{
			result = orderRefundService.getOrderRefundByDetailId(detailId);
		}
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "申请客服介入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "refundId", value = "售后ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/refund/applyCustomerServiceIn"}, method=RequestMethod.POST)
	public CommonResult<String> applyCustomerServiceIn(Integer refundId) throws ServiceException {
		orderRefundService.applyCustomerServiceIn(getCurrentMember(), refundId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "取消售后")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "refundId", value = "ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/refund/cancel"}, method=RequestMethod.POST)
	public CommonResult<String> cancel(Integer refundId) throws ServiceException {
		orderRefundService.cancelRefund(getCurrentMember(), refundId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "退货")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "refundId", value = "售后ID"),
		@ApiImplicitParam(name = "deliveryType", value = "快递类型"),
		@ApiImplicitParam(name = "deliveryNo", value = "快递单号"),
	})
	@ResponseBody
	@RequestMapping(value={"/refund/salesReturnByMember"}, method=RequestMethod.POST)
	public CommonResult<String> salesReturnByMember(Integer refundId, String deliveryType, String deliveryNo) throws ServiceException {
		orderRefundService.salesReturnByMember(getCurrentMember(), refundId, deliveryType, deliveryNo);
		return CommonResult.success(null);
	}

}