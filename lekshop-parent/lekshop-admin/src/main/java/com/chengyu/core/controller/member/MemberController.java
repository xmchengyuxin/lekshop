package com.chengyu.core.controller.member;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.chengyu.core.component.OperationLog;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.domain.form.MemberSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomCountMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.funds.MemberMissionLogService;
import com.chengyu.core.service.funds.MemberPointLogService;
import com.chengyu.core.service.funds.MemberSpmissionLogService;
import com.chengyu.core.service.member.*;
import com.chengyu.core.service.system.SysInviteCodeService;
import com.chengyu.core.util.ValidateUtil;
import com.chengyu.core.util.ip.IpSearch;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title  会员列表
 * @author LeGreen
 * @date   2021/1/20
 */
@Api(tags = "会员列表")
@Controller
@RequestMapping("/system")
public class MemberController extends AdminBaseController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private MemberRealnameService memberRealnameService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private MemberMissionLogService publishPointLogService;
	@Autowired
	private MemberSpmissionLogService spmissionLogService;
	@Autowired
	private MemberPointLogService memberPointLogService;
	@Autowired
	private MemberRelService memberRelService;
	@Autowired
	private CustomCountMapper countMapper;
	@Autowired
	private MemberNewsService memberNewsService;
	@Autowired
	private MemberImeIdService memberImeIdService;
	@Autowired
	private SysInviteCodeService sysInviteCodeService;

	@ApiOperation(value = "会员列表")
	@ResponseBody
	@RequestMapping(value="/member/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMember>> getList(
			MemberSearchForm form,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws ServiceException {
		
		List<UmsMember> list = memberService.getMemberList(form, page, pageSize);
		List<Map<String,Object>> memberList = Convert.convert(new TypeReference<List<Map<String,Object>>>() {}, list);
		if(CollectionUtil.isEmpty(memberList)){
			return CommonResult.success(CommonPage.restPage(new ArrayList<>()));
		}
		for(Map<String,Object> map : memberList){
			map.put("account", accountService.getMemberAccount(MapUtil.getInt(map, "id")));
			//最后登录时间
			UmsMemberLoginLog loginLog = memberService.getLastLoginLog(MapUtil.getInt(map, "id"));
			if(loginLog != null){
				map.put("lastLoginIp", loginLog.getIp());
			}
		}
		PageInfo pageInfo = new PageInfo<>(list);
		pageInfo.setList(memberList);
		return CommonResult.success(CommonPage.restPage(pageInfo));
	}

	@ApiOperation(value = "直推列表")
	@ResponseBody
	@RequestMapping(value="/member/getZtList", method=RequestMethod.GET)
	public CommonResult<CommonPage<UmsMember>> getZtList(
			Integer memberId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws Exception {
		MemberSearchForm form = new MemberSearchForm();
		form.setTjrId(memberId);
		List<UmsMember> memberList = memberService.getMemberList(form, page, pageSize);
		return CommonResult.success(CommonPage.restPage(memberList));
	}

	@ApiOperation(value = "我的上级结构")
	@ResponseBody
	@RequestMapping(value="/member/getSjList", method=RequestMethod.GET)
	public CommonResult<List<Map<String,Object>>> getSjList(Integer memberId) throws Exception {
		List<UmsMemberRel> relList = memberRelService.getAllUpMemberByMemberId(memberId);
		if(CollectionUtil.isEmpty(relList)) {
			return CommonResult.success(null);
		}

		List<Map<String,Object>> memberList = new ArrayList<>();
		for(UmsMemberRel rel : relList){
			UmsMember member = memberService.getMemberById(rel.getUpMemberId());
			Map<String,Object> map = Convert.convert(new TypeReference<Map<String,Object>>() {}, member);
			map.put("level", rel.getType());
			memberList.add(map);
		}
		return CommonResult.success(memberList);
	}

	@ApiOperation(value = "会员详情")
	@ResponseBody
	@RequestMapping(value="/member/get", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> get(Integer memberId) throws ServiceException {
		Map<String,Object> map = new HashMap<>(16);
		map.put("member", memberService.getMemberById(memberId));
		map.put("memberAccount", accountService.getMemberAccount(memberId));
		map.put("realname", memberRealnameService.getMemberRealnameByMemberId(memberId));
		return CommonResult.success(map);
	}

	@ApiOperation(value = "会员详情加强版")
	@ResponseBody
	@RequestMapping(value="/member/getDetail", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> getDetail(Integer memberId, String uid, String phone, String inviteCode) {
		Map<String,Object> map = new HashMap<>(16);
		UmsMember member = null;
		if(memberId != null){
			member = memberService.getMemberById(memberId);
		}else if(StringUtils.isNotBlank(uid)){
			member = memberService.getMemberByParams(uid, MemberTypes.UID);
		}else if(StringUtils.isNotBlank(phone)){
			member = memberService.getMemberByParams(phone, MemberTypes.PHONE);
		}else if(StringUtils.isNotBlank(inviteCode)){
			member = memberService.getMemberByParams(inviteCode, MemberTypes.INVITE_CODE);
		}
		if(member == null){
			return CommonResult.success(map);
		}
		if(StringUtils.isNotBlank(member.getRegisterIpAddress())){
			member.setRegisterIp(member.getRegisterIp()+" "+member.getRegisterIpAddress());
		}
		Map<String,Object> memberMap = BeanUtil.beanToMap(member);
		UmsMemberLoginLog loginLog = memberService.getLastLoginLog(member.getId());
		if(loginLog != null){
			memberMap.put("lastLoginTime", loginLog.getAddTime());
			memberMap.put("lastLoginIp", loginLog.getIp() + " " + (StringUtils.isNotBlank(loginLog.getAddress()) ? loginLog.getAddress() : ""));
		}
		map.put("member", memberMap);

		UmsMemberAccount memberAccount = accountService.getMemberAccount(member.getId());
		Map<String,Object> accountMap = BeanUtil.beanToMap(memberAccount);
		//今日总收入, 总收入, 总支出, 总充值, 总提现
		Map<String,Object> countMap = memberAccountLogService.count(member.getId());
		accountMap.putAll(countMap);
		map.put("memberAccount", accountMap);
		map.put("realname", memberRealnameService.getMemberRealnameByMemberId(member.getId()));
		return CommonResult.success(map);
	}

	@OperationLog
	@ApiOperation(value = "修改会员信息")
	@ResponseBody
	@RequestMapping(value="/member/editSubmit", method=RequestMethod.POST)
	public CommonResult<String> editSubmit(UmsMember member) throws ServiceException {
		memberService.updateMember(member);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "修改会员资金")
	@ResponseBody
	@RequestMapping(value="/member/updateAccount", method=RequestMethod.POST)
	public CommonResult<UmsMemberAccount> updateAccount(Integer memberId, BigDecimal amount, BigDecimal mission, BigDecimal spmission, BigDecimal point, BigDecimal shangbaoAmount, String remark) throws ServiceException {
		UmsMember member = memberService.getMemberById(memberId);
		String orderNo = "A" + StringUtils.genOrderNo(memberId);
		if(StringUtils.isBlank(remark)){
			return CommonResult.failed("请务必填写备注");
		}
		String msg = "管理员["+getSessionAdmin().getName()+"]手动操作资金";
		if(StringUtils.isNotBlank(remark)){
			msg = msg + "," + remark;
		}
		String ip = this.getRequestIp();
		if(amount != null && amount.compareTo(BigDecimal.ZERO) != 0){
			if(amount.compareTo(BigDecimal.ZERO) > 0){
				memberAccountLogService.inAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_ADMIN_IN, orderNo, amount.abs(), msg, ip);
			}else{
				memberAccountLogService.outAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_ADMIN_OUT, orderNo, amount.abs(), msg, ip);
			}
		}
		if(mission != null && mission.compareTo(BigDecimal.ZERO) != 0){
			if(mission.compareTo(BigDecimal.ZERO) > 0){
				publishPointLogService.inAccount(member, AccountEnums.MemberMissionTypes.ACCOUNT_ADMIN_IN, orderNo, mission.abs(), msg, ip);
			}else{
				publishPointLogService.outAccount(member, AccountEnums.MemberMissionTypes.ACCOUNT_ADMIN_OUT, orderNo, mission.abs(), msg, ip);
			}
		}
		if(spmission != null && spmission.compareTo(BigDecimal.ZERO) != 0){
			if(spmission.compareTo(BigDecimal.ZERO) > 0){
				spmissionLogService.inAccount(member, null, AccountEnums.MemberSpmissionTypes.ACCOUNT_ADMIN_IN, orderNo, spmission.abs(), msg, ip);
			}else{
				spmissionLogService.outAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_ADMIN_OUT, orderNo, spmission.abs(), msg, ip);
			}
		}

		if(point != null && point.compareTo(BigDecimal.ZERO) != 0){
			if(point.compareTo(BigDecimal.ZERO) > 0){
				memberPointLogService.inAccount(member, AccountEnums.MemberPointTypes.ACCOUNT_ADMIN_IN, orderNo, point.abs(), msg, ip);
			}else{
				memberPointLogService.outAccount(member, AccountEnums.MemberPointTypes.ACCOUNT_ADMIN_OUT, orderNo, point.abs(), msg, ip);
			}
		}
		return CommonResult.success(accountService.getMemberAccount(memberId));
	}

	@OperationLog
	@ApiOperation(value = "修改邀请人")
	@ResponseBody
	@RequestMapping(value="/member/updateInvite", method=RequestMethod.POST)
	public CommonResult<String> updateInvite(Integer memberId, String inviteUid) throws ServiceException {
		memberService.updateInvite(memberId, inviteUid);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "本金互转")
	@ResponseBody
	@RequestMapping(value="/member/transferAmount", method=RequestMethod.POST)
	public CommonResult<String> transferAmount(Integer memberId, String uid, BigDecimal amount) throws ServiceException {
		super.validateRepeat("do-tranferAmount-admin-time-between-" + memberId, "请勿重复操作,5秒后再试!", 5000L);
		memberAccountLogService.transferAmount(memberId, uid, amount);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "私信")
	@ResponseBody
	@RequestMapping(value="/member/sendMsg", method=RequestMethod.POST)
	public CommonResult<String> sendMsg(Integer memberId, String title, String content) throws ServiceException {
		UmsMember member = memberService.getMemberById(memberId);
		memberNewsService.addMemberNews(member, MemberNewsEnums.MemberNewsTypes.NEWS_USER, title, null, content);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "限制提现")
	@ResponseBody
	@RequestMapping(value="/member/limitWithdraw", method=RequestMethod.POST)
	public CommonResult<String> limitWithdraw(Integer memberId, Integer status) throws ServiceException {
		memberService.limitWithdraw(memberId, status);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "登录设备列表")
	@ResponseBody
	@RequestMapping(value="/member/getImeIdList", method=RequestMethod.GET)
	public CommonResult<List<UmsMemberImeid>> getImeIdList(Integer memberId) {
		List<UmsMemberImeid> list = memberImeIdService.getAllImeid(memberId);
		return CommonResult.success(list);
	}

	@OperationLog
	@ApiOperation(value = "删除设备")
	@ResponseBody
	@RequestMapping(value="/member/deleteImeId", method=RequestMethod.POST)
	public CommonResult<String> deleteImeId(Integer imeId) {
		memberImeIdService.deleteImeid(imeId);
		return CommonResult.success(null);
	}

	@OperationLog
	@ApiOperation(value = "注册")
	@ResponseBody
	@RequestMapping(value={"/member/register"}, method=RequestMethod.POST)
	public CommonResult<Map<String, Object>> regSubmit(Integer type, String username, String password, String inviteCode) throws Exception {
		ValidateUtil.validateNull(new Object[]{type, username, password}, "用户类型不能为空", "用户名不能为空", "密码不能为空");

		UmsMember member = new UmsMember();
		member.setType(type);
		member.setCode(username.trim());
		member.setNickname(username.trim());
		member.setPhone(username.trim());
		member.setPassword(password);
		if(StringUtils.isNotBlank(inviteCode)){
			if(CommonConstant.FIRST_INVITE_CODE.equals(inviteCode)){
				member.setTjrId(0);
			}else{
				SysInviteCode config = sysInviteCodeService.getConfigByInviteCode(inviteCode);
				if(config == null){
					return CommonResult.failed("邀请码错误");
				}
				if(config.getType() == CommonConstant.INVITE_CODE_MEMBER){
					UmsMember tjr = memberService.getMemberById(config.getUserId());
					if(tjr.getInviteStatus() != CommonConstant.YES_INT){
						return CommonResult.failed("邀请码错误");
					}
					member.setTjrId(tjr.getId());
					member.setTjrUid(tjr.getUid());
				}
			}
		}
		member.setRegisterIp(this.getRequestIp());
		member.setRegisterIpAddress(IpSearch.getInstance().getIpAddress(member.getRegisterIp()));
		member.setRegisterUrl(this.getRequest().getHeader("Origin"));
		if(StringUtils.isNotBlank(member.getRegisterUrl())){
			member.setRegisterUrl(member.getRegisterUrl()
					.replaceAll("http://", "")
					.replaceAll("https://", "")
					.replaceAll("/", ""));
		}
		memberService.register(member);
		return CommonResult.success(null);
	}

}