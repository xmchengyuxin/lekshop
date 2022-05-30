package com.chengyu.core.controller.goods;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PmsGoodsParams;
import com.chengyu.core.service.goods.GoodsParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  商品参数
 * @author LeGreen
 * @date   2022/5/16
 */
@Api(tags = "商品参数")
@Controller
@RequestMapping("/system")
public class GoodsParamsController extends AdminBaseController {
	
	@Autowired
	private GoodsParamsService goodsParamsService;
	
	@ApiOperation(value = "商品参数列表")
	@ResponseBody
	@RequestMapping(value="/goodsQuality/query", method=RequestMethod.GET)
    public CommonResult<List<PmsGoodsParams>> get(Integer goodsId) throws Exception{
		return CommonResult.success(goodsParamsService.getGoodsParamsList(goodsId));
    }
	
}