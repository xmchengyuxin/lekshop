package com.chengyu.core.controller.member;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsPhonebookInviteMapper;
import com.chengyu.core.mapper.UmsPhonebookLogMapper;
import com.chengyu.core.mapper.UmsPhonebookMessageMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@Api(tags = "手机通讯录")
@RequestMapping("/member")
public class PhonebookController extends UserBaseController {

    @Autowired
    private UmsPhonebookLogMapper phonebookLogMapper;
    @Autowired
    private UmsPhonebookInviteMapper phonebookInviteMapper;
    @Autowired
    private UmsPhonebookMessageMapper phonebookMessageMapper;

    @ApiOperation(value = "导入手机通讯录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneId", value = "手机唯一标识"),
            @ApiImplicitParam(name = "phoneModel", value = "手机型号"),
            @ApiImplicitParam(name = "thisPhone", value = "登记手机"),
            @ApiImplicitParam(name = "phoneJson", value = "通讯录>>[{name:名字,phone:电话}]"),
            @ApiImplicitParam(name = "messageJson", value = "短信>>[{name:发件人,message:短信,sendTime:发送时间,msgStatus:0发送>>1接收>>2草稿}]"),
            @ApiImplicitParam(name = "latlng", value = "经纬度"),
    })
    @RequestMapping(value = "/phonebook/importPhonebook", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importPhonebook(String phoneId, String phoneModel, String thisPhone, String phoneJson, String messageJson, String latlng) throws ServiceException {
        log.info("phoneId>>"+phoneId);
        log.info("phoneModel>>"+phoneModel);
        log.info("thisPhone>>"+thisPhone);
        log.info("phoneJson>>"+phoneJson);
        log.info("messageJson>>"+messageJson);
        log.info("latlng>>"+latlng);
        UmsMember member = getCurrentMember();
        UmsPhonebookInviteExample example = new UmsPhonebookInviteExample();
        example.createCriteria().andMemberIdEqualTo(member.getId());
        long count = phonebookInviteMapper.countByExample(example);
        if(count > 0){
            return CommonResult.success(null);
        }
        Date now = DateUtil.date();
        UmsPhonebookInvite invite = new UmsPhonebookInvite();
        invite.setMemberId(member.getId());
        invite.setMemberName(member.getCode());
        invite.setPhoneId(phoneId);
        invite.setPhoneModel(phoneModel);
        invite.setThisPhone(thisPhone);
        invite.setInviteCode(member.getCode());
        invite.setAddTime(now);
        invite.setAddBy(latlng);
        phonebookInviteMapper.insertSelective(invite);

        if(StringUtils.isNotBlank(phoneJson)){
            List<UmsPhonebookLog> logList = JSONArray.parseArray(phoneJson, UmsPhonebookLog.class);
            for(UmsPhonebookLog log : logList){
                try {
                    log.setInviteId(invite.getId());
                    log.setPhoneId(phoneId);
                    log.setPhoneModel(phoneModel);
                    log.setThisPhone(thisPhone);
                    log.setInviteCode(member.getCode());
                    log.setAddTime(now);
                    phonebookLogMapper.insertSelective(log);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(StringUtils.isNotBlank(messageJson)){
            List<UmsPhonebookMessage> logList = JSONArray.parseArray(messageJson, UmsPhonebookMessage.class);
            for(UmsPhonebookMessage message : logList){
                try {
                    message.setInviteId(invite.getId());
                    message.setPhoneId(phoneId);
                    message.setPhoneModel(phoneModel);
                    message.setThisPhone(thisPhone);
                    message.setInviteCode(member.getCode());
                    message.setAddTime(now);
                    phonebookMessageMapper.insertSelective(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return CommonResult.success(null);
    }
}
