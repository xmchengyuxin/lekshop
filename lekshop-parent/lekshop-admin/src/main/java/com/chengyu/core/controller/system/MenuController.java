package com.chengyu.core.controller.system;

import java.security.Principal;
import java.util.List;

import com.chengyu.core.component.DemoStationForbid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.result.MenuResult;
import com.chengyu.core.domain.result.MenuSingleResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysMenu;
import com.chengyu.core.service.system.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @类描述   	菜单管理
 * @作者   		LeGreen
 * @创建时间  	2020年3月10日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年3月10日             
 *     -----------------------------------------------------------
 * </pre>
 */
@Controller
@Api(tags = "MenuController", description = "菜单管理")
@RequestMapping("/system")
public class MenuController extends AdminBaseController {
	
	@Autowired
	private MenuService menuService;
	
	@ApiOperation(value = "菜单列表")
	@ResponseBody
	@RequestMapping(value="/menu/getList", method=RequestMethod.GET)
	public CommonResult<List<MenuResult>> getList() throws Exception {
		return CommonResult.success(menuService.getMenuList());
	}
	
	@ApiOperation(value = "获取单个菜单")
	@ResponseBody
	@RequestMapping(value="/menu/get", method=RequestMethod.GET)
	public CommonResult<MenuSingleResult> get(Integer id) throws Exception {
		return CommonResult.success(menuService.getMenuById(id));
	}

	@DemoStationForbid
	@OperationLog
	@ApiOperation(value = "提交菜单")
	@ResponseBody
	@RequestMapping(value="/menu/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> adminSubmit(SysMenu menu, Principal principal) throws Exception {
		menu.setUpdBy(principal.getName());
		menuService.updateMenu(menu);
		return CommonResult.success(null);
	}

	@DemoStationForbid
	@OperationLog
	@ApiOperation(value = "删除菜单")
	@ResponseBody
	@RequestMapping(value="/menu/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(Integer id) throws Exception {
		menuService.deleteMenu(id);
		return CommonResult.success(null);
	}
}