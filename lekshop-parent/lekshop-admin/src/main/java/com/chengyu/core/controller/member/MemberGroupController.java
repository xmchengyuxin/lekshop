package com.chengyu.core.controller.member;

import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberGroup;
import com.chengyu.core.service.member.MemberGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  会员组别
 * @author LeGreen
 * @date   2021/1/15
 */
@Api(tags = "会员组别")
@Controller
@RequestMapping("/system")
public class MemberGroupController extends AdminBaseController {
	
	@Autowired
	private MemberGroupService memberGroupService;
	
	@ApiOperation(value = "会员组别列表")
	@ResponseBody
	@RequestMapping(value="/memberGroup/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberGroup>> getList(
			Integer type,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		
		List<UmsMemberGroup> list = memberGroupService.getMemberGroupList(type, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "会员组别选择器")
	@ResponseBody
	@RequestMapping(value="/memberGroup/getSelector", method=RequestMethod.GET)
	public CommonResult<List<UmsMemberGroup>> getSelector(Integer type) {
		if(type == null){
			return CommonResult.success(memberGroupService.getAllGroupList());
		}else if(type == CommonConstant.BUYER){
			return CommonResult.success(memberGroupService.getAllBuyerGroupList());
		}else{
			return CommonResult.success(memberGroupService.getAllSellerGroupList());
		}
	}
	
	@OperationLog
	@ApiOperation(value = "添加编辑会员组别")
	@ResponseBody
	@RequestMapping(value="/memberGroup/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsMemberGroup memberGroup) {
		memberGroupService.addMemberGroup(memberGroup);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改会员组别状态")
	@ResponseBody
	@RequestMapping(value="/memberGroup/updateStatus", method=RequestMethod.POST)
	public CommonResult<String> updateStatus(Integer id, Integer status) {
		memberGroupService.updateStatus(id, status);
		return CommonResult.success(null);
	}
	
	@OperationLog
	@ApiOperation(value = "删除会员组别")
	@ResponseBody
	@RequestMapping(value="/memberGroup/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return CommonResult.failed("请至少选择一条数据");
		}
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			memberGroupService.deleteGroup(Integer.valueOf(id));
		}
		return CommonResult.success(null);
	}
}