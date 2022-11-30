package com.chengyu.core.controller.shop;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.result.ShopAccountOrgResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.service.shop.ShopPermissionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "子账号权限")
@Controller
@RequestMapping("/member")
public class ShopAccountRoleController extends ShopBaseController {

    @Autowired
    private ShopPermissionsService shopPermissionsService;

    @ApiOperation(value = "获取当前用户所有角色")
    @ResponseBody
    @RequestMapping(value = {"/getAllRole"}, method = RequestMethod.GET)
    public CommonResult<List<ShopAccountOrgResult>> getAllRole() throws ServiceException {
        return CommonResult.success(shopPermissionsService.getAccountOrg(getCurrentAccount()));
    }

    @ApiOperation(value = "切换用户角色")
    @ResponseBody
    @RequestMapping(value = {"/changeRole"}, method = RequestMethod.POST)
    public CommonResult<String> changeRole(Integer roleId) throws ServiceException {
        shopPermissionsService.changeRole(getCurrentAccount(), roleId);
        return CommonResult.success(null);
    }

}