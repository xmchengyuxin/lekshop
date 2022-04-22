package com.chengyu.core.controller.fund;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberSpmissionLog;
import com.chengyu.core.service.funds.MemberSpmissionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  推广佣金明细
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "推广佣金明细")
@Controller
@RequestMapping("/system")
public class MemberSpmissionController extends AdminBaseController {
	
	@Autowired
	private MemberSpmissionLogService spmissionLogService;

	@ApiOperation(value = "推广佣金明细")
	@ResponseBody
	@RequestMapping(value="/spmissionLog/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberSpmissionLog>> getList(Integer page, Integer pageSize, AccountSearchForm form) throws Exception {
		List<UmsMemberSpmissionLog> list = spmissionLogService.getAccountLog(form, page , pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

}