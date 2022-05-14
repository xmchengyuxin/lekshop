package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.model.UmsShopCoupon;
import com.chengyu.core.service.goods.GoodsService;
import com.chengyu.core.service.shop.ShopCouponService;
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

@Api(tags = "店铺优惠券")
@Controller
@RequestMapping("/member")
public class ShopCouponController extends UserBaseController {
	
	@Autowired
	private ShopCouponService shopCouponService;

	@ApiOperation(value = "搜索商品(查询商品列表大部分用此接口)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "1满减券>>2折扣券"),
		@ApiImplicitParam(name = "shopId", value = "店铺ID"),
	})
	@ResponseBody
	@RequestMapping(value="/coupon/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShopCoupon>> search(Integer shopId, Integer type, Integer page, Integer pageSize) {
		List<UmsShopCoupon> list = shopCouponService.getShopCouponList(shopId, null, type, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "领取优惠券")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shopCouponId", value = "优惠券ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/coupon/draw"}, method=RequestMethod.POST)
	public CommonResult<UmsMemberBank> drawCoupon(Integer shopCouponId) throws ServiceException {
		shopCouponService.drawCoupon(getCurrentMember(), shopCouponId);
		return CommonResult.success(null);
	}
}