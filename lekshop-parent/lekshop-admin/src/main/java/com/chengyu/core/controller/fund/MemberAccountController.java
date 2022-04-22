package com.chengyu.core.controller.fund;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.domain.result.KeyValueResult;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.model.UmsMemberAccountLog;
import com.chengyu.core.service.funds.MemberAccountLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title  资金明细
 * @author LeGreen
 * @date   2021/1/22
 */
@Api(tags = "资金明细")
@Controller
@RequestMapping("/system")
public class MemberAccountController extends AdminBaseController {
	
	@Autowired
	private MemberAccountLogService accountLogService;

	@ApiOperation(value = "资金明细")
	@ResponseBody
	@RequestMapping(value="/account/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMemberAccountLog>> getList(Integer page, Integer pageSize, AccountSearchForm form) throws Exception {
		List<UmsMemberAccountLog> list = accountLogService.getAccountLog(form, page , pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "资金类型选择器")
	@ResponseBody
	@RequestMapping(value="/accountType/getSelector", method=RequestMethod.GET)
	public CommonResult getSelector(Integer type) {
		return CommonResult.success(this.getKeyValueList(type));
	}

	private List<KeyValueResult> getKeyValueList(Integer type){
		List<KeyValueResult> list = CollectionUtil.newArrayList();
		if(type == 1){
			//钱包余额资金类型
			AccountEnums.MemberAccountTypes[] types = AccountEnums.MemberAccountTypes.values();
			for(AccountEnums.MemberAccountTypes mtype : types){
				list.add(new KeyValueResult(mtype.getName(), mtype.getValue()));
			}
		}else if(type == 2){
			//信用分资金类型
			AccountEnums.MemberPointTypes[] types = AccountEnums.MemberPointTypes.values();
			for(AccountEnums.MemberPointTypes mtype : types){
				list.add(new KeyValueResult(mtype.getName(), mtype.getValue()));
			}
		}else if(type == 3){
			//推广佣金资金类型
			AccountEnums.MemberSpmissionTypes[] types = AccountEnums.MemberSpmissionTypes.values();
			for(AccountEnums.MemberSpmissionTypes mtype : types){
				list.add(new KeyValueResult(mtype.getName(), mtype.getValue()));
			}
		}else if(type == 4){
			//佣金资金类型
			AccountEnums.MemberMissionTypes[] types = AccountEnums.MemberMissionTypes.values();
			for(AccountEnums.MemberMissionTypes mtype : types){
				list.add(new KeyValueResult(mtype.getName(), mtype.getValue()));
			}
		}
		return list;
	}

}