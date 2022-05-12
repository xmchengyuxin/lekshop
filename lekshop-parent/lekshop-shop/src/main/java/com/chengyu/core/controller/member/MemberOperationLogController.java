package com.chengyu.core.controller.member;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
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
@RequestMapping("/member")
public class MemberOperationLogController extends ShopBaseController {
	
	@Autowired
	private MemberOperationLogService operationLogService;
	
	@ApiOperation(value = "会员日志列表")
	@ResponseBody
	@RequestMapping(value="/memberOperation/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberOperationLog>> getList(
			String keyword,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		List<UmsMemberOperationLog> list = operationLogService.getMemberOperationLogList(getCurrentMemberId(), null, keyword, null, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

}