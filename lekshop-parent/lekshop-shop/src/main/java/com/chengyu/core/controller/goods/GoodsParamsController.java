package com.chengyu.core.controller.goods;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PmsGoodsParams;
import com.chengyu.core.service.goods.GoodsParamsService;
import com.chengyu.core.utils.StringUtils;
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
@RequestMapping("/member")
public class GoodsParamsController extends ShopBaseController {
	
	@Autowired
	private GoodsParamsService goodsParamsService;
	
	@ApiOperation(value = "商品参数列表")
	@ResponseBody
	@RequestMapping(value="/goodsQuality/query", method=RequestMethod.GET)
    public CommonResult<List<PmsGoodsParams>> get(Integer goodsId) throws Exception{
		return CommonResult.success(goodsParamsService.getGoodsParamsList(goodsId));
    }
	
	@ApiOperation(value = "添加修改商品参数")
	@ResponseBody
	@RequestMapping(value="/goodsQuality/add", method=RequestMethod.POST)
    public CommonResult<String> addGoodsQuality(PmsGoodsParams goodsQuality) {
		goodsParamsService.addGoodsParams(goodsQuality);
		return CommonResult.success(null);
    }
	
	@ApiOperation(value = "删除商品参数")
	@RequestMapping("/goodsQuality/delete")
    @ResponseBody
    public CommonResult<String> deleteComment(String ids) {
		if(StringUtils.isEmpty(ids)) return CommonResult.failed("请至少选择一条数据");
		
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			goodsParamsService.deleteGoodsParams(Integer.valueOf(id));
		}
		return CommonResult.success(null);
    }
	
}