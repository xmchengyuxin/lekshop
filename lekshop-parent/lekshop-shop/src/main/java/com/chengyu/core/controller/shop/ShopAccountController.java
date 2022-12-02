package com.chengyu.core.controller.shop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.result.ShopAccountRoleResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.model.UmsShopAccountLoginLog;
import com.chengyu.core.service.shop.ShopAccountService;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "子账号列表")
@Controller
@RequestMapping("/member")
public class ShopAccountController extends ShopBaseController {

    @Autowired
    private ShopAccountService shopAccountService;

    @ApiOperation(value = "子账号列表")
    @ResponseBody
    @RequestMapping(value = "/shopAccount/getList", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsShopAccount>> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

        List<UmsShopAccount> list = shopAccountService.getAccountList(getCurrentShopId(), page, pageSize);
        List<Map<String, Object>> memberList = Convert.convert(new TypeReference<List<Map<String, Object>>>() {
        }, list);
        if (CollectionUtil.isEmpty(memberList)) {
            return CommonResult.success(CommonPage.restPage(new ArrayList<>()));
        }
        for (Map<String, Object> map : memberList) {
            //最后登录时间
            UmsShopAccountLoginLog loginLog = shopAccountService.getLastLoginLog(MapUtil.getInt(map, "id"));
            if (loginLog != null) {
                map.put("lastLoginIp", loginLog.getIp());
            }
        }
        PageInfo pageInfo = new PageInfo<>(list);
        pageInfo.setList(memberList);
        return CommonResult.success(CommonPage.restPage(pageInfo));
    }

    @ApiOperation(value = "获取部门下的子账号")
    @ResponseBody
    @RequestMapping(value = "/shopAccount/getListByDept", method = RequestMethod.GET)
    public CommonResult<List<UmsShopAccount>> getListByDept(Integer deptId) {
        List<UmsShopAccount> list = shopAccountService.getAccountListByDept(deptId);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "子账号详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "accountId", value = "子账号ID"),
    })
    @ResponseBody
    @RequestMapping(value = "/shopAccount/getDetail", method = RequestMethod.GET)
    public CommonResult<Map<String, Object>> getDetail(Integer accountId) {
        Map<String, Object> map = new HashMap<>(16);
        UmsShopAccount member = shopAccountService.getAccountById(accountId);
        if (member == null) {
            return CommonResult.success(map);
        }
        Map<String, Object> memberMap = BeanUtil.beanToMap(member);
        UmsShopAccountLoginLog loginLog = shopAccountService.getLastLoginLog(member.getId());
        if (loginLog != null) {
            memberMap.put("lastLoginTime", loginLog.getAddTime());
            memberMap.put("lastLoginIp", loginLog.getIp() + " " + (StringUtils.isNotBlank(loginLog.getAddress()) ? loginLog.getAddress() : ""));
        }
        map.put("member", memberMap);
        return CommonResult.success(map);
    }

    @ApiOperation(value = "当前账号详情")
    @ResponseBody
    @RequestMapping(value = "/shopAccount/getInfo", method = RequestMethod.GET)
    public CommonResult<UmsShopAccount> getInfo() throws ServiceException {
        return CommonResult.success(getCurrentAccount());
    }

    @OperationLog
    @ApiOperation(value = "修改当前账号信息")
    @ResponseBody
    @RequestMapping(value = "/shopAccount/updateInfo", method = RequestMethod.POST)
    public CommonResult<String> updateInfo(UmsShopAccount shopAccount) throws ServiceException {
        shopAccount.setId(getCurrentAccount().getId());
        shopAccountService.updateAccount(shopAccount);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "修改子账号信息")
    @ResponseBody
    @RequestMapping(value = "/shopAccount/editSubmit", method = RequestMethod.POST)
    public CommonResult<String> editSubmit(UmsShopAccount shopAccount) throws ServiceException {
        shopAccountService.updateAccount(shopAccount);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "添加子账号")
    @ResponseBody
    @RequestMapping(value = {"/shopAccount/register"}, method = RequestMethod.POST)
    public CommonResult<Map<String, Object>> regSubmit(UmsShopAccount shopAccount) throws Exception {
        UmsShop shop = getCurrentShop();
        shopAccount.setShopId(shop.getId());
        shopAccount.setShopName(shop.getName());
        shopAccount.setUsername(shop.getMemberName()+":"+shopAccount.getUsername());
        shopAccount.setMemberId(shop.getMemberId());
        shopAccountService.register(shopAccount);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "子账号角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "子账号ID"),
    })
    @ResponseBody
    @RequestMapping(value = "/shopAccount/getRoleList", method = RequestMethod.GET)
    public CommonResult<List<ShopAccountRoleResult>> getRoleList(Integer accountId) {
        List<ShopAccountRoleResult> list = shopAccountService.getRoleList(accountId);
        return CommonResult.success(list);
    }

    @OperationLog
    @ApiOperation(value = "添加子账号角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleIds", value = "拥有角色集合,逗号隔开"),
        @ApiImplicitParam(name = "accountId", value = "子账号ID"),
    })
    @ResponseBody
    @RequestMapping(value = {"/shopAccount/addRole"}, method = RequestMethod.POST)
    public CommonResult<String> addRole(Integer accountId, String roleIds) {
        if(StringUtils.isBlank(roleIds)){
            return CommonResult.failed("请选择角色");
        }
        shopAccountService.addRole(accountId, roleIds);
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "删除子账号角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberRoleId", value = "子账号角色ID"),
    })
    @ResponseBody
    @RequestMapping(value = "/shopAccount/deleteRole", method = RequestMethod.POST)
    public CommonResult<String> deleteRole(Integer accountRoleId) {
        shopAccountService.deleteAccountRole(accountRoleId);
        return CommonResult.success(null);
    }

}