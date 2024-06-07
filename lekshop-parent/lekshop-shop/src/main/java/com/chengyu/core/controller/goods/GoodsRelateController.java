package com.chengyu.core.controller.goods;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsRelateResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoodsRelate;
import com.chengyu.core.service.goods.GoodsRelateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "关联商品")
@Controller
@RequestMapping("/member")
public class GoodsRelateController extends ShopBaseController {
	
	@Autowired
	private GoodsRelateService goodsRelateService;

	@ApiOperation(value = "关联商品列表")
	@ResponseBody
	@RequestMapping(value="/goodsRelate/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<GoodsRelateResult>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		CommonPage<GoodsRelateResult> pageInfo = goodsRelateService.getGoodsRelateList(page, pageSize);
		return CommonResult.success(pageInfo);
	}

	@OperationLog
	@ApiOperation(value = "添加关联商品")
	@ResponseBody
	@RequestMapping(value="/goodsRelate/add", method=RequestMethod.POST)
	public CommonResult<String> add(PmsGoodsRelate relate) {
		goodsRelateService.addGoodsRelate(relate);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改关联商品顺序")
	@ResponseBody
	@RequestMapping(value="/goodsRelate/updateSort", method=RequestMethod.POST)
	public CommonResult<String> updateSort(Integer relateId, Integer sort) {
		goodsRelateService.updateSort(relateId, sort);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除关联商品")
	@ResponseBody
	@RequestMapping(value="/goodsRelate/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		List<Integer> idList = Convert.convert(new TypeReference<List<Integer>>() {}, ids.split(CommonConstant.DH_REGEX));
		goodsRelateService.deleteGoodsRelate(idList);
		return CommonResult.success(null);
	}
}