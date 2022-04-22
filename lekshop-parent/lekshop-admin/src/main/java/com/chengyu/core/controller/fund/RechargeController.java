package com.chengyu.core.controller.fund;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.RechargeSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberRecharge;
import com.chengyu.core.service.member.MemberRechargeService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @title  充值管理
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "充值管理")
@Controller
@RequestMapping("/system")
public class RechargeController extends AdminBaseController {
	
	@Autowired
	private MemberRechargeService rechargeService;

	@ApiOperation(value = "充值列表")
	@ResponseBody
	@RequestMapping(value="/recharge/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberRecharge>> getList(Integer page, Integer pageSize, RechargeSearchForm form) throws Exception {
		if(StringUtils.isNotBlank(form.getStatusArr())){
			form.setStatusList(Arrays.asList(form.getStatusArr().split(",")));
		}
		List<UmsMemberRecharge> list = rechargeService.getRechargeLog(form, page , pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "审核充值")
	@ResponseBody
	@RequestMapping(value="/recharge/verifyEditSubmit", method=RequestMethod.POST)
	public CommonResult<String> verifyEditSubmit(String ids, Integer status, String remark) throws Exception {
		if(StringUtils.isBlank(ids)){
			return CommonResult.failed("请至少选择一条数据");
		}
		if (status == null) {
			return CommonResult.failed("请先选择审核结果！");
		}

		super.validateRepeat("do-adminverifyRecharge-time-between-"+ids, "请勿重复审核,5秒后重试!", 5000L);

		for(String id : ids.split(CommonConstant.DH_REGEX)){
			rechargeService.verifyRecharge(Integer.valueOf(id), getSessionAdmin().getCode(), status, remark);
		}
		return CommonResult.success(null);
	}

}