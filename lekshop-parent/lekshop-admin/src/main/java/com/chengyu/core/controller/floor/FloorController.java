package com.chengyu.core.controller.floor;

import com.chengyu.core.component.DemoStationForbid;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.FloorMobilePages;
import com.chengyu.core.model.FloorMobileTemplate;
import com.chengyu.core.service.floor.FloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "装修商城")
@Controller
@RequestMapping("/system")
public class FloorController extends AdminBaseController {
	
	@Autowired
	private FloorService floorService;
	
	@ApiOperation(value = "装修模板列表")
	@ResponseBody
	@RequestMapping(value="/floor/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<FloorMobileTemplate>> getList(
			Integer type,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<FloorMobileTemplate> list = floorService.getFloorMobileTemplateList(type, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "添加编辑装修模板")
	@ResponseBody
	@RequestMapping(value="/floor/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(FloorMobileTemplate config) throws ServiceException {
		if(config.getId() == null){
			floorService.addFloorMobileTemplate(config);
		}else{
			floorService.updateFloorMobileTemplate(config);
		}
		return CommonResult.success(null);
	}

	@DemoStationForbid
	@OperationLog
	@ApiOperation(value = "删除装修模板")
	@ResponseBody
	@RequestMapping(value="/floor/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(Integer id) throws ServiceException {
		floorService.deleteFloorMobileTemplateById(id);
		return CommonResult.success(null);
	}

	@DemoStationForbid
	@ApiOperation(value = "保存装修模板")
	@ResponseBody
	@RequestMapping(value="/floor/savePageData", method=RequestMethod.POST)
	public CommonResult<String> savePageData(Integer templateId, String name, Integer type, Integer status, String pageData, String oriData) {
		floorService.saveMobileData(templateId, name, type, status, pageData, oriData);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "装修模板数据列表")
	@ResponseBody
	@RequestMapping(value="/floor/getDataList", method=RequestMethod.GET)
	public CommonResult<FloorMobileTemplate> getDataList(Integer templateId) {
		FloorMobileTemplate template = floorService.getFloorMobileTemplate(templateId);
		return CommonResult.success(template);
	}
}