package com.chengyu.core.controller.goods;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.domain.form.OrderCommentSearchForm;
import com.chengyu.core.domain.form.OrderGroupSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.*;
import com.chengyu.core.service.goods.GoodsParamsService;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.member.MemberCollectGoodsService;
import com.chengyu.core.service.member.MemberCollectShopService;
import com.chengyu.core.service.member.MemberViewGoodsService;
import com.chengyu.core.service.order.OrderCommentService;
import com.chengyu.core.service.order.OrderGroupService;
import com.chengyu.core.service.shop.ShopCouponService;
import com.chengyu.core.service.shop.ShopFreightService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "商品")
@Controller
@RequestMapping("/common")
public class GoodsController extends UserBaseController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberViewGoodsService memberViewGoodsService;
	@Autowired
	private GoodsParamsService goodsParamsService;
	@Autowired
	private MemberCollectGoodsService memberCollectGoodsService;
	@Autowired
	private MemberCollectShopService memberCollectShopService;
	@Autowired
	private ShopFreightService shopFreightService;
	@Autowired
	private ShopCouponService shopCouponService;
	@Autowired
	private OrderCommentService orderCommentService;
	@Autowired
	private OrderGroupService orderGroupService;

	@ApiOperation(value = "搜索商品(查询商品列表大部分用此接口)")
	@ResponseBody
	@RequestMapping(value="/goods/search", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> search(GoodsSearchForm form, Integer page, Integer pageSize) {
		form.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "商品详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "商品ID"),
		@ApiImplicitParam(name = "orderGroupId", value = "分享的团长订单ID"),
	})
	@ResponseBody
	@RequestMapping(value="/goods/get", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> get(Integer goodsId, Integer orderGroupId) {
		Map<String,Object> result = new HashMap<>();
		PmsGoods goods = goodsService.getGoods(goodsId);
		if(goods == null){
			return CommonResult.success(result);
		}
		result.put("goods", goods);
		UmsShop shop = shopService.getShopById(goods.getShopId());
		result.put("shop", shop);
		//商品参数
		result.put("goodsParams", goodsParamsService.getGoodsParamsList(goodsId));
		//加入足迹
		UmsMember member = getCurrentMemberOrNull();
		memberViewGoodsService.viewGoods(member, goodsId);
		//是否关注商品
		result.put("isCollectGoods", memberCollectGoodsService.isCollectGoods(member, goodsId));
		//是否关注店铺
		result.put("isCollectShop", memberCollectShopService.isCollectShop(member, shop.getId()));
		//SKU
		result.put("goodsSku", goodsService.getGoodsSkuList(goodsId));
		//运费
		result.put("freight", shopFreightService.getShopFreightById(goods.getFreightTemplateId()));
		//当前条件下优惠券
		result.put("couponList", shopCouponService.getAllShopCoupons(goods.getShopId(), goods.getId(), goods.getCateId()));
		//阶梯拼团
		if(goods.getType() == GoodsEnums.GoodsType.GROUP_GOODS.getValue()){
			if(goods.getGroupType() == 2){
				//阶梯拼团
				result.put("groupList", goodsService.getGoodsGroupList(goodsId));
			}

			//统计参团数量
			long pintuanNum = orderGroupService.countGroupNum(goods.getId());
			result.put("pintuanNum", pintuanNum);

			List<OmsOrderGroup> assembleList = new ArrayList<>();
			if(orderGroupId == null){
				//查询快到期的两个团
				OrderGroupSearchForm form = new OrderGroupSearchForm();
				form.setGoodsId(goods.getId());
				form.setStatus(1);
				assembleList = orderGroupService.getOrderGroupList(form, 1, 2);
			}else{
				//查询分享的团
				OmsOrderGroup orderGroup = orderGroupService.getOrderGroupById(orderGroupId);
				assembleList.add(orderGroup);
			}
			result.put("assembleList", assembleList);
		}
		return CommonResult.success(result);
	}

	@ApiOperation(value = "商品拼团成员列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "商品ID"),
	})
	@ResponseBody
	@RequestMapping(value="/goods/getGroupList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderGroup>> getGroupList(Integer goodsId, Integer page, Integer pageSize) {
		OrderGroupSearchForm form = new OrderGroupSearchForm();
		form.setGoodsId(goodsId);
		form.setStatus(1);
		List<OmsOrderGroup> groupList = orderGroupService.getOrderGroupList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(groupList));
	}

	@ApiOperation(value = "商品评论列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsId", value = "商品ID"),
			@ApiImplicitParam(name = "goodsComment", value = "1好评>>2中评>>3差评>>4有图"),
	})
	@ResponseBody
	@RequestMapping(value="/goods/getCommentList", method=RequestMethod.GET)
	public CommonResult<CommonPage<OmsOrderComment>> search(Integer goodsId, Integer goodsComment, Integer page, Integer pageSize) {
		OrderCommentSearchForm form = new OrderCommentSearchForm();
		form.setGoodsId(goodsId);
		form.setGoodsComment(goodsComment);
		List<OmsOrderComment> list = orderCommentService.getCommentList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "猜你喜欢的商品")
	@ResponseBody
	@RequestMapping(value="/goods/getLikeList", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> getLikeList(GoodsSearchForm form, Integer page, Integer pageSize) {
		UmsMember member = getCurrentMemberOrNull();
		if(member != null){
			//查询用户近一个月浏览过的商品分类
			List<Integer> cateIdList = memberViewGoodsService.getMemberViewGoodsCateIdList(member.getId());
			form.setCatePidList(cateIdList);
		}
		form.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
}