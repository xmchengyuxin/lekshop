package com.chengyu.core.controller.system;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.ShopMenuResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OrgMenu;
import com.chengyu.core.service.org.OrgMenuService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "菜单管理")
@Controller
@RequestMapping("/system")
public class ShopMenuController extends AdminBaseController {

    @Autowired
    private OrgMenuService menuService;

    @ApiOperation(value = "菜单列表")
    @ResponseBody
    @RequestMapping(value = "/orgMenu/getList", method = RequestMethod.GET)
    public CommonResult<List<ShopMenuResult>> getList() {
        List<ShopMenuResult> list = menuService.getAllMenus();
        return CommonResult.success(list);
    }

    @OperationLog
    @ApiOperation(value = "添加编辑菜单")
    @ResponseBody
    @RequestMapping(value = "/orgMenu/editSubmit", method = RequestMethod.POST)
    public CommonResult<String> editSubmit(OrgMenu menu) {
        if (menu.getId() == null) {
            menuService.addMenu(menu);
        } else {
            menuService.updateMenu(menu);
        }

        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "删除菜单")
    @ResponseBody
    @RequestMapping(value = "/orgMenu/delete", method = RequestMethod.POST)
    public CommonResult<String> delete(String ids) throws ServiceException {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.failed("请至少选择一个菜单");
        }

        for (String idStr : ids.split(CommonConstant.DH_REGEX)) {
            menuService.deleteMenu(Integer.valueOf(idStr));
        }
        return CommonResult.success(null);
    }

}