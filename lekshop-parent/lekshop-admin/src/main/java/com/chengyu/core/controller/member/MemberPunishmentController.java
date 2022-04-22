package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberPunishment;
import com.chengyu.core.service.member.MemberPunishmentService;
import com.chengyu.core.utils.StringUtils;
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
 * @title  用户处罚
 * @author LeGreen
 * @date   2021/1/30
 */
@Api(tags = "用户处罚")
@Controller
@RequestMapping("/system")
public class MemberPunishmentController extends AdminBaseController {
	
	@Autowired
	private MemberPunishmentService memberPunishmentService;

	@ApiOperation(value = "用户处罚列表")
	@ResponseBody
	@RequestMapping(value="/memberPunishment/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberPunishment>> getList(
			String memberName,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {

		List<UmsMemberPunishment> list = memberPunishmentService.getPunishmentList(null, memberName, null, null, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "删除用户处罚")
	@ResponseBody
	@RequestMapping(value="/memberPunishment/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isBlank(ids)){
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			memberPunishmentService.deletePunishment(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "添加用户处罚")
	@ResponseBody
	@RequestMapping(value="/memberPunishment/add", method=RequestMethod.POST)
	public CommonResult<String> addSystemBlock(UmsMemberPunishment punishment) throws ServiceException {
		memberPunishmentService.addPunishment(punishment);
		return CommonResult.success(null);
	}

}