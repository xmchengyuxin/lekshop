package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShopCoupon;
import com.chengyu.core.service.shop.ShopCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  店铺优惠券
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "店铺优惠券")
@Controller
@RequestMapping("/member")
public class ShopCouponController extends ShopBaseController {
	
	@Autowired
	private ShopCouponService shopCouponService;
	
	@ApiOperation(value = "店铺优惠券列表")
	@ResponseBody
	@RequestMapping(value="/coupon/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsShopCoupon>> getList(
			String name, Integer type,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<UmsShopCoupon> list = shopCouponService.getShopCouponList(getCurrentShop().getId(), name, type, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加店铺优惠券")
	@ResponseBody
	@RequestMapping(value="/coupon/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsShopCoupon coupon) throws ServiceException {
		shopCouponService.addCoupon(getCurrentShop(), coupon);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除店铺优惠券")
	@ResponseBody
	@RequestMapping(value="/coupon/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			shopCouponService.deleteCoupon(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}