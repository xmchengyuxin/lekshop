package com.chengyu.core.controller.member;

import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.MemberSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "客户列表")
@Controller
@RequestMapping("/member")
public class CustomerController extends ShopBaseController {
	
	@ApiOperation(value = "与店铺相关的会员列表")
	@ResponseBody
	@RequestMapping(value="/member/getSimpleList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<UmsMember>> getSimpleList(
			MemberSearchForm form, Integer page, Integer pageSize) {
		List<UmsMember> list = memberService.getMemberList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}


}