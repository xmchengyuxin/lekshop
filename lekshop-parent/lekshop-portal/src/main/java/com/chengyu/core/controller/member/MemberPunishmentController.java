package com.chengyu.core.controller.member;

import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberPunishment;
import com.chengyu.core.service.member.MemberPunishmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  用户处罚列表
 * @author LeGreen
 * @date   2021/1/30
 */
@Api(tags = "用户处罚")
@Controller
@RequestMapping("/member")
public class MemberPunishmentController extends UserBaseController {
	
	@Autowired
	private MemberPunishmentService memberPunishmentService;

	@ApiOperation(value = "用户处罚列表")
	@ResponseBody
	@RequestMapping(value={"/punishment/getList"}, method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberPunishment>> getList(Integer page, Integer pageSize) {
		List<UmsMemberPunishment> list = memberPunishmentService.getPunishmentList(null, null, null, null, page ,pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

}