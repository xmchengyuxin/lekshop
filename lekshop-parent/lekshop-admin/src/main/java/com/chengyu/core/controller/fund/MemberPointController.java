package com.chengyu.core.controller.fund;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberPointLog;
import com.chengyu.core.service.funds.MemberPointLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  积分明细
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "积分明细")
@Controller
@RequestMapping("/system")
public class MemberPointController extends AdminBaseController {
	
	@Autowired
	private MemberPointLogService memberPointLogService;

	@ApiOperation(value = "积分明细")
	@ResponseBody
	@RequestMapping(value="/point/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberPointLog>> getList(Integer page, Integer pageSize, AccountSearchForm form) throws Exception {
		List<UmsMemberPointLog> list = memberPointLogService.getAccountLog(form, page , pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

}