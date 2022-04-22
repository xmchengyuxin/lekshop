package com.chengyu.core.controller.member;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.enums.AccountEnums.MemberSpmissionTypes;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomCountMapper;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberAccount;
import com.chengyu.core.model.UmsMemberLoginLog;
import com.chengyu.core.model.UmsMemberRel;
import com.chengyu.core.service.funds.MemberSpmissionLogService;
import com.chengyu.core.service.member.MemberAccountService;
import com.chengyu.core.service.member.MemberRelService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  我的推广
 * @author LeGreen
 * @date   2021/1/9
 */
@Api(tags = "我的推广")
@Controller
@RequestMapping("/member")
public class MemberSpreadController extends UserBaseController {
	
	@Autowired
	private MemberRelService memberRelService;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private MemberSpmissionLogService spmissionLogService;
	@Autowired
	private CustomCountMapper countMapper;

	@ApiOperation(value = "推荐的用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "level", value = "1>>直推>>2二级推荐>>3三级推荐"),
	})
	@ResponseBody
	@RequestMapping(value="/spread/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<Map<String,Object>>> getList(Integer level, Integer page, Integer pageSize) throws ServiceException {
		if(level == null){
			level = 1;
		}
		List<UmsMemberRel> list = memberRelService.getDownMemberByLevel(getCurrentMemberId(), level, page, pageSize);
		List<Map<String,Object>> mapList = Convert.convert(new TypeReference<List<Map<String,Object>>>() {}, list);
		if(CollectionUtil.isEmpty(mapList)){
			return CommonResult.success(CommonPage.restPage(mapList));
		}

		for(Map<String,Object> map : mapList){
			Integer memberId = MapUtil.getInt(map, "downMemberId");
			UmsMember member = memberService.getMemberById(memberId);
			map.putAll(BeanUtil.beanToMap(member));
			//查询余额
			UmsMemberAccount account = accountService.getMemberAccount(memberId);
			map.putAll(BeanUtil.beanToMap(account));
			//查询发展的下线个数
			map.put("xiaxianNum", memberRelService.countDownNum(memberId, 1));
			//最后登录时间
			UmsMemberLoginLog loginLog = memberService.getLastLoginLog(memberId);
			map.put("lastLoginTime", loginLog == null ? "-" : loginLog.getAddTime());
			//贡献佣金
			AccountSearchForm form = new AccountSearchForm();
			form.setMemberId(getCurrentMemberId());
			form.setDevoteId(memberId);
			map.put("devoteAmount", spmissionLogService.countIncome(form));
		}
		PageInfo pageInfo = new PageInfo<>(list);
		pageInfo.setList(mapList);
		return CommonResult.success(CommonPage.restPage(pageInfo));
	}

	@ApiOperation(value = "推荐统计")
	@ResponseBody
	@RequestMapping(value="/spread/count", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> count() throws ServiceException {
		UmsMember member = getCurrentMember();
		Map<String,Object> result = new HashMap<>(16);
		result.put("oneSpreadNum", memberRelService.countDownNum(member.getId(), 1));
		result.put("twoSpreadNum", memberRelService.countDownNum(member.getId(), 2));
		result.put("threeSpreadNum", memberRelService.countDownNum(member.getId(), 3));
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(member.getId());
		form.setTypeList(Arrays.asList(
				MemberSpmissionTypes.ACCOUNT_FIRST_BUYER.getValue(),
				MemberSpmissionTypes.ACCOUNT_FIRST_SELLER.getValue(),
				MemberSpmissionTypes.ACCOUNT_FINISH_TASK.getValue(),
				MemberSpmissionTypes.ACCOUNT_PUBLISH_TASK.getValue(),
				MemberSpmissionTypes.ACCOUNT_TASK_REWARD.getValue(),
				MemberSpmissionTypes.ACCOUNT_DASHIFU_REWARD.getValue()));
		result.put("totalSpreadAmount", spmissionLogService.countIncome(form));
		return CommonResult.success(result);
	}

	@ApiOperation(value = "用户详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "用户ID"),
	})
	@ResponseBody
	@RequestMapping(value="/spread/getMemberDetail", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> getMemberDetail(Integer memberId) throws ServiceException {
		Map<String,Object> result = new HashMap<>(16);
		UmsMember member = memberService.getMemberById(memberId);
		result.putAll(BeanUtil.beanToMap(member));
		//查询余额
		UmsMemberAccount account = accountService.getMemberAccount(memberId);
		result.putAll(BeanUtil.beanToMap(account));
		//查询发展的下线个数
		result.put("xiaxianNum", memberRelService.countDownNum(memberId, 1));
		//最后登录时间
		UmsMemberLoginLog loginLog = memberService.getLastLoginLog(memberId);
		result.put("lastLoginTime", loginLog == null ? "-" : loginLog.getAddTime());
		//贡献佣金
		AccountSearchForm form = new AccountSearchForm();
		form.setMemberId(getCurrentMemberId());
		form.setDevoteId(memberId);
		result.put("devoteAmount", spmissionLogService.countIncome(form));
		return CommonResult.success(result);
	}
}