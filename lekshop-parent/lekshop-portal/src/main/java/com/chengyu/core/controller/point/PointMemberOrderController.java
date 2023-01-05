package com.chengyu.core.controller.point;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.PointOrderFreightResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PointOrder;
import com.chengyu.core.service.point.PointOrderFreightService;
import com.chengyu.core.service.point.PointOrderService;
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

@Api(tags = "积分商城订单管理")
@Controller
@RequestMapping("/member/point")
public class PointMemberOrderController extends UserBaseController {
	
	@Autowired
	private PointOrderService orderService;
	@Autowired
	private PointOrderFreightService orderFreightService;

	@ApiOperation(value = "获取订单列表")
	@ResponseBody
	@RequestMapping(value={"/order/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<PointOrder>> getList(OrderSearchForm form, Integer page, Integer pageSize) throws ServiceException {
		form.setMemberId(getCurrentMemberId());
		List<PointOrder> list = orderService.getOrderList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "订单详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderId", value = "订单ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/get"}, method=RequestMethod.GET)
	public CommonResult<PointOrder> get(Integer orderId) throws ServiceException {
		PointOrder result = orderService.getOrder(orderId);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "物流详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单ID"),
	})
	@ResponseBody
	@RequestMapping(value="/order/getFreightList", method=RequestMethod.GET)
	public CommonResult<PointOrderFreightResult> getDeliveryList(Integer orderId) {
		PointOrderFreightResult result = orderFreightService.getOrderFreight(orderId);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "取消订单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/cancel"}, method=RequestMethod.POST)
	public CommonResult<String> cancel(Integer orderId) throws ServiceException {
		orderService.cancelOrder(getCurrentMember(), orderId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderId", value = "订单ID"),
		@ApiImplicitParam(name = "addressId", value = "更换后地址ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/updateAddress"}, method=RequestMethod.POST)
	public CommonResult<String> updateAddress(Integer orderId, Integer addressId) throws ServiceException {
		orderService.updateOrderAddress(getCurrentMember(), orderId, addressId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "确认收货")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderId", value = "订单ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/confirm"}, method=RequestMethod.POST)
	public CommonResult<String> confirmOrder(Integer orderId) throws ServiceException {
		orderService.confirmOrder(getCurrentMember(), orderId);
		return CommonResult.success(null);
	}

}