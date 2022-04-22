package com.chengyu.core.controller.system;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysAdminType;
import com.chengyu.core.service.system.AdminTypeService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Api(tags = "AdminTypeController", description = "角色管理")
@Controller
@RequestMapping("/system")
public class AdminTypeController extends AdminBaseController {
	
	@Autowired
	private AdminTypeService adminTypeService;
	
	@ApiOperation(value = "角色列表")
	@ResponseBody
	@RequestMapping(value="/adminType/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<SysAdminType>> getList(
			Integer brandId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception{
		
		List<SysAdminType> list = adminTypeService.getAdminTypeList(page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑角色")
	@ResponseBody
	@RequestMapping(value="/adminType/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> submit(
			Integer id, 
			String name,
			String menuIds) throws Exception {
		if(StringUtils.isEmpty(menuIds)){
			return CommonResult.failed("请至少选择一项菜单!");
		}
		
		SysAdminType adminType = new SysAdminType();
		adminType.setId(id);
		adminType.setName(name);
		adminType.setUpdTime(new Date());
		adminType.setMenus(menuIds);
		adminType.setUpdTime(new Date());
		if(id == null || id <= 0L) {
			adminType.setAddTime(adminType.getUpdTime());
			adminTypeService.addAdminType(adminType);
		} else {
			adminTypeService.updateAdminType(adminType);
		}
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除角色")
	@ResponseBody
	@RequestMapping(value="/adminType/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一个角色");
		}
		
		for(String idStr : ids.split(",")){
			Integer id = Integer.valueOf(idStr);
			adminTypeService.deleteAdminType(id);
		}
		return CommonResult.success(null);
	}
}