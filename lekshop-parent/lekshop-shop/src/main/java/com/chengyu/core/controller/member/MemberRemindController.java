package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberRemind;
import com.chengyu.core.service.member.MemberRemindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  消息提醒
 * @author LeGreen
 * @date   2021/12/28
 */
@Api(tags = "消息提醒")
@Controller
@RequestMapping("/member")
public class MemberRemindController extends UserBaseController {
	
	@Autowired
	private MemberRemindService memberRemindService;
	
	@ApiOperation(value = "提醒列表")
	@ResponseBody
	@RequestMapping(value="/remind/getList", method=RequestMethod.GET)
    public CommonResult<CommonPage<UmsMemberRemind>> getNews(Integer page, Integer pageSize) throws Exception{
		List<UmsMemberRemind> list = memberRemindService.getRemindList(getCurrentMemberId(), 1, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
    }
	
	@ResponseBody
	@ApiOperation(value = "统计未读提醒")
	@RequestMapping(value="/remind/count", method=RequestMethod.GET)
    public CommonResult<Map<String,Object>> countNews() throws Exception{
		Long num = memberRemindService.countUnReadNum(getCurrentMemberId(), 1);
		Map<String,Object> result = new HashMap<>(16);
		result.put("num", num);
		return CommonResult.success(result);
    }
	
	@ResponseBody
	@ApiOperation(value = "全部置为已读")
	@RequestMapping(value="/remind/readAll", method=RequestMethod.POST)
    public CommonResult<String> readAll() throws Exception{
		memberRemindService.readAll(getCurrentMemberId(), 1);
		return CommonResult.success(null);
    }
}