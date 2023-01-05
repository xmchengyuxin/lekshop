package com.chengyu.core.controller.point;

import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.OrderDeliveryForm;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "积分商城订单")
@Controller
@RequestMapping("/system/point")
public class PointOrderController extends AdminBaseController {
	
	@Autowired
	private PointOrderService orderService;
	@Autowired
	private PointOrderFreightService orderFreightService;

	@ApiOperation(value = "订单列表")
	@ResponseBody
	@RequestMapping(value="/order/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<PointOrder>> getList(OrderSearchForm form,
													  @RequestParam(value = "page", defaultValue = "1") Integer page,
													  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<PointOrder> list = orderService.getOrderList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "订单详情")
	@ResponseBody
	@RequestMapping(value="/order/get", method=RequestMethod.GET)
	public CommonResult<PointOrder> get(Integer orderId) throws ServiceException {
		PointOrder result = orderService.getOrder(orderId);
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
		String massage = orderService.delivery(list);
		return CommonResult.success(massage);
	}

	@ApiOperation(value = "物流详情")
	@ResponseBody
	@RequestMapping(value="/order/getFreightList", method=RequestMethod.GET)
	public CommonResult<PointOrderFreightResult> getDeliveryList(Integer orderId) {
		PointOrderFreightResult result = orderFreightService.getOrderFreight(orderId);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "删除订单")
	@ResponseBody
	@RequestMapping(value="/order/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> idList = getIdList(ids);
		orderService.deleteOrderByAdmin(idList);
		return CommonResult.success(null);
	}

}