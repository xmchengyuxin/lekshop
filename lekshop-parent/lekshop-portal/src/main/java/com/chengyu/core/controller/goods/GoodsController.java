package com.chengyu.core.controller.goods;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.service.goods.GoodsService;
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

@Api(tags = "商品")
@Controller
@RequestMapping("/common")
public class GoodsController extends UserBaseController {
	
	@Autowired
	private GoodsService goodsService;

	@ApiOperation(value = "搜索商品(查询商品列表大部分用此接口)")
	@ResponseBody
	@RequestMapping(value="/goods/search", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> search(GoodsSearchForm form, Integer page, Integer pageSize) {
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "商品详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "商品ID"),
	})
	@ResponseBody
	@RequestMapping(value="/goods/get", method=RequestMethod.GET)
	public CommonResult<PmsGoods> get(Integer goodsId) {
		PmsGoods goods = goodsService.getGoods(goodsId);
		return CommonResult.success(goods);
	}
}