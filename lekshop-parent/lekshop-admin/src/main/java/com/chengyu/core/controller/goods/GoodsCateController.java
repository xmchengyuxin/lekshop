package com.chengyu.core.controller.goods;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.GoodsCateResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.PmsGoodsCate;
import com.chengyu.core.service.goods.GoodsCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/system")
public class GoodsCateController extends AdminBaseController {
	
	@Autowired
	private GoodsCateService goodsCateService;
	
	@ApiOperation(value = "电商分类列表")
	@ResponseBody
	@RequestMapping(value="/goodsCate/getList", method=RequestMethod.GET)
	public CommonResult<List<GoodsCateResult>> getList(Integer status) throws ServiceException {
		List<GoodsCateResult> list = goodsCateService.getGoodsCateResults(status);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "添加电商分类")
	@ResponseBody
	@RequestMapping(value="/goodsCate/add", method=RequestMethod.POST)
	public CommonResult<String> add(Integer pid, String name, Integer sort, String img, String target) {
		goodsCateService.addGoodsCate(pid, name, sort, img, target);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "编辑电商分类")
	@ResponseBody
	@RequestMapping(value="/goodsCate/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(PmsGoodsCate cate) throws ServiceException {
		if(cate.getId() == null){
			goodsCateService.addGoodsCate(cate.getPid(), cate.getName(), cate.getSort(), cate.getImg(), cate.getTarget());
		}else {
			goodsCateService.updateGoodsCate(cate);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除电商分类")
	@ResponseBody
	@RequestMapping(value="/goodsCate/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			goodsCateService.deleteGoodsCate(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}