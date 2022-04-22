package com.chengyu.core.controller.system;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.SysAdmin;
import com.chengyu.core.service.system.AdminService;
import com.chengyu.core.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AdminController", description = "管理员管理")
@Controller
@RequestMapping("/system")
public class AdminController extends AdminBaseController {
	
	@Autowired
	private AdminService adminService;
	
	@ApiOperation(value = "管理员列表")
	@ResponseBody
	@RequestMapping(value="/admin/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<SysAdmin>> getList(
			Integer typeId,
			String name,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception{
		
		List<SysAdmin> list = adminService.getAdminList(typeId, name, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
	@ApiOperation(value = "当前管理员")
	@ResponseBody
	@RequestMapping(value="/admin/get", method=RequestMethod.GET)
	public CommonResult<SysAdmin> get(Principal principal) {
		String username = principal.getName();
        SysAdmin admin = adminService.getAdminByUsername(username);
        admin.setPassword(null);
		return CommonResult.success(admin);
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑管理员")
	@ResponseBody
	@RequestMapping(value="/admin/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> adminSubmit(SysAdmin admin){
		if(admin.getId() == null || admin.getId() <= 0L) {
			if(StringUtils.isEmpty(admin.getPassword())) {
				return CommonResult.failed("密码不能为空");
			}
		}
		
		admin.setUpdTime(new Date());
		if(admin.getId() == null || admin.getId() <= 0L) {
			admin.setAddTime(admin.getUpdTime());
			adminService.addAdmin(admin);
		} else {
			adminService.updateAdmin(admin);
		}
		
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除管理员")
	@ResponseBody
	@RequestMapping(value="/admin/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) throws ServiceException{
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一个角色");
		}
		
		for(String idStr : ids.split(",")){
			adminService.deleteAdmin(Integer.valueOf(idStr));
		}
		return CommonResult.success(null);
	}
	
}