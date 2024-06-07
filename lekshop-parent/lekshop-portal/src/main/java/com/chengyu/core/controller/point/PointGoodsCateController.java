package com.chengyu.core.controller.point;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.PointGoodsCate;
import com.chengyu.core.service.point.PointGoodsCateService;
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

@Api(tags = "积分商城分类")
@Controller
@RequestMapping("/common/point")
public class PointGoodsCateController extends UserBaseController {
	
	@Autowired
	private PointGoodsCateService goodsCateService;

	@ApiOperation(value = "积分商城列表")
	@ResponseBody
	@RequestMapping(value="/goodsCate/getList", method=RequestMethod.GET)
	public CommonResult<List<GoodsCateResult>> getList() {
		List<GoodsCateResult> list = goodsCateService.getGoodsCateResults(CommonConstant.YES_INT);
		return CommonResult.success(list);
	}

	@ApiOperation(value = "根据父级ID查询积分商城分类")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pid", value = "上级ID,为空时则查询一级分类"),
	})
	@ResponseBody
	@RequestMapping(value="/goodsCate/getListByPid", method=RequestMethod.GET)
	public CommonResult<List<PointGoodsCate>> getListByLevel(Integer pid) {
		List<PointGoodsCate> list = goodsCateService.getListByPid(pid);
		return CommonResult.success(list);
	}
}