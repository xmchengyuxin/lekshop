package com.chengyu.core.service.config;

import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.form.ParamsSearchForm;
import com.chengyu.core.model.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title  多元化参数管理器
 * @author LeGreen
 * @date   2021/1/9
 */
@Component
public class ParamsManager {

    @Getter
    enum ParamsType{
        /** 实名认证接口设置 **/
        CONFIG_REALNAME("config_realname"),
        /** 系统收款账号 **/
        CONFIG_ACCOUNT("config_account"),
        /** 用户佣金设置 **/
        CONFIG_MISSION("config_mission"),
        /** 提现设置 **/
        CONFIG_WITHDRAW("config_withdraw"),
        /** 公众号设置 **/
        CONFIG_GZH("config_gzh");

        private final String key;

        ParamsType(String key){
            this.key = key;
        }

        public static ParamsType get(String key){
            ParamsType[] types = values();
            for(ParamsType type : types){
                if(type.getKey().equals(key)){
                    return type;
                }
            }
            return null;
        }
    }


    @Autowired
    private ConfigAccountService configAccountService;
    @Autowired
    private ConfigMissionService configMissionService;
    @Autowired
    private ConfigWithdrawService configWithdrawService;
    @Autowired
    private ConfigRealnameService configRealnameService;
    @Autowired
    private ConfigGzhService configGzhService;

    public Object getParamsByKey(ParamsSearchForm form){
        ParamsType type = ParamsType.get(form.getKey());
        if(type == null){
            return null;
        }
        switch (type){
            case CONFIG_ACCOUNT :
                return configAccountService.getAccountByType(form.getAccountType());
            case CONFIG_MISSION:
                return configMissionService.getConfigMissionDetail(form.getMissionConfigId(), form.getGroupId());
            case CONFIG_WITHDRAW:
                return configWithdrawService.getConfigWithdrawByGroupId(form.getGroupId());
            case CONFIG_REALNAME:
                return configRealnameService.getConfigRealnameByGroupId(form.getGroupId());
            case CONFIG_GZH:
                return configGzhService.getGzhNoCache();
            default: return null;
        }
    }

    public void addOrUpdate(String key, String jsonForm){
        ParamsType type = ParamsType.get(key);
        if(type == null){
            return;
        }
        switch (type){
            case CONFIG_ACCOUNT :
                configAccountService.addConfigAccount(JSONUtil.toBean(jsonForm, ConfigAccount.class));
                break;
            case CONFIG_MISSION:
                configMissionService.addConfigMissionDetail(JSONUtil.toBean(jsonForm, ConfigMissionDetail.class));
                break;
            case CONFIG_WITHDRAW:
                configWithdrawService.addConfigWithdraw(JSONUtil.toBean(jsonForm, ConfigWithdraw.class));
                break;
            case CONFIG_REALNAME:
                configRealnameService.addConfigRealname(JSONUtil.toBean(jsonForm, ConfigRealname.class));
                break;
            case CONFIG_GZH:
                configGzhService.addConfigGzh(JSONUtil.toBean(jsonForm, ConfigGzh.class));
                break;
            default:
        }
    }
}
