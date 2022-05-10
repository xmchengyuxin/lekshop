package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShopInfo;
import com.chengyu.core.service.shop.ShopInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  店铺信息
 * @author LeGreen
 * @date   2022/5/9
 */
@Api(tags = "店铺信息")
@Controller
@RequestMapping("/member")
public class ShopInfoController extends UserBaseController {
	
	@Autowired
	private ShopInfoService shopInfoService;

	@ApiOperation(value = "店铺详情")
	@ResponseBody
	@RequestMapping(value="/shopInfo/get", method=RequestMethod.GET)
	public CommonResult<UmsShopInfo> getList() throws ServiceException {
		UmsShopInfo shopInfo = shopInfoService.getShopInfo(getCurrentMember());
		return CommonResult.success(shopInfo);
	}

	@OperationLog
	@ApiOperation(value = "申请入驻-提交资质")
	@ResponseBody
	@RequestMapping(value={"/shopInfo/applyShopCert"}, method=RequestMethod.POST)
	public CommonResult<Integer> applyShopCert(UmsShopInfo shopInfo) throws Exception {
		UmsMember member = getCurrentMember();
		shopInfo.setMemberId(member.getId());
		shopInfo.setMemberName(member.getCode());
		Integer shopInfoId = shopInfoService.applyShopCert(getCurrentMember(), shopInfo);
		return CommonResult.success(shopInfoId);
	}

	@OperationLog
	@ApiOperation(value = "申请入驻-完善店铺")
	@ResponseBody
	@RequestMapping(value={"/shopInfo/applyShopDetail"}, method=RequestMethod.POST)
	public CommonResult<String> applyShopDetail(UmsShopInfo shopInfo) throws Exception {
		shopInfoService.applyShopDetail(shopInfo);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "申请入驻-完善店铺")
	@ResponseBody
	@RequestMapping(value={"/shopInfo/submitShopInfo"}, method=RequestMethod.POST)
	public CommonResult<String> submitShopInfo(UmsShopInfo shopInfo) throws Exception {
		shopInfoService.submitShopInfo(shopInfo);
		return CommonResult.success(null);
	}
}