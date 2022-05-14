package com.chengyu.core.controller.goods;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.service.goods.GoodsCateService;
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

@Api(tags = "电商分类")
@Controller
@RequestMapping("/common")
public class GoodsCateController extends UserBaseController {
	
	@Autowired
	private GoodsCateService goodsCateService;

	@ApiOperation(value = "电商分类列表")
	@ResponseBody
	@RequestMapping(value="/goodsCate/getList", method=RequestMethod.GET)
	public CommonResult<List<GoodsCateResult>> getList() {
		List<GoodsCateResult> list = goodsCateService.getGoodsCateResults(CommonConstant.YES_INT);
		return CommonResult.success(list);
	}

	@ApiOperation(value = "根据父级ID查询电商分类")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pid", value = "上级ID,为空时则查询一级分类"),
	})
	@ResponseBody
	@RequestMapping(value="/goodsCate/getListByPid", method=RequestMethod.GET)
	public CommonResult<List<PmsGoodsCate>> getListByLevel(Integer pid) {
		List<PmsGoodsCate> list = goodsCateService.getListByPid(pid);
		return CommonResult.success(list);
	}
}