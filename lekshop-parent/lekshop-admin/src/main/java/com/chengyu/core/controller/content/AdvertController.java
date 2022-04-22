package com.chengyu.core.controller.content;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.CmsAdvert;
import com.chengyu.core.service.content.AdvertService;
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

@Api(tags = "AdvertController", description = "广告管理")
@Controller
@RequestMapping("/system")
public class AdvertController extends AdminBaseController {
	
	@Autowired
	private AdvertService advertService;
	
	@ApiOperation(value = "广告列表")
	@ResponseBody
	@RequestMapping(value="/advert/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<CmsAdvert>> getList(String cate, 
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		
		List<CmsAdvert> list = advertService.getAdvertList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "编辑广告")
	@ResponseBody
	@RequestMapping(value="/advert/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(CmsAdvert advert) throws Exception {
		if(advert.getId() != null && advert.getId() > 0L) {
			advertService.updateAdvert(advert);
		}else{
			advertService.addAdvert(advert);
		}
		
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "隐藏广告")
	@ResponseBody
	@RequestMapping(value="/advert/updateAdvertStatus", method=RequestMethod.POST)
	public CommonResult<String> updateAdvertStatus(Integer id, String status) throws Exception {
		CmsAdvert advert = new CmsAdvert();
		advert.setId(id);
		advert.setStatus(status);
		advertService.updateAdvert(advert);
		
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除广告")
	@ResponseBody
	@RequestMapping(value="/advert/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		
		for(String id : ids.split(",")){
			advertService.deleteAdvert(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
	
}