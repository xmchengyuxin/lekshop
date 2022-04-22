package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberOperationLog;
import com.chengyu.core.service.member.MemberOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  会员操作日志
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "会员操作日志")
@Controller
@RequestMapping("/system")
public class MemberOperationLogController extends AdminBaseController {
	
	@Autowired
	private MemberOperationLogService operationLogService;
	
	@ApiOperation(value = "会员日志列表")
	@ResponseBody
	@RequestMapping(value="/memberOperation/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberOperationLog>> getList(
			String memberName, String keyword,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		List<UmsMemberOperationLog> list = operationLogService.getMemberOperationLogList(null, memberName, keyword, null, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "删除会员日志")
	@ResponseBody
	@RequestMapping(value="/memberOperation/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(Integer days) {
		operationLogService.deleteLogByDays(days);
		return CommonResult.success(null);
	}
	
}