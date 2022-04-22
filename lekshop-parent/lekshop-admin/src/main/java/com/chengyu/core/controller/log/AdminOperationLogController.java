package com.chengyu.core.controller.log;

import java.util.List;

import com.chengyu.core.mapper.SysAdminOperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.SysAdminOperationLog;
import com.chengyu.core.model.SysAdminOperationLogExample;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AdminOperationLogController", description = "操作日志管理")
@Controller
@RequestMapping("/system")
public class AdminOperationLogController extends AdminBaseController {
	
	@Autowired
	private SysAdminOperationLogMapper adminOperationLogMapper;
	
	@ApiOperation(value = "操作日志列表")
	@ResponseBody
	@RequestMapping(value="/adminOperation/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<SysAdminOperationLog>> getList(
			String adminName,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception{
		
		PageHelper.startPage(page, pageSize);
		
		SysAdminOperationLogExample example = new SysAdminOperationLogExample();
		SysAdminOperationLogExample.Criteria criteria = example.createCriteria(); 
		if(StringUtils.isNotBlank(adminName)){
			criteria.andOperateNameLike("%" + adminName + "%");
		}
		example.setOrderByClause("add_time desc");
		List<SysAdminOperationLog> list = adminOperationLogMapper.selectByExampleWithBLOBs(example);
		return CommonResult.success(CommonPage.restPage(list));
	}
	
}