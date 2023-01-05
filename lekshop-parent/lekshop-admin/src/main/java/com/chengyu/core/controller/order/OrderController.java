package com.chengyu.core.controller.order;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderPriceLog;
import com.chengyu.core.service.order.OrderPriceLogService;
import com.chengyu.core.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @title  订单
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "订单")
@Controller
@RequestMapping("/system")
public class OrderController extends AdminBaseController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderPriceLogService orderPriceLogService;
	
	@ApiOperation(value = "订单列表")
	@ResponseBody
	@RequestMapping(value="/order/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OrderResult>> getList(OrderSearchForm form,
													  @RequestParam(value = "page", defaultValue = "1") Integer page,
													  @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
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

	@ApiOperation(value = "价格修改记录")
	@ResponseBody
	@RequestMapping(value="/order/getPriceLog", method=RequestMethod.GET)
	public CommonResult<List<OmsOrderPriceLog>> getPriceLog(Integer orderId) {
		List<OmsOrderPriceLog> list= orderPriceLogService.getOrderPriceLogByOrderId(orderId);
		return CommonResult.success(list);
	}
}