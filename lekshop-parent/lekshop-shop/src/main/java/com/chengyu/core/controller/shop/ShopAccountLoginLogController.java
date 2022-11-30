package com.chengyu.core.controller.shop;

import com.chengyu.core.component.DataPermissions;
import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsShopAccountLoginLogMapper;
import com.chengyu.core.model.UmsShopAccountLoginLog;
import com.chengyu.core.model.UmsShopAccountLoginLogExample;
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

@Api(tags = "子账号登录日志")
@Controller
@RequestMapping("/member")
public class ShopAccountLoginLogController extends ShopBaseController {
	
	@Autowired
	private UmsShopAccountLoginLogMapper accountLoginLogMapper;
	
	@ApiOperation(value = "子账号登录日志列表")
	@ResponseBody
	@RequestMapping(value="/login/getList", method=RequestMethod.GET)
	@DataPermissions
	public CommonResult<CommonPage<UmsShopAccountLoginLog>> getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		PageHelper.startPage(page, pageSize);
		UmsShopAccountLoginLogExample example = new UmsShopAccountLoginLogExample();
		example.setOrderByClause("add_time desc");
		List<UmsShopAccountLoginLog> list = accountLoginLogMapper.selectByExample(example);
		return CommonResult.success(CommonPage.restPage(list));
	}

}