package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberBank;
import com.chengyu.core.service.member.MemberBankService;
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
 * @title  银行卡认证
 * @author LeGreen
 * @date   2021/1/8
 */
@Api(tags = "银行卡认证")
@Controller
@RequestMapping("/member")
public class MemberBankController extends ShopBaseController {
	
	@Autowired
	private MemberBankService memberBankService;

	@ApiOperation(value = "银行认证列表")
	@ResponseBody
	@RequestMapping(value="/memberBank/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberBank>> getList(
			BaseSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		form.setMemberId(getCurrentMemberId());
		List<UmsMemberBank> list = memberBankService.getMemberBankList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "修改银行认证")
	@ResponseBody
	@RequestMapping(value="/memberBank/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsMemberBank memberBank) throws ServiceException {
		UmsMember member = getCurrentMember();
		memberBank.setMemberId(member.getId());
		memberBank.setMemberName(member.getCode());
		memberBankService.update(memberBank);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除银行认证")
	@ResponseBody
	@RequestMapping(value="/memberBank/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isBlank(ids)){
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			memberBankService.deleteMemberBank(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}

}