package com.chengyu.core.controller.log;

import java.util.List;

import com.chengyu.core.mapper.SysAdminLoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysAdminLoginLog;
import com.chengyu.core.model.SysAdminLoginLogExample;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AdminLoginLogController", description = "登录日志管理")
@Controller
@RequestMapping("/system")
public class AdminLoginLogController extends AdminBaseController {
	
	@Autowired
	private SysAdminLoginLogMapper adminLoginMapper;
	
	@ApiOperation(value = "登录日志列表")
	@ResponseBody
	@RequestMapping(value="/adminLogin/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<SysAdminLoginLog>> getList(
			String adminName,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize)  throws Exception{
		
		PageHelper.startPage(page, pageSize);
		
		SysAdminLoginLogExample example = new SysAdminLoginLogExample();
		SysAdminLoginLogExample.Criteria criteria = example.createCriteria(); 
		if(StringUtils.isNotBlank(adminName)){
			criteria.andAdminNameLike("%" + adminName + "%");
		}
		example.setOrderByClause("add_time desc");
		List<SysAdminLoginLog> list = adminLoginMapper.selectByExample(example);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
}