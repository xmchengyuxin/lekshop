package com.chengyu.core.controller.order;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.controller.callback.manager.CallbackManager;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
	private CallbackManager paySusManager;
	@Autowired
	private OrderGroupService orderGroupService;
	@Autowired
	private MemberCouponService memberCouponService;

	@ApiOperation(value = "确认订单查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsParams", value = "[shopId,店铺ID,skuId:商品skuId,num:购买数量]"),
			@ApiImplicitParam(name = "groupId", value = "拼团购买方式>>-1单独购买>>0普通拼团>>其他为阶梯拼团ID")
	})
	@ResponseBody
	@RequestMapping(value={"/order/place"}, method=RequestMethod.POST)
	public CommonResult<List<ShopGoodsOrder>> confirm(String goodsParams, UmsMemberAddress address, Integer groupId) {
		if(StringUtils.isBlank(goodsParams)){
			return CommonResult.failed("请选择商品");
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
		paySusManager.getPaySusFactory(CallbackManager.CALLBACK_ORDER).paySus(payOrderNo);
		return CommonResult.success(null);
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
			return CommonResult.failed("该拼团不存在");
		}
		if(assemble.getStatus() != 1) {
			return CommonResult.failed("该拼团已成功或失败,请选择别的团");
		}
		if(new Date().after(assemble.getEndTime())) {
			return CommonResult.failed("该拼团已结束,请选择别的团");
		}
		if(assemble.getMemberId().equals(getCurrentMember().getId())) {
			return CommonResult.failed("不能参加自己的团");
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