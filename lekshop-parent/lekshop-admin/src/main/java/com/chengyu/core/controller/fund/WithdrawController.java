package com.chengyu.core.controller.fund;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.domain.form.BankForm;
import com.chengyu.core.domain.form.WithdrawSearchForm;
import com.chengyu.core.domain.result.WithdrawCountResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberWithdraw;
import com.chengyu.core.service.member.MemberRelService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.member.MemberWithdrawService;
import com.chengyu.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @title  提现管理
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "提现管理")
@Controller
@RequestMapping("/system")
public class WithdrawController extends AdminBaseController {
	
	@Autowired
	private MemberWithdrawService withdrawService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRelService memberRelService;

	@ApiOperation(value = "提现列表")
	@ResponseBody
	@RequestMapping(value="/withdraw/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberWithdraw>> getList(Integer page, Integer pageSize, WithdrawSearchForm form) throws Exception {
		if(CommonConstant.YES.equals(form.getStatus())){
			form.setStatusList(CollectionUtil.newArrayList(form.getStatus()));
		}
		if(StringUtils.isNotBlank(form.getTjrMemberName())){
			//一级推荐人
			UmsMember tjr = memberService.getMemberByParams(form.getTjrMemberName(), MemberTypes.CODE);
			if(tjr == null){
				return CommonResult.success(CommonPage.restPage(new ArrayList<>()));
			}
			form.setMemberIdList(memberRelService.getAllDownMemberIds(tjr.getId()));;
		}
		List<UmsMemberWithdraw> list = withdrawService.getWithdrawLog(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "导出提现列表")
	@ResponseBody
	@RequestMapping(value="/withdraw/getExportList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberWithdraw>> getExportList(Integer page, Integer pageSize, WithdrawSearchForm form) throws Exception {
		if(CommonConstant.YES.equals(form.getStatus())){
			form.setStatusList(CollectionUtil.newArrayList(form.getStatus()));
		}
		if(StringUtils.isNotBlank(form.getTjrMemberName())){
			//一级推荐人
			UmsMember tjr = memberService.getMemberByParams(form.getTjrMemberName(), MemberTypes.CODE);
			if(tjr == null){
				return CommonResult.success(CommonPage.restPage(new ArrayList<>()));
			}
			form.setMemberIdList(memberRelService.getAllDownMemberIds(tjr.getId()));;
		}
		List<UmsMemberWithdraw> list = withdrawService.getWithdrawLog(form, page, pageSize);
		if(CollectionUtil.isNotEmpty(list)){
			for(UmsMemberWithdraw withdraw : list){
				withdraw.setAddBy(null);
				withdraw.setUpdBy(null);
				//查询上级和上上级
				List<UmsMember> memberList = memberRelService.getUpMemberByFenxiaoLevel(withdraw.getMemberId(), 2);
				if(CollectionUtil.isNotEmpty(memberList)){
					if(memberList.size() >= 1){
						withdraw.setAddBy(memberList.get(0).getCode());
					}
					if(memberList.size() >= 2){
						withdraw.setUpdBy(memberList.get(1).getCode());
					}
				}
			}
		}
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "审核提现")
	@ResponseBody
	@RequestMapping(value="/withdraw/verifyEditSubmit", method=RequestMethod.POST)
	public CommonResult<String> verifyEditSubmit(String ids, Integer status, String remark) throws Exception {
		if (status == null){
			return CommonResult.failed("请先选择审核结果！");
		}
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据!");
		}

		super.validateRepeat("do-adminverifyWithdraw-time-between-"+ids, "请勿重复审核,5秒后重试!", 5000L);

		String message = withdrawService.batchVerify(ids, status, getSessionAdmin().getCode(), remark);

		if(com.chengyu.core.utils.StringUtils.isNotBlank(message)){
			return CommonResult.failed(message);
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "设为处理中")
	@ResponseBody
	@RequestMapping(value="/withdraw/setDoing", method=RequestMethod.POST)
	public CommonResult<String> setDoing(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据!");
		}

		String message = withdrawService.batchSetDoing(ids);
		return CommonResult.success(message);
	}

	@OperationLog
	@ApiOperation(value = "查询支付宝余额")
	@ResponseBody
	@RequestMapping(value="/withdraw/queryZfbBalance", method=RequestMethod.GET)
	public CommonResult<WithdrawCountResult> queryZfbBalance(String ids) throws Exception {
		String balance = withdrawService.queryZfbBalance();
		WithdrawSearchForm form = new WithdrawSearchForm();
		List<String> idStrList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		List<Integer> idList = Convert.convert(new TypeReference<List<Integer>>() {}, idStrList);
		form.setIdList(idList);
		WithdrawCountResult result = withdrawService.countWithdraw(form);
		result.setTotalAmount(new BigDecimal(balance));
		return CommonResult.success(result);
	}

	@OperationLog
	@ApiOperation(value = "支付宝转账")
	@ResponseBody
	@RequestMapping(value="/withdraw/transferByZfb", method=RequestMethod.POST)
	public CommonResult<String> transferByZfb(String ids) throws Exception {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据!");
		}

		super.validateRepeat("do-adminverifyWithdraw-time-between-"+ids, "请勿重复审核,5秒后重试!", 5000L);

		String message = withdrawService.batchVerifySusByZfb(ids, getSessionAdmin().getCode());
		return CommonResult.success(message);
	}

	@ApiOperation(value = "统计提现金额")
	@ResponseBody
	@RequestMapping(value="/withdraw/count", method=RequestMethod.GET)
	public CommonResult<WithdrawCountResult> countWithdraw(WithdrawSearchForm form) throws Exception {
		if(CommonConstant.YES.equals(form.getStatus())){
			form.setStatusList(CollectionUtil.newArrayList(form.getStatus()));
		}
		WithdrawCountResult result = withdrawService.countWithdraw(form);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "最近的提现记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "提现方式>>1银行>>2支付宝>>3微信", required=true),
	})
	@ResponseBody
	@RequestMapping(value="/withdraw/getLast", method=RequestMethod.GET)
	public CommonResult<UmsMemberWithdraw> getLastWithdraw(Integer type, Integer memberId) {
		WithdrawSearchForm form = new WithdrawSearchForm();
		form.setMemberId(memberId);
		form.setType(type);
		List<UmsMemberWithdraw> list = withdrawService.getWithdrawLog(form, 1, 1);
		return CommonResult.success(CollectionUtil.isEmpty(list) ? null : list.get(0));
	}

	@ApiOperation(value = "申请提现")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "method", value = "出款方式>>1账户资金>>2佣金资金", required=true),
			@ApiImplicitParam(name = "type", value = "提现方式>>1银行>>2支付宝>>3微信", required=true),
			@ApiImplicitParam(name = "amount", value = "提现金额", required=true),
	})
	@ResponseBody
	@RequestMapping(value="/withdraw/apply", method=RequestMethod.POST)
	public CommonResult<String> apply(Integer memberId, BigDecimal amount, BankForm bankForm) throws Exception{
		UmsMember member = memberService.getMemberById(memberId);
		super.validateRepeat("do-withdraw-admin-time-between-" + member.getId(), "请勿重复操作,5秒后再试!", 5000L);
		withdrawService.withdrawByAdmin(member, 1, AccountEnums.AccountMethod.BALANCE.getValue(), amount, bankForm);
		return CommonResult.success(null);
	}
}