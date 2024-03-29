package com.chengyu.core.controller.order;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.CaculateFreightFeeForm;
import com.chengyu.core.domain.form.OrderAddForm;
import com.chengyu.core.domain.form.OrderBuyDetailForm;
import com.chengyu.core.domain.form.PayBaseForm;
import com.chengyu.core.domain.result.OrderPayResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.*;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.member.MemberCouponService;
import com.chengyu.core.service.order.OrderGroupService;
import com.chengyu.core.service.order.OrderService;
import com.chengyu.core.service.pay.PayService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "订单")
@Controller
@RequestMapping("/member")
public class OrderController extends UserBaseController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderGroupService orderGroupService;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private PayService payService;

	@ApiOperation(value = "确认订单查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsParams", value = "[shopId,店铺ID,skuId:商品skuId,num:购买数量]"),
			@ApiImplicitParam(name = "groupId", value = "拼团购买方式>>-1单独购买>>0普通拼团>>其他为阶梯拼团ID")
	})
	@ResponseBody
	@RequestMapping(value={"/order/place"}, method=RequestMethod.POST)
	public CommonResult<List<ShopGoodsOrder>> confirm(String goodsParams, UmsMemberAddress address, Integer groupId) throws ServiceException {
		if(StringUtils.isBlank(goodsParams)){
			throw new ServiceException("order.goods");
		}
		List<OrderBuyDetailForm> buyDetailFormList = JSONArray.parseArray(goodsParams, OrderBuyDetailForm.class);
		List<Integer> shopIdList = buyDetailFormList.stream().map(OrderBuyDetailForm::getShopId).distinct().collect(Collectors.toList());

		List<ShopGoodsOrder> list = new ArrayList<>();
		for(Integer shopId : shopIdList){
			ShopGoodsOrder goodsOrder = new ShopGoodsOrder();

			List<OrderBuyDetailForm> goodsFormList = buyDetailFormList.stream().filter(detail->detail.getShopId().equals(shopId)).collect(Collectors.toList());
			List<ShopGoods> goodsList = new ArrayList<>();
			List<CaculateFreightFeeForm> freightFeeFormList = new ArrayList<>();
			for(OrderBuyDetailForm buyDetailForm : goodsFormList){
				PmsGoodsSku sku = goodsService.getGoodsSku(buyDetailForm.getSkuId());
				PmsGoods goods = goodsService.getGoods(sku.getGoodsId());
				ShopGoods shopGoods = new ShopGoods();
				shopGoods.setGoods(goods);
				shopGoods.setGoodsSku(sku);
				shopGoods.setBuyNum(buyDetailForm.getNum());
				shopGoods.setGroupId(groupId);
				if(shopGoods.getGroupId() != null){
					if(shopGoods.getGroupId() == -1){
						//单独购买
						sku.setPrice(sku.getOriPrice());
						shopGoods.setGroupName("单购价");
					}else if(shopGoods.getGroupId() == 0){
						//普通拼团
						shopGoods.setGroupName("拼团价");
					}else{
						//阶梯拼团
						PmsGoodsGroup group = goodsService.getGoodsGroup(shopGoods.getGroupId());
						sku.setPrice(NumberUtil.mul(sku.getPrice(), NumberUtil.div(group.getDiscounts(), 100)));
						shopGoods.setGroupName(group.getNum()+"人阶梯价");
					}

				}
				goodsList.add(shopGoods);
				CaculateFreightFeeForm freightForm = new CaculateFreightFeeForm(goods.getFreightTemplateId(), buyDetailForm.getNum(), sku.getWeight());
				freightFeeFormList.add(freightForm);
			}
			goodsOrder.setGoodsList(goodsList);
			goodsOrder.setShopId(goodsList.get(0).getGoods().getShopId());
			goodsOrder.setShopName(goodsList.get(0).getGoods().getShopName());
			goodsOrder.setFreightFee(orderService.caculateFreightFee(goodsOrder.getShopId(), address, freightFeeFormList));
			list.add(goodsOrder);
		}
		return CommonResult.success(list);
	}

	@Setter
	@Getter
	private class ShopGoodsOrder {
		private Integer shopId;
		private String shopName;
		private List<ShopGoods> goodsList;
		private BigDecimal freightFee;
	}

	@Setter
	@Getter
	private class ShopGoods {
		private PmsGoods goods;
		private PmsGoodsSku goodsSku;
		private Integer groupId;
		private String groupName;
		private Integer buyNum;
	}

	@ApiOperation(value = "添加订单")
	@OperationLog
	@ApiImplicitParams({
		@ApiImplicitParam(name = "addFormJson", value = "[goodsDetail:[skuId:商品skuId,num:购买数量],carIds:购物车ID集合,couponId:优惠券ID,remark:备注,groupId:拼团购买方式>>-1单独购买>>0普通拼团>>其他为阶梯拼团ID,joinGroupId:参团ID]")
	})
	@ResponseBody
	@RequestMapping(value={"/order/add"}, method=RequestMethod.POST)
	public CommonResult<OrderPayResult> add(UmsMemberAddress address, String addFormJson) throws ServiceException {
		List<OrderAddForm> addFormList = JSONArray.parseArray(addFormJson, OrderAddForm.class);
		OrderPayResult result = orderService.addOrder(getCurrentMember(), address, addFormList);
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "支付订单")
	@ResponseBody
	@RequestMapping(value={"/order/pay"}, method=RequestMethod.POST)
	public CommonResult<Map<String,Object>> payOrder(String payOrderNo, PayBaseForm payBaseForm) throws Exception{
		UmsMember member = getCurrentMember();
		super.validateRepeat("payOrder-"+member.getId(), 5000L, "点太快了~");
		//调用支付接口
		payBaseForm.setIp(this.getRequestIp());
		String message = payService.orderPay(member, payOrderNo, payBaseForm);

		Map<String,Object> result = new HashMap<>();
		result.put("payOrderNo", payOrderNo);
		result.put("message", message);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "支付详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "payOrderNo", value = "支付订单号"),
	})
	@ResponseBody
	@RequestMapping(value="/order/payDetail", method=RequestMethod.GET)
	public CommonResult<OmsPayOrder> payDetail(String payOrderNo) throws Exception{
		OmsPayOrder payOrder = orderService.getPayOrder(payOrderNo);
		return CommonResult.success(payOrder);
	}

	@ApiOperation(value = "校验是否可参团")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "groupId", value = "拼团ID"),
	})
	@ResponseBody
	@RequestMapping(value="/order/validateGroup", method=RequestMethod.GET)
	public CommonResult<String> validateGroup(Integer groupId) throws Exception{
		//判断参加的团是否是自己创建的团
		OmsOrderGroup assemble = orderGroupService.getOrderGroupById(groupId);
		if(assemble == null) {
			throw new ServiceException("order.group.notexist");
		}
		if(assemble.getStatus() != 1) {
			throw new ServiceException("order.group.unjoin");
		}
		if(new Date().after(assemble.getEndTime())) {
			throw new ServiceException("order.group.end");
		}
		if(assemble.getMemberId().equals(getCurrentMember().getId())) {
			throw new ServiceException("order.group.myself");
		}
		return CommonResult.success(null);
	}

	@ApiOperation(value = "获取当前可用优惠券")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "shopId", value = "店铺ID"),
			@ApiImplicitParam(name = "goodsIds", value = "商品ID逗号隔开"),
			@ApiImplicitParam(name = "cateIds", value = "商品分类ID逗号隔开"),
			@ApiImplicitParam(name = "totalAmount", value = "总金额"),
	})
	@ResponseBody
	@RequestMapping(value="/order/getCouponSelector", method=RequestMethod.GET)
	public CommonResult<List<Map<String,Object>>> getCouponSelector(Integer shopId, String goodsIds, String cateIds, BigDecimal totalAmount) throws ServiceException {
		List<Map<String,Object>> list = memberCouponService.getCanUseCouponList(getCurrentMember(), shopId, goodsIds, cateIds, totalAmount);
		return CommonResult.success(list);
	}
}