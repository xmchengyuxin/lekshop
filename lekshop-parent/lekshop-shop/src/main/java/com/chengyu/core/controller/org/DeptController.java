package com.chengyu.core.controller.org;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.DeptResult;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OrgDept;
import com.chengyu.core.model.UmsShop;
import com.chengyu.core.service.org.OrgDeptService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "部门管理")
@Controller
@RequestMapping("/member")
public class DeptController extends ShopBaseController {

    @Autowired
    private OrgDeptService deptService;

    @ApiOperation(value = "部门列表")
    @ResponseBody
    @RequestMapping(value = "/dept/getList", method = RequestMethod.GET)
    public CommonResult<List<DeptResult>> getList() throws ServiceException {
        List<DeptResult> list = deptService.getAllDepts(getCurrentShopId(), 0);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "查询直属下级部门")
    @ResponseBody
    @RequestMapping(value = "/dept/getJuniorList", method = RequestMethod.GET)
    public CommonResult<List<OrgDept>> getJuniorList(Integer deptId) throws ServiceException {
        List<OrgDept> list = deptService.getJuniorDeptList(getCurrentShopId(), deptId);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "查询上级部门")
    @ResponseBody
    @RequestMapping(value = "/dept/getUpList", method = RequestMethod.GET)
    public CommonResult<List<DeptResult>> getUpList(Integer deptId) throws ServiceException {
        List<DeptResult> list = deptService.getUpDeptList(getCurrentShopId(), deptId, 999, false, false);
        return CommonResult.success(list);
    }

    @OperationLog
    @ApiOperation(value = "添加编辑部门")
    @ResponseBody
    @RequestMapping(value = "/dept/editSubmit", method = RequestMethod.POST)
    public CommonResult<String> editSubmit(OrgDept dept) throws ServiceException {
        UmsShop shop = getCurrentShop();
        dept.setShopId(shop.getId());
        dept.setShopName(shop.getName());
        if (dept.getId() == null) {
            deptService.addDept(dept);
        } else {
            deptService.updateDept(dept);
        }
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "删除部门")
    @ResponseBody
    @RequestMapping(value = "/dept/delete", method = RequestMethod.POST)
    public CommonResult<String> delete(String ids) throws ServiceException {
        if (StringUtils.isEmpty(ids)) {
            return CommonResult.failed("请至少选择一个部门");
        }

        for (String idStr : ids.split(CommonConstant.DH_REGEX)) {
            deptService.deleteDept(Integer.valueOf(idStr));
        }
        return CommonResult.success(null);
    }

    @OperationLog
    @ApiOperation(value = "初始化内置部门和角色")
    @ResponseBody
    @RequestMapping(value = "/dept/init", method = RequestMethod.POST)
    public CommonResult<String> init() throws ServiceException {
        UmsShop shop = getCurrentShop();
        deptService.init(shop);
        return CommonResult.success(null);
    }

}