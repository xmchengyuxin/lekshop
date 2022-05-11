package com.chengyu.core.controller.shop;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.shop.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  店铺资料
 * @author LeGreen
 * @date   2022/5/9
 */
@Api(tags = "店铺资料")
@Controller
@RequestMapping("/member")
public class ShopController extends ShopBaseController {
	
	@Autowired
	private ShopService shopService;

	@ApiOperation(value = "店铺资料")
	@ResponseBody
	@RequestMapping(value="/shop/get", method=RequestMethod.GET)
	public CommonResult<UmsShop> get() throws ServiceException {
		UmsShop shopInfo = shopService.getShopByMemberId(getCurrentMember().getId());
		return CommonResult.success(shopInfo);
	}

	@OperationLog
	@ApiOperation(value = "修改店铺资料")
	@ResponseBody
	@RequestMapping(value={"/shop/update"}, method=RequestMethod.POST)
	public CommonResult<Integer> update(UmsShop shop) throws Exception {
		shop.setId(getCurrentShop().getId());
		shopService.updateShop(shop);
		return CommonResult.success(null);
	}

}