package com.chengyu.core.controller.goods;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.GoodsSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoods;
import com.chengyu.core.service.goods.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @title  商品
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "商品")
@Controller
@RequestMapping("/system")
public class GoodsController extends AdminBaseController {
	
	@Autowired
	private GoodsService goodsService;
	
	@ApiOperation(value = "商品列表")
	@ResponseBody
	@RequestMapping(value="/goods/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<PmsGoods>> getList(GoodsSearchForm form,
													   @RequestParam(value = "page", defaultValue = "1") Integer page,
													   @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<PmsGoods> list = goodsService.getGoodsList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "修改排序")
	@ResponseBody
	@RequestMapping(value="/goods/updateSort", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(Integer id, Integer sort) throws ServiceException {
		PmsGoods updateGoods = new PmsGoods();
		updateGoods.setId(id);
		updateGoods.setSort(sort);
		goodsService.updateGoods(updateGoods);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "批量上架")
	@ResponseBody
	@RequestMapping(value="/goods/shangjia", method=RequestMethod.POST)
	public CommonResult<String> shangjia(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.shangjiaGoods(goodsIdList);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "批量下架")
	@ResponseBody
	@RequestMapping(value="/goods/xiajia", method=RequestMethod.POST)
	public CommonResult<String> xiajia(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.xiajiaGoods(goodsIdList);
		return CommonResult.success(null);
	}

	private List<Integer> getIdList(String ids){
		List<String> idStrList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		return Convert.convert(new TypeReference<List<Integer>>() {}, idStrList);
	}

	@OperationLog
	@ApiOperation(value = "删除商品")
	@ResponseBody
	@RequestMapping(value="/goods/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> goodsIdList = getIdList(ids);
		goodsService.deleteGoods(goodsIdList);
		return CommonResult.success(null);
	}
}