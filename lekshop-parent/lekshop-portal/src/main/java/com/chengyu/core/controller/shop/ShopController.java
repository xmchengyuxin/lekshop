package com.chengyu.core.controller.shop;

import cn.hutool.core.bean.BeanUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.member.MemberCollectShopService;
import com.chengyu.core.service.shop.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Api(tags = "店铺")
@Controller
@RequestMapping("/common")
public class ShopController extends UserBaseController {
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private MemberCollectShopService memberCollectShopService;

	@ApiOperation(value = "店铺详情")
	@ResponseBody
	@RequestMapping(value="/shop/get", method=RequestMethod.GET)
	public CommonResult<Map<String, Object>> getList(Integer shopId) {
		UmsShop shop = shopService.getShopById(shopId);
		Map<String, Object> map = BeanUtil.beanToMap(shop);
		//是否关注此店铺
		map.put("isCollectShop", memberCollectShopService.isCollectShop(getCurrentMemberOrNull(), shopId));
		return CommonResult.success(map);
	}
}