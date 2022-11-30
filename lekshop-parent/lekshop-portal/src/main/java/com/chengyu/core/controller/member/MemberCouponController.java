package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.model.UmsMemberCoupon;
import com.chengyu.core.service.member.MemberCouponService;
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

@Api(tags = "用户优惠券")
@Controller
@RequestMapping("/member")
public class MemberCouponController extends UserBaseController {
	
	@Autowired
	private ShopCouponService shopCouponService;
	@Autowired
	private MemberCouponService memberCouponService;

	@ApiOperation(value = "我的优惠券列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "type", value = "1满减券>>2折扣券"),
		@ApiImplicitParam(name = "shopId", value = "店铺ID"),
	})
	@ResponseBody
	@RequestMapping(value="/memberCoupon/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberCoupon>> search(Integer status, Integer page, Integer pageSize) throws ServiceException {
		List<UmsMemberCoupon> list = memberCouponService.getMemberCouponList(getCurrentMemberId(), null, status, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "领取优惠券")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shopCouponId", value = "优惠券ID"),
	})
	@ResponseBody
	@RequestMapping(value={"/memberCoupon/draw"}, method=RequestMethod.POST)
	public CommonResult<UmsMemberBank> drawCoupon(Integer shopCouponId) throws ServiceException {
		shopCouponService.drawCoupon(getCurrentMember(), shopCouponId);
		return CommonResult.success(null);
	}
}