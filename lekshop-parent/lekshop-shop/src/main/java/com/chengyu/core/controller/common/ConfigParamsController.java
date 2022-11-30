package com.chengyu.core.controller.common;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.ParamsSearchForm;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsShopAccount;
import com.chengyu.core.service.config.ParamsManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title  多元化配置管理
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "多元化配置")
@Controller
@RequestMapping("/common")
public class ConfigParamsController extends ShopBaseController {

    @Autowired
    private ParamsManager paramsManager;

    @ApiOperation(value = "多元化参数")
    @ResponseBody
    @RequestMapping(value="/params/get", method= RequestMethod.GET)
    public CommonResult<Object> getParams(ParamsSearchForm form) {
        UmsShopAccount shopAccount = getCurrentAccountOrNull();
        if(shopAccount != null){
            UmsMember member = memberService.getMemberById(shopAccount.getMemberId());
            form.setGroupId(member.getGroupId());
            form.setMissionConfigId(member.getMissionConfigId());
        }
        Object object = paramsManager.getParamsByKey(form);
        return CommonResult.success(object);
    }

}