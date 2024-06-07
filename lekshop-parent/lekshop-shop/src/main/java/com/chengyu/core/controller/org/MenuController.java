package com.chengyu.core.controller.org;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.result.ShopMenuResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.org.OrgMenuService;
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
@RequestMapping("/member")
public class MenuController extends ShopBaseController {

    @Autowired
    private OrgMenuService menuService;

    @ApiOperation(value = "菜单列表")
    @ResponseBody
    @RequestMapping(value = "/menu/getList", method = RequestMethod.GET)
    public CommonResult<List<ShopMenuResult>> getList() {
        List<ShopMenuResult> list = menuService.getAllMenus();
        return CommonResult.success(list);
    }

}