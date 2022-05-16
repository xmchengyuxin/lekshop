package com.chengyu.core.controller.shop;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.ShopCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.shop.ShopCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "店铺分类")
@Controller
@RequestMapping("/common")
public class ShopCateController extends UserBaseController {
	
	@Autowired
	private ShopCateService shopCateService;

	@ApiOperation(value = "店铺分类列表")
	@ResponseBody
	@RequestMapping(value="/shopCate/getList", method=RequestMethod.GET)
	public CommonResult<List<ShopCateResult>> getList(Integer shopId) {
		List<ShopCateResult> list = shopCateService.getShopCateResults(shopId, CommonConstant.YES_INT);
		return CommonResult.success(list);
	}
}