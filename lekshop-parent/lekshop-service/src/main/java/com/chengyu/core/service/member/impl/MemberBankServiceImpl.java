package com.chengyu.core.service.member.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.MemberRemindEnums.AdminRemindTypes;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberBankMapper;
import com.chengyu.core.mapper.UmsMemberRealnameMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigRealnameService;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.member.MemberBankService;
import com.chengyu.core.service.member.MemberRealnameService;
import com.chengyu.core.service.member.MemberRemindService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.system.ThirdConfigService;
import com.chengyu.core.util.third.manager.ThirdManager;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @title  会员银行卡
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class MemberBankServiceImpl implements MemberBankService {
	
	@Autowired
	private UmsMemberBankMapper memberBankMapper;
	@Autowired
	private ConfigRealnameService configRealnameService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRealnameService memberRealnameService;
	@Autowired
	private UmsMemberRealnameMapper memberRealnameMapper;
	@Autowired
	private ThirdManager thirdManager;
	@Autowired
	private ThirdConfigService thirdConfigService;
	@Autowired
	private MemberRemindService memberRemindService;

	@Override
	public List<UmsMemberBank> getMemberBankList(BaseSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberBankExample example = new UmsMemberBankExample();
		example.setOrderByClause("add_time desc");
		UmsMemberBankExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(form.getKeyword())){
			UmsMemberBankExample.Criteria criteria1 = example.createCriteria();
			UmsMemberBankExample.Criteria criteria2 = example.createCriteria();
			UmsMemberBankExample.Criteria criteria3 = example.createCriteria();
			UmsMemberBankExample.Criteria criteria4 = example.createCriteria();
			if(form.getStatus() != null){
				criteria1.andStatusEqualTo(form.getStatus()).andMemberNameLike("%"+form.getKeyword()+"%");
				criteria2.andStatusEqualTo(form.getStatus()).andAccountNameLike("%"+form.getKeyword()+"%");
				criteria3.andStatusEqualTo(form.getStatus()).andIdCardLike("%"+form.getKeyword()+"%");
				criteria4.andStatusEqualTo(form.getStatus()).andBankAccountLike("%"+form.getKeyword()+"%");
			}else{
				criteria1.andMemberNameLike("%"+form.getKeyword()+"%");
				criteria2.andAccountNameLike("%"+form.getKeyword()+"%");
				criteria3.andIdCardLike("%"+form.getKeyword()+"%");
				criteria4.andBankAccountLike("%"+form.getKeyword()+"%");
			}
			example.or(criteria1);
			example.or(criteria2);
			example.or(criteria3);
			example.or(criteria4);
		}else{
			if(form.getStatus() != null){
				criteria.andStatusEqualTo(form.getStatus());
			}
		}
		if(form.getMemberId() != null){
			criteria.andMemberIdEqualTo(form.getMemberId());
		}
		return memberBankMapper.selectByExample(example);
	}

	@Override
	public UmsMemberBank getMemberBankByMemberId(Integer memberId) {
		UmsMemberBankExample example = new UmsMemberBankExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<UmsMemberBank> list = memberBankMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteMemberBank(Integer id) {
		memberBankMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyMemberBankSus(Integer id) throws ServiceException {
		UmsMemberBank bank = memberBankMapper.selectByPrimaryKey(id);
		if(bank == null || bank.getStatus() != CommonConstant.WAIT_INT){
			throw new ServiceException("待审核状态才能进行审批");
		}

		UmsMemberBank updateBank = new UmsMemberBank();
		updateBank.setId(id);
		updateBank.setStatus(CommonConstant.SUS_INT);
		updateBank.setUpdTime(DateUtil.date());
		memberBankMapper.updateByPrimaryKeySelective(updateBank);

		UmsMember updateMember = new UmsMember();
		updateMember.setId(bank.getMemberId());
		updateMember.setBankStatus(CommonConstant.SUS_INT);
		memberService.updateMember(updateMember);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyMemberBankFail(Integer id, String reason) throws ServiceException {
		UmsMemberBank bank = memberBankMapper.selectByPrimaryKey(id);
		if(bank == null || bank.getStatus() != CommonConstant.WAIT_INT){
			throw new ServiceException("待审核状态才能进行审批");
		}

		UmsMemberBank updateBank = new UmsMemberBank();
		updateBank.setId(id);
		updateBank.setStatus(CommonConstant.FAIL_INT);
		updateBank.setReason(reason);
		updateBank.setUpdTime(DateUtil.date());
		memberBankMapper.updateByPrimaryKeySelective(updateBank);

		UmsMember updateMember = new UmsMember();
		updateMember.setId(bank.getMemberId());
		updateMember.setBankStatus(CommonConstant.FAIL_INT);
		memberService.updateMember(updateMember);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public UmsMemberBank applyBank(UmsMember member, UmsMemberBank memberBank) throws ServiceException {
		ConfigRealname config = configRealnameService.getConfigRealnameByGroupId(member.getGroupId());
		if(config != null){
			if(config.getWeixinNeed() != null && config.getWeixinNeed() == CommonConstant.YES_INT){
				//微信账号必填
				if(StringUtils.isBlank(memberBank.getWxAccount())){
					throw new ServiceException("请填写微信账号");
				}
			}
			if(config.getWeixinImgNeed() != null && config.getWeixinImgNeed() == CommonConstant.YES_INT){
				//微信收款码必填
				if(StringUtils.isBlank(memberBank.getWxErweima())){
					throw new ServiceException("请上传微信收款码");
				}
			}
			if(config.getZfbNeed() != null && config.getZfbNeed() == CommonConstant.YES_INT){
				//支付宝账号必填
				if(StringUtils.isBlank(memberBank.getZfbAccount())){
					throw new ServiceException("请填写支付宝账号");
				}
			}
			if(config.getZfbImgNeed() != null && config.getZfbImgNeed() == CommonConstant.YES_INT){
				//支付宝收款码必填
				if(StringUtils.isBlank(memberBank.getZfbErweima())){
					throw new ServiceException("请上传支付宝收款码");
				}
			}
		}
		UmsMemberBankExample example = new UmsMemberBankExample();
		example.createCriteria().andMemberIdEqualTo(member.getId());
		List<UmsMemberBank> list = memberBankMapper.selectByExample(example);
		UmsMemberBank mbank = new UmsMemberBank();
		BeanUtil.copyProperties(memberBank, mbank);
		if(CollectionUtil.isNotEmpty(list)){
			UmsMemberBank haveBank = list.get(0);
			if(haveBank.getStatus() == CommonConstant.SUS_INT){
				throw new ServiceException("您已完成过银行卡认证");
			}
			mbank.setId(haveBank.getId());
			mbank.setNum(haveBank.getNum());
		}else{
			mbank.setNum(0);
		}
		//判断身份证是否已被认证
		UmsMemberRealnameExample realnameExample = new UmsMemberRealnameExample();
		realnameExample.createCriteria().andIdCardEqualTo(memberBank.getIdCard()).andStatusIn(Arrays.asList(0,1)).andMemberIdNotEqualTo(member.getId());
		if(memberRealnameMapper.countByExample(realnameExample) > 0){
			throw new ServiceException("该身份证已被占用");
		}
		if(config != null && config.getRealnameBankSame() != null && config.getRealnameBankSame() == CommonConstant.YES_INT){
			realnameExample = new UmsMemberRealnameExample();
			realnameExample.createCriteria().andStatusIn(Arrays.asList(0,1)).andMemberIdEqualTo(member.getId());
			List<UmsMemberRealname> realnameList = memberRealnameMapper.selectByExample(realnameExample);
			if(CollectionUtil.isNotEmpty(realnameList)){
				UmsMemberRealname realname = realnameList.get(0);
				if(!realname.getRealname().equals(mbank.getAccountName())){
					throw new ServiceException("收款姓名与实名不一致");
				}
			}
		}
		//查看实名认证配置
		int bankStatus = CommonConstant.WAIT_INT;
		Integer num = mbank.getNum();
		if(config != null && config.getThirdBankStatus() == CommonConstant.YES_INT){
			//第三方接口实时校验, 超过次数需要收费
			if(mbank.getNum() >= config.getChargeNum() && config.getChargeFee().compareTo(BigDecimal.ZERO) > 0){
				memberAccountLogService.outAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_BANK_FEE, StringUtils.genOrderNo(member.getId()), config.getChargeFee(), "银行卡认证超过次数扣费", null);
			}
			//第三方认证API
			if(StringUtils.isNotBlank(memberBank.getBankAccount())){
				String msg = thirdManager.getThidFactory(thirdConfigService.getThirdConfigByNid(ThirdEnums.BANK.getKey()))
						.approveBank(memberBank.getBankAccount(), memberBank.getAccountName(), memberBank.getIdCard());
				if(msg == null){
					bankStatus = CommonConstant.SUS_INT;
				}else{
					bankStatus = CommonConstant.FAIL_INT;
					mbank.setReason(msg);
				}
				num++;
			}
		}
		mbank.setMemberId(member.getId());
		mbank.setMemberName(member.getCode());
		mbank.setStatus(bankStatus);
		mbank.setUpdTime(DateUtil.date());
		mbank.setNum(num);
		if(mbank.getId() == null){
			mbank.setAddTime(mbank.getUpdTime());
			memberBankMapper.insertSelective(mbank);
		}else{
			memberBankMapper.updateByPrimaryKeySelective(mbank);
		}

		if(mbank.getStatus() == CommonConstant.SUS_INT){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(member.getId());
			updateMember.setBankStatus(CommonConstant.SUS_INT);

			//如果开启了同步实名认证
			if(config != null && config.getSynRealname() == CommonConstant.YES_INT && member.getRealnameStatus() != CommonConstant.SUS_INT){
				updateMember.setRealname(mbank.getAccountName());
				updateMember.setRealnameStatus(CommonConstant.SUS_INT);
				UmsMemberRealname realname = memberRealnameService.getMemberRealnameByMemberId(member.getId());
				if(realname == null){
					realname = new UmsMemberRealname();
					realname.setMemberId(member.getId());
					realname.setMemberName(member.getCode());
					realname.setRealname(mbank.getAccountName());
					realname.setIdCard(mbank.getIdCard());
					realname.setLinkPhone(member.getPhone());
					realname.setStatus(CommonConstant.SUS_INT);
					realname.setAddTime(DateUtil.date());
					memberRealnameMapper.insertSelective(realname);
				}else{
					realname.setRealname(mbank.getAccountName());
					realname.setIdCard(mbank.getIdCard());
					realname.setStatus(CommonConstant.SUS_INT);
					memberRealnameMapper.updateByPrimaryKeySelective(realname);
				}
			}
			memberService.updateMember(updateMember);
		}else if(mbank.getStatus() == CommonConstant.WAIT_INT){
			memberRemindService.addAdminRemind(AdminRemindTypes.WAIT_VERIFY_BANK, "「"+member.getCode()+"」申请了银行认证, 请尽快审批");
		}
		return mbank;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(UmsMemberBank memberBank) throws ServiceException {
		memberBank.setUpdTime(DateUtil.date());
		memberBank.setStatus(CommonConstant.SUS_INT);
		if(memberBank.getId() == null){
			memberBank.setAddTime(memberBank.getUpdTime());
			memberBankMapper.insertSelective(memberBank);
		}else{
			memberBankMapper.updateByPrimaryKeySelective(memberBank);
		}

		if(memberBank.getStatus() != null && memberBank.getStatus() == CommonConstant.SUS_INT){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(memberBank.getMemberId());
			updateMember.setBankStatus(CommonConstant.SUS_INT);
			memberService.updateMember(updateMember);
		}else if(memberBank.getStatus() != null && memberBank.getStatus() == CommonConstant.FAIL_INT){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(memberBank.getMemberId());
			updateMember.setBankStatus(CommonConstant.FAIL_INT);
			memberService.updateMember(updateMember);
		}
	}
}