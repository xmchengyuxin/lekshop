package com.chengyu.core.controller.shop;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsShopService;
import com.chengyu.core.service.shop.ShopServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  服务管理
 * @author LeGreen
 * @date   2022/5/6
 */
@Api(tags = "服务管理")
@Controller
@RequestMapping("/member")
public class ShopServiceController extends ShopBaseController {
	
	@Autowired
	private ShopServiceService shopServiceService;

	@ApiOperation(value = "服务选择器")
	@ResponseBody
	@RequestMapping(value="/shopService/getSelector", method=RequestMethod.GET)
	public CommonResult<List<UmsShopService>> getSelector() {
		List<UmsShopService> list = shopServiceService.getAllShopServices();
		return CommonResult.success(list);
	}

}