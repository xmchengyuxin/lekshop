package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.enums.MemberRemindEnums.AdminRemindTypes;
import com.chengyu.core.domain.form.RechargeSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomMemberRechargeMapper;
import com.chengyu.core.mapper.UmsMemberRechargeMapper;
import com.chengyu.core.model.ConfigWithdraw;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.model.UmsMemberRecharge;
import com.chengyu.core.model.UmsMemberRechargeExample;
import com.chengyu.core.service.config.ConfigAccountService;
import com.chengyu.core.service.config.ConfigWithdrawService;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.funds.MemberMissionLogService;
import com.chengyu.core.service.member.MemberNewsService;
import com.chengyu.core.service.member.MemberRechargeService;
import com.chengyu.core.service.member.MemberRemindService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.utils.NumberUtils;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title  充值
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberRechargeServiceImpl implements MemberRechargeService {
	
	@Autowired
	private UmsMemberRechargeMapper rechargeMapper;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private MemberMissionLogService missionLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ConfigWithdrawService configWithdrawService;
	@Autowired
	private MemberNewsService newsService;
	@Autowired
	private CustomMemberRechargeMapper customMemberRechargeMapper;
	@Autowired
	private ConfigAccountService configAccountService;
	@Autowired
	private MemberRemindService memberRemindService;

	@Override
	public List<UmsMemberRecharge> getRechargeLog(RechargeSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsMemberRechargeExample example = new UmsMemberRechargeExample();
		example.setOrderByClause("add_time desc");
		UmsMemberRechargeExample.Criteria criteria = example.createCriteria();
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			if(CollectionUtil.isNotEmpty(form.getStatusList())){
				criteria.andVerifyTimeBetween(form.getDateFrom(), form.getDateTo());
			}else{
				criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
			}
		}
		if(!StringUtils.isEmpty(form.getMemberName())){
			criteria.andMemberNameLike("%"+form.getMemberName()+"%");
		}
		if(!StringUtils.isEmpty(form.getOrderNo())){
			criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
		}
		if(CollectionUtil.isNotEmpty(form.getStatusList())){
			criteria.andStatusIn(form.getStatusList());
		}
		return rechargeMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String recharge(UmsMember member, String paymethod, BigDecimal amount, String remitBank, String remitRealname, String remitAccount, String img) {
		UmsMemberRecharge recharge = new UmsMemberRecharge();
		recharge.setOrderNo("R"+StringUtils.genOrderNo(member.getId()));
		recharge.setMemberId(member.getId());
		recharge.setMemberName(member.getCode());
		recharge.setRealname(remitRealname);
		recharge.setPaymethod(paymethod);
		recharge.setImg(img);
		recharge.setRemitAccount(remitAccount);
		recharge.setRemitBank(remitBank);
		recharge.setRemitRealname(remitRealname);
		recharge.setAmount(amount);
		recharge.setRealAmount(amount);
		recharge.setAddTime(DateUtil.date());
		recharge.setSaveTime(recharge.getAddTime());
		recharge.setStatus(CommonConstant.WAIT);
		rechargeMapper.insertSelective(recharge);

		memberRemindService.addAdminRemind(AdminRemindTypes.WAIT_VERIFY_RECHARGE, "「"+member.getCode()+"」进行了充值, 请尽快审批");
		return recharge.getOrderNo();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyRecharge(Integer id, String verifyName, Integer status, String remark) throws ServiceException {
		UmsMemberRecharge recharge = rechargeMapper.selectByPrimaryKey(id);
		if(recharge == null || !CommonConstant.WAIT.equals(recharge.getStatus())){
			throw new ServiceException("待审核状态才能进行审核");
		}
		UmsMemberRecharge updateRecharge = new UmsMemberRecharge();
		updateRecharge.setId(id);
		updateRecharge.setRemark(remark);
		updateRecharge.setVerifyTime(DateUtil.date());
		if(status == CommonConstant.SUS_INT){
			//审核成功
			updateRecharge.setStatus(CommonConstant.SUS);
		}else if(status == CommonConstant.FAIL_INT){
			//审核失败
			updateRecharge.setStatus(CommonConstant.FAIL);
		}
		rechargeMapper.updateByPrimaryKeySelective(updateRecharge);
		
		if(status == CommonConstant.SUS_INT){
			UmsMember member = memberService.getMemberById(recharge.getMemberId());
			memberAccountLogService.inAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_RECHARGE, recharge.getOrderNo(), recharge.getAmount(), "充值成功", null);

			newsService.addMemberNews(member, MemberNewsEnums.MemberNewsTypes.NEWS_RECHARGE, "账户余额充值成功", null,
					"您好, 已成功为您充值"+ NumberUtils.format2(recharge.getRealAmount())+"元");
		}

		try {
			if(recharge.getConfigAccountId() != null){
				BigDecimal amount = status == CommonConstant.SUS_INT ? recharge.getAmount() : BigDecimal.ZERO.subtract(recharge.getAmount());
				//判断是否是今日充值
				boolean isUpdateToday = DateUtil.isSameDay(recharge.getAddTime(), DateUtil.date());
				configAccountService.updateAmount(recharge.getConfigAccountId(), amount, isUpdateToday);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void cancel(String orderNo) throws ServiceException {
		UmsMemberRechargeExample example = new UmsMemberRechargeExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<UmsMemberRecharge> list = rechargeMapper.selectByExample(example);
		
		UmsMemberRecharge recharge = list.get(0);
		if(recharge == null) {
			throw new ServiceException("该笔充值记录不存在,无法取消!");
		}
		if(!CommonConstant.WAIT.equals(recharge.getStatus())) {
			throw new ServiceException("该笔充值记录已被审核,无法取消!");
		}
		
		UmsMemberRecharge updateRecharge = new UmsMemberRecharge();
		updateRecharge.setId(recharge.getId());
		updateRecharge.setStatus("3");
		updateRecharge.setRemark("用户取消充值!");
		rechargeMapper.updateByPrimaryKeySelective(updateRecharge);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void pointToBalance(UmsMember member, BigDecimal publishPoint) throws ServiceException {
		//校验是否开启此功能
		ConfigWithdraw config = configWithdrawService.getConfigWithdrawByGroupId(member.getGroupId());
		if(config == null || config.getMissionToBalance() == CommonConstant.NO_INT){
			throw new ServiceException("您所在的会员组暂不支持此功能");
		}

		String orderNo = "D"+StringUtils.genOrderNo(member.getId());
		missionLogService.outAccount(member, AccountEnums.MemberMissionTypes.ACCOUNT_TRANSFER_BALANCE, orderNo, publishPoint, "佣金兑换余额", null);
		memberAccountLogService.inAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_TRANSFER_BALANCE, orderNo, publishPoint, "佣金兑换余额", null);
	}

	@Override
	public List<UmsMemberRecharge> getRechargeTemplateList(Integer memberId) {
		return customMemberRechargeMapper.getRechargeTemplateList(memberId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String rechargeOnline(UmsMember member, String payMethod, BigDecimal amount, String returnUrl) throws ServiceException {
		UmsMemberRecharge recharge = new UmsMemberRecharge();
		recharge.setOrderNo("R"+StringUtils.genOrderNo(member.getId()));
		recharge.setMemberId(member.getId());
		recharge.setMemberName(member.getCode());
		recharge.setPaymethod("5");
		recharge.setAmount(amount);
		recharge.setRealAmount(amount);
		recharge.setAddTime(DateUtil.date());
		recharge.setSaveTime(recharge.getAddTime());
		recharge.setStatus(CommonConstant.WAIT);
		rechargeMapper.insertSelective(recharge);

		return null;
	}

	@Override
	public UmsMemberRecharge getRechargeLogByOrderNo(String orderNo) {
		UmsMemberRechargeExample example = new UmsMemberRechargeExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<UmsMemberRecharge> list = rechargeMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	public void rechargSusByCallback(UmsMemberRecharge recharge) throws ServiceException {
		UmsMemberRecharge updateRecharge = new UmsMemberRecharge();
		updateRecharge.setId(recharge.getId());
		updateRecharge.setVerifyTime(DateUtil.date());
		updateRecharge.setStatus(CommonConstant.SUS);
		rechargeMapper.updateByPrimaryKeySelective(updateRecharge);

		UmsMember member = memberService.getMemberById(recharge.getMemberId());
		memberAccountLogService.inAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_RECHARGE, recharge.getOrderNo(), recharge.getAmount(), "充值成功", null);

		newsService.addMemberNews(member, MemberNewsEnums.MemberNewsTypes.NEWS_RECHARGE, "账户余额充值成功", null,
					"您好, 已成功为您充值"+ NumberUtils.format2(recharge.getRealAmount())+"元");
	}

}