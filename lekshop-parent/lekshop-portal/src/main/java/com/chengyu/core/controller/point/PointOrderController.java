package com.chengyu.core.controller.point;

import cn.hutool.core.util.NumberUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.result.PointOrderPayResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PointGoods;
import com.chengyu.core.model.PointOrder;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAddress;
import com.chengyu.core.service.pay.PayService;
import com.chengyu.core.service.point.PointGoodsService;
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

import java.util.HashMap;
import java.util.Map;

@Api(tags = "积分商城订单")
@Controller
@RequestMapping("/member/point")
public class PointOrderController extends UserBaseController {
	
	@Autowired
	private PointOrderService orderService;
	@Autowired
	private PointGoodsService goodsService;
	@Autowired
	private PayService payService;

	@ApiOperation(value = "确认订单查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品ID"),
			@ApiImplicitParam(name = "buyNum", value = "兑换数量"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/place"}, method=RequestMethod.POST)
	public CommonResult<Map<String, Object>> confirm(Integer goodsId, Integer buyNum) {
		Map<String, Object> result = new HashMap<>();
		PointGoods goods = goodsService.getGoods(goodsId);
		result.put("goods", goods);
		result.put("buyTotalPrice", NumberUtil.mul(goods.getPrice(), buyNum));
		result.put("buyTotalPoint", NumberUtil.mul(goods.getPoint(), buyNum));
		return CommonResult.success(result);
	}

	@ApiOperation(value = "添加积分商品订单")
	@OperationLog
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品ID"),
			@ApiImplicitParam(name = "buyNum", value = "兑换数量"),
	})
	@ResponseBody
	@RequestMapping(value={"/order/add"}, method=RequestMethod.POST)
	public CommonResult<PointOrderPayResult> add(UmsMemberAddress address, Integer goodsId, Integer buyNum) throws ServiceException {
		PointOrderPayResult result = orderService.addOrder(getCurrentMember(), address, goodsId, buyNum);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "支付订单")
	@ResponseBody
	@RequestMapping(value={"/order/pay"}, method=RequestMethod.POST)
	public CommonResult<Map<String,Object>> payOrder(String payOrderNo, PayBaseForm payBaseForm) throws Exception{
		UmsMember member = getCurrentMember();
		super.validateRepeat("payPointOrder-"+member.getId(), 5000L, "点太快了~");
		//调用支付接口
		payBaseForm.setIp(this.getRequestIp());
		String message = payService.pointOrderPay(member, payOrderNo, payBaseForm);

		Map<String,Object> result = new HashMap<>();
		result.put("payOrderNo", payOrderNo);
		result.put("message", message);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "支付详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "payOrderNo", value = "订单号"),
	})
	@ResponseBody
	@RequestMapping(value="/order/payDetail", method=RequestMethod.GET)
	public CommonResult<PointOrder> payDetail(String payOrderNo) {
		PointOrder payOrder = orderService.getOrderByOrderNo(payOrderNo);
		return CommonResult.success(payOrder);
	}
}