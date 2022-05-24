package com.chengyu.core.controller.shop;

import cn.hutool.core.bean.BeanUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.TrendsEnums;
import com.chengyu.core.domain.form.TrendsForm;
import com.chengyu.core.domain.result.WalkTrendsResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.member.MemberCollectShopService;
import com.chengyu.core.service.shop.ShopConfigService;
import com.chengyu.core.service.shop.ShopService;
import com.chengyu.core.service.walk.WalkMemberService;
import com.chengyu.core.service.walk.WalkTrendsService;
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
	@Autowired
	private ShopConfigService shopConfigService;
	@Autowired
	private WalkTrendsService walkTrendsService;
	@Autowired
	private WalkMemberService walkMemberService;

	@ApiOperation(value = "店铺详情")
	@ResponseBody
	@RequestMapping(value="/shop/get", method=RequestMethod.GET)
	public CommonResult<Map<String, Object>> getList(Integer shopId) {
		UmsShop shop = shopService.getShopById(shopId);
		Map<String, Object> map = BeanUtil.beanToMap(shop);
		//是否关注此店铺
		map.put("isCollectShop", memberCollectShopService.isCollectShop(getCurrentMemberOrNull(), shopId));
		map.put("cateStyle", shopConfigService.getShopConfig(shopId).getCateStyle());
		return CommonResult.success(map);
	}

	@ApiOperation(value = "店铺动态")
	@ResponseBody
	@RequestMapping(value="/shop/getTrendsList", method=RequestMethod.GET)
	public CommonResult<CommonPage<WalkTrendsResult>> search(Integer shopId, Integer page, Integer pageSize) {
		TrendsForm form = new TrendsForm();
		form.setStatus(CommonConstant.SUS_INT);
		form.setWalkMemberId(walkMemberService.getWalkMemberByMember(shopService.getShopById(shopId).getMemberId()).getId());
		form.setType(TrendsEnums.TrendsType.GOODS.getValue());
		CommonPage<WalkTrendsResult> list = walkTrendsService.getTrendsList(form, page, pageSize);
		return CommonResult.success(list);
	}
}