package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.UmsMemberRealname;
import com.chengyu.core.service.member.MemberRealnameService;
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
 * @title  实名认证
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "实名认证")
@Controller
@RequestMapping("/system")
public class MemberRealnameController extends AdminBaseController {
	
	@Autowired
	private MemberRealnameService memberRealnameService;

	@ApiOperation(value = "实名认证列表")
	@ResponseBody
	@RequestMapping(value="/memberRealname/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberRealname>> getList(
			BaseSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {

		List<UmsMemberRealname> list = memberRealnameService.getMemberRealnameList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@OperationLog
	@ApiOperation(value = "审核实名认证")
	@ResponseBody
	@RequestMapping(value="/memberRealname/verify", method=RequestMethod.POST)
	public CommonResult<String> verify(String ids, Integer status, String remark) throws ServiceException {
		if(StringUtils.isBlank(ids)){
			return CommonResult.failed("请至少选择一条数据");
		}
		if(status == CommonConstant.SUS_INT){
			for(String id : ids.split(CommonConstant.DH_REGEX)){
				memberRealnameService.verifyMemberRealnameSus(Integer.valueOf(id));
			}
		}else if(status == CommonConstant.FAIL_INT){
			for(String id : ids.split(CommonConstant.DH_REGEX)){
				memberRealnameService.verifyMemberRealnameFail(Integer.valueOf(id), remark);
			}
		}else{
			return CommonResult.failed("审批状态不正确");
		}
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改实名认证")
	@ResponseBody
	@RequestMapping(value="/memberRealname/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsMemberRealname realname) throws ServiceException {
		memberRealnameService.update(realname);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "删除实名认证")
	@ResponseBody
	@RequestMapping(value="/memberRealname/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isBlank(ids)){
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			memberRealnameService.deleteMemberRealname(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}

}