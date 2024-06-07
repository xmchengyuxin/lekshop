package com.chengyu.core.controller.point;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.enums.GoodsEnums;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PointGoods;
import com.chengyu.core.service.point.PointGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "积分商城商品")
@Controller
@RequestMapping("/common/point")
public class PointGoodsController extends UserBaseController {
	
	@Autowired
	private PointGoodsService goodsService;

	@ApiOperation(value = "搜索积分商城商品(查询商品列表大部分用此接口)")
	@ResponseBody
	@RequestMapping(value="/goods/search", method=RequestMethod.GET)
	public CommonResult<CommonPage<PointGoods>> search(GoodsSearchForm form, Integer page, Integer pageSize) {
		form.setStatus(GoodsEnums.GoodsStatus.SELL.getValue());
		List<PointGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "积分商品详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsId", value = "商品ID"),
	})
	@ResponseBody
	@RequestMapping(value="/goods/get", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> get(Integer goodsId) {
		Map<String,Object> result = new HashMap<>();
		PointGoods goods = goodsService.getGoods(goodsId);
		if(goods == null){
			return CommonResult.success(result);
		}
		result.put("goods", goods);
		return CommonResult.success(result);
	}

}