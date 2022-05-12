package com.chengyu.core.controller.member;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberLoginLogMapper;
import com.chengyu.core.model.UmsMemberLoginLog;
import com.chengyu.core.model.UmsMemberLoginLogExample;
import com.github.pagehelper.PageHelper;
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
 * @title  会员登录日志
 * @author LeGreen
 * @date   2022/5/12
 */
@Api(tags = "会员登录日志")
@Controller
@RequestMapping("/member")
public class MemberLoginLogController extends ShopBaseController {
	
	@Autowired
	private UmsMemberLoginLogMapper memberLoginLogMapper;
	
	@ApiOperation(value = "会员登录日志列表")
	@ResponseBody
	@RequestMapping(value="/login/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberLoginLog>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		PageHelper.startPage(page, pageSize);
		UmsMemberLoginLogExample example = new UmsMemberLoginLogExample();
		example.setOrderByClause("add_time desc");
		example.createCriteria().andMemberIdEqualTo(getCurrentMemberId());
		List<UmsMemberLoginLog> list = memberLoginLogMapper.selectByExample(example);
		return CommonResult.success(CommonPage.restPage(list));
	}

}