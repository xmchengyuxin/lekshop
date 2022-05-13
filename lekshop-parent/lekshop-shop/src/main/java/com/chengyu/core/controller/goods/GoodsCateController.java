package com.chengyu.core.controller.goods;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.goods.GoodsCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  电商分类
 * @author LeGreen
 * @date   2022/5/7
 */
@Api(tags = "电商分类")
@Controller
@RequestMapping("/member")
public class GoodsCateController extends ShopBaseController {
	
	@Autowired
	private GoodsCateService goodsCateService;
	
	@ApiOperation(value = "电商分类列表")
	@ResponseBody
	@RequestMapping(value="/goodsCate/getList", method=RequestMethod.GET)
	public CommonResult<List<GoodsCateResult>> getList(Integer status) throws ServiceException {
		List<GoodsCateResult> list = goodsCateService.getGoodsCateResults(status);
		return CommonResult.success(list);
	}

}