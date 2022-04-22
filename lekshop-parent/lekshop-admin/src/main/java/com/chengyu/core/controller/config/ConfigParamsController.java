package com.chengyu.core.controller.config;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.enums.SecretEnum;
import com.chengyu.core.domain.form.ParamsSearchForm;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.service.config.ParamsManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  多元化配置管理
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "多元化配置")
@Controller
@RequestMapping("/system")
public class ConfigParamsController extends AdminBaseController {

    @Autowired
    private ParamsManager paramsManager;

    @ApiOperation(value = "多元化参数")
    @ResponseBody
    @RequestMapping(value="/params/get", method= RequestMethod.GET)
    public CommonResult<Object> getParams(ParamsSearchForm form) {
        Object object = paramsManager.getParamsByKey(form);
        return CommonResult.success(object);
    }

    @OperationLog
    @ApiOperation(value = "修改参数设置")
    @ResponseBody
    @RequestMapping(value="/params/editSubmit", method=RequestMethod.POST)
    public CommonResult<String> addOrUpdate(String key, String jsonData) {
        paramsManager.addOrUpdate(key, jsonData);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "获取密保问题")
    @ResponseBody
    @RequestMapping(value="/secret/getList", method=RequestMethod.GET)
    public CommonResult<List<Map<String, String>>> getSecretList() {
        List<Map<String, String>> list = new ArrayList<>();
        for(SecretEnum secretEnum : SecretEnum.values()){
            Map<String, String> map = new HashMap<>();
            map.put("key", secretEnum.getKey());
            map.put("question", secretEnum.getQuestion());
            list.add(map);
        }
        return CommonResult.success(list);
    }
}