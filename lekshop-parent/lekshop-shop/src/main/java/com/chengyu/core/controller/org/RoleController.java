package com.chengyu.core.controller.org;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OrgRole;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.org.OrgRoleService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "角色管理")
@Controller
@RequestMapping("/member")
public class RoleController extends ShopBaseController {

    @Autowired
    private OrgRoleService roleService;

    @ApiOperation(value = "角色列表")
    @ResponseBody
    @RequestMapping(value = "/role/getList", method = RequestMethod.GET)
    public CommonResult<CommonPage<OrgRole>> getList(Integer deptId,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
        List<OrgRole> list = roleService.getRoleList(getCurrentShopId(), deptId, page, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "角色选择器")
    @ResponseBody
    @RequestMapping(value = "/role/getSelector", method = RequestMethod.GET)
    public CommonResult<List<OrgRole>> getSelector(Integer deptId) throws ServiceException {
        List<OrgRole> list = roleService.getAllRolesByDeptId(getCurrentShopId(), deptId);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "查询角色关联菜单数据")
    @ResponseBody
    @RequestMapping(value = "/role/getMenus", method = RequestMethod.GET)
    public CommonResult<List<Integer>> getMenus(Integer roleId) {
        List<Integer> list = roleService.getMenusByRoleId(roleId);
        return CommonResult.success(list);
    }

    @OperationLog
    @ApiOperation(value = "添加编辑角色")
    @ResponseBody
    @RequestMapping(value = "/role/editSubmit", method = RequestMethod.POST)
    public CommonResult<String> editSubmit(OrgRole role, String menuIds) throws ServiceException {
        UmsShop shop = getCurrentShop();
        role.setShopId(shop.getId());
        role.setShopName(shop.getName());
        roleService.addRole(role, menuIds);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "删除角色")
    @ResponseBody
    @RequestMapping(value = "/role/delete", method = RequestMethod.POST)
    public CommonResult<String> delete(String ids) throws ServiceException {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.failed("请至少选择一个角色");
        }

        for (String idStr : ids.split(CommonConstant.DH_REGEX)) {
            roleService.deleteRole(Integer.valueOf(idStr));
        }
        return CommonResult.success(null);
    }

}