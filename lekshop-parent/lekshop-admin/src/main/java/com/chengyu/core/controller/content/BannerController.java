package com.chengyu.core.controller.content;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsBanner;
import com.chengyu.core.service.content.BannerService;
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

@Api(tags = "BannerController", description = "轮播图管理")
@Controller
@RequestMapping("/system")
public class BannerController extends AdminBaseController {
	
	@Autowired
	private BannerService bannerService;
	
	@ApiOperation(value = "轮播图列表")
	@ResponseBody
	@RequestMapping(value="/scrollImg/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsBanner>> getList(
			String location,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		List<CmsBanner> list = bannerService.getBannerList(location, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑轮播图")
	@ResponseBody
	@RequestMapping(value="/scrollImg/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsBanner scrollImg) throws Exception {
		if(scrollImg.getId() != null && scrollImg.getId() > 0L) {
			bannerService.updateBanner(scrollImg);
		} else {
			bannerService.addBanner(scrollImg);
		}
		
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除轮播图")
	@ResponseBody
	@RequestMapping(value="/scrollImg/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		
		for(String id : ids.split(",")){
			bannerService.deleteBannerById(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}