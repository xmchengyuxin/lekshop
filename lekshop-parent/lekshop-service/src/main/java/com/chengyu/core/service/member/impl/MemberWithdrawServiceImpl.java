package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import com.alipay.api.AlipayApiException;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.MemberNewsEnums;
import com.chengyu.core.domain.enums.MemberRemindEnums.AdminRemindTypes;
import com.chengyu.core.domain.enums.MemberRemindEnums.MemberRemindTypes;
import com.chengyu.core.domain.form.BankForm;
import com.chengyu.core.domain.form.WithdrawSearchForm;
import com.chengyu.core.domain.result.WithdrawCountResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.CustomMemberWithdrawMapper;
import com.chengyu.core.mapper.UmsMemberWithdrawMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigWithdrawService;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.funds.MemberSpmissionLogService;
import com.chengyu.core.service.member.*;
import com.chengyu.core.service.pay.alipay.AliPay;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.utils.NumberUtils;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @title  用户提现
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberWithdrawServiceImpl implements MemberWithdrawService {
	
	@Autowired
	private UmsMemberWithdrawMapper withdrawMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CustomMemberWithdrawMapper customWithdrawMapper;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private MemberSpmissionLogService spmissionLogService;
	@Autowired
	private MemberNewsService newsService;
	@Autowired
	private MemberAccountService accountService;
	@Autowired
	private ConfigWithdrawService configWithdrawService;
	@Autowired
	private MemberPunishmentService memberPunishmentService;
	@Autowired
	private WeixinNoticeService weixinNoticeService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private AliPay aliPay;
	@Autowired
	private MemberRemindService memberRemindService;

	@Override
	public List<UmsMemberWithdraw> getWithdrawLog(WithdrawSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		
		UmsMemberWithdrawExample example = new UmsMemberWithdrawExample();
		example.setOrderByClause("add_time desc");
		UmsMemberWithdrawExample.Criteria criteria = example.createCriteria();
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getStatus())){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getMethod() != null){
			criteria.andMethodEqualTo(form.getMethod());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			if(CollectionUtil.isNotEmpty(form.getStatusList())){
				criteria.andUpdTimeBetween(form.getDateFrom(), form.getDateTo());
			}else{
				criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
			}
		}
		if(!StringUtils.isEmpty(form.getMemberName())){
			criteria.andMemberNameLike("%"+form.getMemberName()+"%");
		}
		if(!StringUtils.isEmpty(form.getBankAccount())){
			criteria.andBankAccountLike("%"+form.getBankAccount()+"%");
		}
		if(!StringUtils.isEmpty(form.getOrderNo())){
			criteria.andOrderNoLike("%"+form.getOrderNo()+"%");
		}
		if(CollectionUtil.isNotEmpty(form.getStatusList())){
			criteria.andStatusIn(form.getStatusList());
		}
		if(CollectionUtil.isNotEmpty(form.getMemberIdList())){
			criteria.andMemberIdIn(form.getMemberIdList());
		}
		return withdrawMapper.selectByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyWithdraw(Integer id, Integer status, String verifyName, String remark) throws ServiceException {
		UmsMemberWithdraw withdraw = withdrawMapper.selectByPrimaryKey(id);
		if(!CommonConstant.DOING.equals(withdraw.getStatus())){
			throw new ServiceException("提现编号"+withdraw.getOrderNo()+"的申请状态不正确,无法进行审核");
		}
		UmsMemberWithdraw updateWithdraw = new UmsMemberWithdraw();
		updateWithdraw.setId(id);
		if(status == CommonConstant.SUS_INT){
			updateWithdraw.setStatus(CommonConstant.SUS);
		}else if(status == CommonConstant.FAIL_INT){
			updateWithdraw.setStatus(CommonConstant.FAIL);
		}
		updateWithdraw.setRemark(remark);
		updateWithdraw.setUpdBy(verifyName);
		updateWithdraw.setUpdTime(DateUtil.date());
		withdrawMapper.updateByPrimaryKeySelective(updateWithdraw);

		UmsMember member = memberService.getMemberById(withdraw.getMemberId());
		if(status == CommonConstant.SUS_INT){
			if(withdraw.getMethod().equals(AccountEnums.AccountMethod.BALANCE.getValue())){
				//先解冻
				memberAccountLogService.unfreezeAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_WITHDRAW_UNFREEZE, withdraw.getOrderNo(), withdraw.getAmount(), "提现成功,释放金额即将扣款", null);
				//再扣款
				memberAccountLogService.outAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_WITHDRAW_REDUCE, withdraw.getOrderNo(), withdraw.getAmount(), "提现成功,扣款金额", null);
			}else if(withdraw.getMethod().equals(AccountEnums.AccountMethod.MISSION.getValue())){
				//先解冻
				spmissionLogService.unfreezeAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_WITHDRAW_UNFREEZE, withdraw.getOrderNo(), withdraw.getAmount(), "提现成功,释放金额即将扣款", null);
				//再扣款
				spmissionLogService.outAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_WITHDRAW_REDUCE, withdraw.getOrderNo(), withdraw.getAmount(), "提现成功,扣款金额", null);
			}
			try {
				newsService.addMemberNews(member, MemberNewsEnums.MemberNewsTypes.NEWS_WITHDRAW, "提现成功", null, "您提现的"+ NumberUtils.format2(withdraw.getAmount())+"元已到账,请注意查收!");
				weixinNoticeService.withdrawSusNotice(withdraw);
				memberRemindService.addMemberRemind(member, MemberRemindTypes.WITHDRAW_SUS, "您提现的"+ NumberUtils.format2(withdraw.getAmount())+"元已到账,请注意查收!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			if(withdraw.getMethod().equals(AccountEnums.AccountMethod.BALANCE.getValue())){
				//进行解冻还款
				memberAccountLogService.unfreezeAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_WITHDRAW_UNFREEZE, withdraw.getOrderNo(), withdraw.getAmount(), "提现失败,返还金额", null);
			}else if(withdraw.getMethod().equals(AccountEnums.AccountMethod.MISSION.getValue())){
				//进行解冻还款
				spmissionLogService.unfreezeAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_WITHDRAW_UNFREEZE, withdraw.getOrderNo(), withdraw.getAmount(), "提现失败,返还金额", null);
			}
			try {
				newsService.addMemberNews(member, MemberNewsEnums.MemberNewsTypes.NEWS_WITHDRAW, "提现失败", null, "您申请提现的"+ NumberUtils.format2(withdraw.getAmount())+"元提现失败,失败原因为"+remark);
				weixinNoticeService.withdrawFailNotice(withdraw);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Integer withdraw(UmsMember member, Integer type, Integer method, BigDecimal amount, BankForm bankForm) throws ServiceException {
		//网站维护校验
		configService.validateWeihuOnlyLogin();
		if(member.getWithdrawLimit() != null && member.getWithdrawLimit() == CommonConstant.YES_INT){
			throw new ServiceException("您当前已被限制提现, 详询客服");
		}
		ConfigWithdraw config = configWithdrawService.getConfigWithdrawByGroupId(member.getGroupId());
		BigDecimal fee = BigDecimal.ZERO;
		if(config != null){
			//提现基础校验
			this.validateWithdraw(member, amount, config, method);

			//计算手续费
			if(type.equals(AccountEnums.WithdrawTypes.ZFB.getValue())){
				fee = NumberUtil.mul(amount, NumberUtil.div(config.getZfbRate(), 100));
				if(fee.compareTo(config.getZfbMinFee()) < 0){
					fee = config.getZfbMinFee();
				}
			}else if(type.equals(AccountEnums.WithdrawTypes.WEIXIN.getValue())){
				fee = NumberUtil.mul(amount, NumberUtil.div(config.getWxRate(), 100));
				if(fee.compareTo(config.getWxMinFee()) < 0){
					fee = config.getWxMinFee();
				}
			}else if(type.equals(AccountEnums.WithdrawTypes.BANK.getValue())){
				fee = NumberUtil.mul(amount, NumberUtil.div(config.getBankRate(), 100));
				if(fee.compareTo(config.getBankMinFee()) < 0){
					fee = config.getBankMinFee();
				}
			}
		}

		String orderNo = StringUtils.genOrderNo(member.getId());
		UmsMemberAccount account = accountService.getMemberAccount(member.getId());
		if(method.equals(AccountEnums.AccountMethod.BALANCE.getValue())){
			if(account.getAmount().compareTo(amount) < 0){
				throw new ServiceException("可用账户余额不足,无法提现");
			}
			//冻结资金
			memberAccountLogService.freezeAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_WITHDRAW_FREEZE, orderNo, amount, "申请提现,等待打款", null);
		}else if(method.equals(AccountEnums.AccountMethod.MISSION.getValue())){
			if(account.getSpmission().compareTo(amount) < 0){
				throw new ServiceException("可用佣金余额不足,无法提现");
			}
			spmissionLogService.freezeAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_WITHDRAW_FREEZE, orderNo, amount, "申请提现,等待打款", null);
		}

		UmsMemberWithdraw withdraw = new UmsMemberWithdraw();
		withdraw.setMemberId(member.getId());
		withdraw.setMemberName(member.getCode());
		withdraw.setType(type);
		withdraw.setMethod(method);
		withdraw.setRealname(bankForm.getAccountName());
		withdraw.setOrderNo(orderNo);
		withdraw.setBank(bankForm.getBank());
		withdraw.setBankName(bankForm.getBank());
		withdraw.setBankAccount(bankForm.getBankAccount());
		if(StringUtils.isNotBlank(bankForm.getQrCode())){
			withdraw.setBankAccount(bankForm.getQrCode());
		}
		withdraw.setTotalAmount(amount);
		withdraw.setAmount(amount.subtract(fee));
		withdraw.setFee(fee);
		withdraw.setStatus(CommonConstant.NO);
		withdraw.setAddTime(DateUtil.date());
		withdrawMapper.insertSelective(withdraw);

		memberRemindService.addAdminRemind(AdminRemindTypes.WAIT_VERIFY_WITHDRAW, "「"+member.getCode()+"」进行了提现, 请尽快审批");
		return withdraw.getId();
	}

	private void validateWithdraw(UmsMember member, BigDecimal amount, ConfigWithdraw config, Integer method) throws ServiceException {
		//判断是否开启提现
		if(config.getWithdrawStatus() != CommonConstant.YES_INT){
			throw  new ServiceException("当前会员组暂未开启提现功能");
		}
		//判断提现账户限制
		if(config.getWithdrawType().equals(AccountEnums.AccountMethod.BALANCE.getValue())
				&& method.equals(AccountEnums.AccountMethod.MISSION.getValue())){
			throw  new ServiceException("当前会员组暂不支持佣金提现");
		}
		if(config.getWithdrawType().equals(AccountEnums.AccountMethod.MISSION.getValue())
				&& method.equals(AccountEnums.AccountMethod.BALANCE.getValue())){
			throw  new ServiceException("当前会员组暂不支持余额提现");
		}
		//是否实名认证
		if(config.getNeedRealname() == CommonConstant.YES_INT && member.getRealnameStatus() != CommonConstant.YES_INT){
			throw  new ServiceException("您需实名认证后才允许提现");
		}
		//是否银行认证
		if(config.getNeedBank() == CommonConstant.YES_INT && member.getBankStatus() != CommonConstant.YES_INT){
			throw  new ServiceException("您需银行认证后才允许提现");
		}
		//判断提现最低金额
		if(method.equals(AccountEnums.AccountMethod.BALANCE.getValue())){
			if(config.getMinBalanceWithdrawAmount().compareTo(amount) > 0){
				throw new ServiceException("最低提现金额为"+NumberUtils.format2(config.getMinBalanceWithdrawAmount()));
			}
			if(config.getMaxBalanceWithdrawAmount().compareTo(amount) < 0){
				throw new ServiceException("最高提现金额为"+NumberUtils.format2(config.getMaxBalanceWithdrawAmount()));
			}
		}else if(method.equals(AccountEnums.AccountMethod.MISSION.getValue())){
			if(config.getMinMissionWithdrawAmount().compareTo(amount) > 0){
				throw new ServiceException("最低提现金额为"+NumberUtils.format2(config.getMinMissionWithdrawAmount()));
			}
			if(config.getMaxMissionWithdrawAmount().compareTo(amount) < 0){
				throw new ServiceException("最高提现金额为"+NumberUtils.format2(config.getMaxMissionWithdrawAmount()));
			}
		}
		//判断每天允许提现次数
		Date now = DateUtil.date();
		if(this.countWithdrawNums(member.getId(), DateUtil.beginOfDay(now), DateUtil.endOfDay(now)) >= config.getWithdrawDayNum()){
			throw new ServiceException("每天只能提现"+config.getWithdrawDayNum()+"次");
		}
		//是否有被处罚
		if(memberPunishmentService.isPunishmenting(member.getId())){
			throw new ServiceException("您当前正在处罚当中, 请等待处罚期后再进行提现");
		}
		//判断是否有正在审核的提现
		UmsMemberWithdrawExample example = new UmsMemberWithdrawExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andStatusEqualTo(CommonConstant.WAIT);
		long count = withdrawMapper.countByExample(example);
		if(count > 0){
			throw new ServiceException("您当前有未到账的提现, 请等待到账后再进行提现");
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String batchVerify(String ids, Integer status, String code, String remark) {
		StringBuilder message = new StringBuilder();
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			try {
				this.verifyWithdraw(Integer.valueOf(id), status, code, remark);
			} catch (ServiceException e) {
				message.append("|").append(e.getMessage());
			}
		}
		String msg = message.toString();
		if(StringUtils.isNotBlank(msg)){
			msg = msg.substring(1);
		}
		return msg;
	}

	@Override
	public WithdrawCountResult countWithdraw(WithdrawSearchForm form) {
		return customWithdrawMapper.countWithdraw(form);
	}

	@Override
	public long countWithdrawNums(Integer memberId, Date beginTime, Date endTime) {
		UmsMemberWithdrawExample example = new UmsMemberWithdrawExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andAddTimeBetween(beginTime, endTime)
		.andStatusIn(Arrays.asList(CommonConstant.WAIT, CommonConstant.SUS));
		return withdrawMapper.countByExample(example);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String batchSetDoing(String ids) {
		int susNum = 0, errorNum = 0;
		for(String id : ids.split(CommonConstant.DH_REGEX)){
			UmsMemberWithdraw withdraw = withdrawMapper.selectByPrimaryKey(Integer.valueOf(id));
			if(!CommonConstant.WAIT.equals(withdraw.getStatus())){
				errorNum++;
			}else{
				susNum++;
				UmsMemberWithdraw updateWithdraw = new UmsMemberWithdraw();
				updateWithdraw.setId(Integer.valueOf(id));
				updateWithdraw.setStatus(CommonConstant.DOING);
				updateWithdraw.setUpdTime(DateUtil.date());
				withdrawMapper.updateByPrimaryKeySelective(updateWithdraw);
			}
		}
		return "批量处理成功"+susNum+"条, 失败"+errorNum+"条";
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void withdrawByAdmin(UmsMember member, Integer type, Integer method, BigDecimal amount, BankForm bankForm) throws ServiceException {
		//网站维护校验
		ConfigWithdraw config = configWithdrawService.getConfigWithdrawByGroupId(member.getGroupId());
		BigDecimal fee = BigDecimal.ZERO;
		if(config != null){
			//计算手续费
			if(type.equals(AccountEnums.WithdrawTypes.ZFB.getValue())){
				fee = NumberUtil.mul(amount, NumberUtil.div(config.getZfbRate(), 100));
				if(fee.compareTo(config.getZfbMinFee()) < 0){
					fee = config.getZfbMinFee();
				}
			}else if(type.equals(AccountEnums.WithdrawTypes.WEIXIN.getValue())){
				fee = NumberUtil.mul(amount, NumberUtil.div(config.getWxRate(), 100));
				if(fee.compareTo(config.getWxMinFee()) < 0){
					fee = config.getWxMinFee();
				}
			}else if(type.equals(AccountEnums.WithdrawTypes.BANK.getValue())){
				fee = NumberUtil.mul(amount, NumberUtil.div(config.getBankRate(), 100));
				if(fee.compareTo(config.getBankMinFee()) < 0){
					fee = config.getBankMinFee();
				}
			}
		}

		String orderNo = StringUtils.genOrderNo(member.getId());
		UmsMemberAccount account = accountService.getMemberAccount(member.getId());
		if(method.equals(AccountEnums.AccountMethod.BALANCE.getValue())){
			if(account.getAmount().compareTo(amount) < 0){
				throw new ServiceException("可用账户余额不足,无法提现");
			}
			//冻结资金
			memberAccountLogService.freezeAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_WITHDRAW_FREEZE, orderNo, amount, "申请提现,等待打款", null);
		}else if(method.equals(AccountEnums.AccountMethod.MISSION.getValue())){
			if(account.getSpmission().compareTo(amount) < 0){
				throw new ServiceException("可用佣金余额不足,无法提现");
			}
			spmissionLogService.freezeAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_WITHDRAW_FREEZE, orderNo, amount, "申请提现,等待打款", null);
		}

		UmsMemberWithdraw withdraw = new UmsMemberWithdraw();
		withdraw.setMemberId(member.getId());
		withdraw.setMemberName(member.getCode());
		withdraw.setType(type);
		withdraw.setMethod(method);
		withdraw.setRealname(bankForm.getAccountName());
		withdraw.setOrderNo(orderNo);
		withdraw.setBank(bankForm.getBank());
		withdraw.setBankName(bankForm.getBank());
		withdraw.setBankAccount(bankForm.getBankAccount());
		if(StringUtils.isNotBlank(bankForm.getQrCode())){
			withdraw.setBankAccount(bankForm.getQrCode());
		}
		withdraw.setTotalAmount(amount);
		withdraw.setAmount(amount.subtract(fee));
		withdraw.setFee(fee);
		withdraw.setStatus(CommonConstant.NO);
		withdraw.setAddTime(DateUtil.date());
		withdrawMapper.insertSelective(withdraw);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void rejectAllWithdraw(Integer memberId) throws ServiceException {
		UmsMemberWithdrawExample example = new UmsMemberWithdrawExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusIn(Arrays.asList(CommonConstant.WAIT, CommonConstant.DOING));
		List<UmsMemberWithdraw> withdrawList = withdrawMapper.selectByExample(example);

		UmsMember member = memberService.getMemberById(memberId);
		for(UmsMemberWithdraw withdraw : withdrawList){
			UmsMemberWithdraw updateWithdraw = new UmsMemberWithdraw();
			updateWithdraw.setId(withdraw.getId());
			updateWithdraw.setStatus(CommonConstant.FAIL);
			updateWithdraw.setRemark("限制提现");
			updateWithdraw.setUpdTime(DateUtil.date());
			withdrawMapper.updateByPrimaryKeySelective(updateWithdraw);

			if(withdraw.getMethod().equals(AccountEnums.AccountMethod.BALANCE.getValue())){
				//进行解冻还款
				memberAccountLogService.unfreezeAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_WITHDRAW_UNFREEZE, withdraw.getOrderNo(), withdraw.getAmount(), "提现失败,返还金额", null);
			}else if(withdraw.getMethod().equals(AccountEnums.AccountMethod.MISSION.getValue())){
				//进行解冻还款
				spmissionLogService.unfreezeAccount(member, AccountEnums.MemberSpmissionTypes.ACCOUNT_WITHDRAW_UNFREEZE, withdraw.getOrderNo(), withdraw.getAmount(), "提现失败,返还金额", null);
			}
			try {
				newsService.addMemberNews(member, MemberNewsEnums.MemberNewsTypes.NEWS_WITHDRAW, "提现失败", null, "您申请提现的"+ NumberUtils.format2(withdraw.getAmount())+"元提现失败,失败原因为限制提现");
				weixinNoticeService.withdrawFailNotice(withdraw);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String batchVerifySusByZfb(String ids, String operationName) throws ServiceException {
		int susNum = 0, errorNum = 0;
		UmsMemberWithdrawExample example = new UmsMemberWithdrawExample();
		List<String> idStrList = Arrays.asList(ids.split(CommonConstant.DH_REGEX));
		List<Integer> idList = Convert.convert(new TypeReference<List<Integer>>() {}, idStrList);
		example.createCriteria().andIdIn(idList).andStatusNotEqualTo(CommonConstant.DOING);
		long count = withdrawMapper.countByExample(example);
		if(count > 0){
			throw new ServiceException("请先将提现置为处理中, 才能进行打款");
		}

		for(Integer id : idList){
			UmsMemberWithdraw withdraw = withdrawMapper.selectByPrimaryKey(id);
			if(withdraw.getType() != 2){
				errorNum++;
				continue;
			}
			String message;
			try {
				message = aliPay.transferToUser(withdraw.getOrderNo(), withdraw.getAmount(), withdraw.getBankAccount(), withdraw.getRealname());
			} catch (AlipayApiException e) {
				errorNum++;
				continue;
			}
			UmsMemberWithdraw updateWithdraw = new UmsMemberWithdraw();
			updateWithdraw.setId(id);
			if(StringUtils.isNotBlank(message)){
				errorNum++;
				updateWithdraw.setRemark(message);
			}else{
				susNum++;
				updateWithdraw.setRemark("转账成功");
				updateWithdraw.setStatus(CommonConstant.SUS);
			}
			updateWithdraw.setUpdBy(operationName);
			updateWithdraw.setUpdTime(DateUtil.date());
			withdrawMapper.updateByPrimaryKeySelective(updateWithdraw);
		}
		return "批量转账成功"+susNum+"条, 失败"+errorNum+"条";
	}

	@Override
	public String queryZfbBalance() throws Exception {
		return aliPay.queryBalance();
	}

}