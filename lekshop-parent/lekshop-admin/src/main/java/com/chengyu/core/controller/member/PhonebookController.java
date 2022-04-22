package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.mapper.UmsPhonebookInviteMapper;
import com.chengyu.core.mapper.UmsPhonebookLogMapper;
import com.chengyu.core.mapper.UmsPhonebookMessageMapper;
import com.chengyu.core.model.UmsPhonebookInvite;
import com.chengyu.core.model.UmsPhonebookInviteExample;
import com.chengyu.core.model.UmsPhonebookLog;
import com.chengyu.core.model.UmsPhonebookLogExample;
import com.chengyu.core.model.UmsPhonebookMessage;
import com.chengyu.core.model.UmsPhonebookMessageExample;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@Api(tags = "手机通讯录")
@RequestMapping("/system")
public class PhonebookController extends AdminBaseController {

    @Autowired
    private UmsPhonebookLogMapper phonebookLogMapper;
    @Autowired
    private UmsPhonebookInviteMapper phonebookInviteMapper;
    @Autowired
    private UmsPhonebookMessageMapper phonebookMessageMapper;

    @ApiOperation(value = "邀请列表")
    @ResponseBody
    @RequestMapping(value="/phonebook/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsPhonebookInvite>> getList(
            String phoneId, String phoneModel, String thisPhone, String inviteCode,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        UmsPhonebookInviteExample example = new UmsPhonebookInviteExample();
        example.setOrderByClause("add_time desc");
        UmsPhonebookInviteExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(phoneId)){
            criteria.andPhoneIdEqualTo(phoneId);
        }
        if(StringUtils.isNotBlank(phoneModel)){
            criteria.andPhoneModelEqualTo(phoneModel);
        }
        if(StringUtils.isNotBlank(thisPhone)){
            criteria.andThisPhoneEqualTo(thisPhone);
        }
        if(StringUtils.isNotBlank(inviteCode)){
            criteria.andInviteCodeEqualTo(inviteCode);
        }
        List<UmsPhonebookInvite> list = phonebookInviteMapper.selectByExample(example);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @OperationLog
    @ApiOperation(value = "删除邀请记录")
    @ResponseBody
    @RequestMapping(value="/phonebook/delete", method=RequestMethod.POST)
    public CommonResult<String> delete(String ids) throws Exception {
        if(StringUtils.isEmpty(ids)) {
            return CommonResult.failed("请至少选择一个记录");
        }

        for(String idStr : ids.split(",")){
            Integer id = Integer.valueOf(idStr);
            phonebookInviteMapper.deleteByPrimaryKey(id);
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "通讯录列表")
    @ResponseBody
    @RequestMapping(value="/phonebookLog/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsPhonebookLog>> getList(
            Integer inviteId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        UmsPhonebookLogExample example = new UmsPhonebookLogExample();
        UmsPhonebookLogExample.Criteria criteria = example.createCriteria();
        criteria.andInviteIdEqualTo(inviteId);
        List<UmsPhonebookLog> list = phonebookLogMapper.selectByExample(example);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "短信列表")
    @ResponseBody
    @RequestMapping(value="/phonebookMessage/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsPhonebookMessage>> getMessageList(
            Integer inviteId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        UmsPhonebookMessageExample example = new UmsPhonebookMessageExample();
        UmsPhonebookMessageExample.Criteria criteria = example.createCriteria();
        criteria.andInviteIdEqualTo(inviteId);
        List<UmsPhonebookMessage> list = phonebookMessageMapper.selectByExampleWithBLOBs(example);
        return CommonResult.success(CommonPage.restPage(list));
    }
}
