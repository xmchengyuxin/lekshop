package com.chengyu.core.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.OrderDeliveryForm;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderFreightResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.order.OrderFreightService;
import com.chengyu.core.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  订单
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "订单")
@Controller
@RequestMapping("/member")
public class OrderController extends ShopBaseController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderFreightService orderFreightService;
	
	@ApiOperation(value = "订单列表")
	@ResponseBody
	@RequestMapping(value="/order/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OrderResult>> getList(OrderSearchForm form,
													  @RequestParam(value = "page", defaultValue = "1") Integer page,
													  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		form.setShopId(getCurrentShop().getId());
		CommonPage<OrderResult> list = orderService.getOrderList(form, page, pageSize);
		return CommonResult.success(list);
	}

	@ApiOperation(value = "订单详情")
	@ResponseBody
	@RequestMapping(value="/order/get", method=RequestMethod.GET)
	public CommonResult<OrderResult> get(Integer orderId) throws ServiceException {
		OrderResult result = orderService.getOrder(orderId);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "发货")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deliveryJson", value = "[deliveryType:快递类型,orderId:订单ID,deliveryNo:快递单号]")
	})
	@ResponseBody
	@RequestMapping(value="/order/delivery", method=RequestMethod.POST)
	public CommonResult<String> delivery(String deliveryJson) throws ServiceException {
		List<OrderDeliveryForm> list = JSONArray.parseArray(deliveryJson, OrderDeliveryForm.class);
		String massage = orderService.delivery(getCurrentShop(), list);
		return CommonResult.success(massage);
	}

	@ApiOperation(value = "物流详情")
	@ResponseBody
	@RequestMapping(value="/order/getFreightList", method=RequestMethod.GET)
	public CommonResult<OrderFreightResult> getDeliveryList(Integer orderId) {
		OrderFreightResult result = orderFreightService.getOrderFreight(orderId);
		return CommonResult.success(result);
	}
}