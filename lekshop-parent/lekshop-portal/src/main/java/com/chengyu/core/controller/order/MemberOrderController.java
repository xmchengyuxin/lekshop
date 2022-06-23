package com.chengyu.core.controller.order;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.enums.OrderEnums;
import com.chengyu.core.domain.form.OrderSearchForm;
import com.chengyu.core.domain.result.OrderFreightResult;
import com.chengyu.core.domain.result.OrderGroupResult;
import com.chengyu.core.domain.result.OrderResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OmsOrderDetail;
import com.chengyu.core.service.order.OrderFreightService;
import com.chengyu.core.service.order.OrderGroupService;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.shop.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "订单管理")
@Controller
@RequestMapping("/member")
public class MemberOrderController extends UserBaseController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGroupService orderGroupService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private OrderFreightService orderFreightService;

	@ApiOperation(value = "获取订单列表")
	@ResponseBody
	@RequestMapping(value={"/order/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<OrderResult>> getList(OrderSearchForm form, Integer page, Integer pageSize) throws ServiceException {
		form.setMemberId(getCurrentMemberId());
		CommonPage<OrderResult> list = orderService.getOrderList(form, page, pageSize);
		return CommonResult.success(list);
	}

	@ApiOperation(value = "获取滚动订单")
	@ResponseBody
	@RequestMapping(value={"/order/getRolliingList"}, method=RequestMethod.GET)
	public CommonResult<List<OmsOrderDetail>> getRolliingList() throws ServiceException {
		List<OmsOrderDetail> list = new ArrayList<>();
		//待付款，待评价，待收货
		OrderSearchForm form = new OrderSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setStatusList(CollectionUtil.newArrayList(OrderEnums.OrderStatus.WAIT_PAY.getValue()));
		List<OmsOrderDetail> detailList = orderService.getOrderDetailList(form, 1, 5);
		if(CollectionUtil.isNotEmpty(detailList)){
			list.addAll(detailList);
		}

		form = new OrderSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setStatus(OrderEnums.OrderStatus.FINISHED.getValue());
		form.setCommentStatus(CommonConstant.NO_INT);
		List<OmsOrderDetail> commentList = orderService.getOrderDetailList(form, 1, 5);
		if(CollectionUtil.isNotEmpty(commentList)){
			list.addAll(commentList);
		}
		return CommonResult.success(list);
	}

	@ApiOperation(value = "订单详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderId", value = "订单ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/get"}, method=RequestMethod.GET)
	public CommonResult<OrderResult> get(Integer orderId) throws ServiceException {
		OrderResult result = orderService.getOrder(orderId);
		result.setShopMemberId(shopService.getMemberByShopId(result.getOrder().getShopId()).getId());
		if(result.getOrder().getType() == GoodsEnums.GoodsType.GROUP_GOODS.getValue()){
			//团购商品查询团购人员
			OrderGroupResult groupResult = orderGroupService.getOrderGroupByOrderNo(result.getOrder().getOrderNo());
			result.setOrderGroup(groupResult.getOrderGroup());
			result.setGroupMemberList(groupResult.getGroupMemberList());
		}
		return CommonResult.success(result);
	}

	@ApiOperation(value = "物流详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "订单ID"),
	})
	@ResponseBody
	@RequestMapping(value="/order/getFreightList", method=RequestMethod.GET)
	public CommonResult<OrderFreightResult> getDeliveryList(Integer orderId) {
		OrderFreightResult result = orderFreightService.getOrderFreight(orderId);
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