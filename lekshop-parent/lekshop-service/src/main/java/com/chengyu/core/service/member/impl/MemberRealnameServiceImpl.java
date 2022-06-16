package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.AccountEnums;
import com.chengyu.core.domain.enums.MemberRemindEnums.AdminRemindTypes;
import com.chengyu.core.domain.enums.ThirdEnums;
import com.chengyu.core.domain.form.BaseSearchForm;
import com.chengyu.core.domain.result.ChatNotice;
import com.chengyu.core.domain.result.CustomerConstant;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberBankMapper;
import com.chengyu.core.mapper.UmsMemberMapper;
import com.chengyu.core.mapper.UmsMemberRealnameMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.config.ConfigRealnameService;
import com.chengyu.core.service.funds.MemberAccountLogService;
import com.chengyu.core.service.im.ChatService;
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
 * @title  会员实名认证
 * @author LeGreen
 * @date   2021/1/6
 */
@Service
public class MemberRealnameServiceImpl implements MemberRealnameService {
	
	@Autowired
	private UmsMemberRealnameMapper memberRealnameMapper;
	@Autowired
	private ConfigRealnameService configRealnameService;
	@Autowired
	private MemberAccountLogService memberAccountLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UmsMemberMapper memberMapper;
	@Autowired
	private ThirdManager thirdManager;
	@Autowired
	private ThirdConfigService thirdConfigService;
	@Autowired
	private UmsMemberBankMapper memberBankMapper;
	@Autowired
	private ChatService chatService;

	@Override
	public List<UmsMemberRealname> getMemberRealnameList(BaseSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberRealnameExample example = new UmsMemberRealnameExample();
		example.setOrderByClause("add_time desc");
		UmsMemberRealnameExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(form.getKeyword())){
			UmsMemberRealnameExample.Criteria criteria1 = example.createCriteria();
			UmsMemberRealnameExample.Criteria criteria2 = example.createCriteria();
			UmsMemberRealnameExample.Criteria criteria3 = example.createCriteria();
			if(form.getStatus() != null){
				criteria1.andStatusEqualTo(form.getStatus()).andMemberNameLike("%"+form.getKeyword()+"%");
				criteria2.andStatusEqualTo(form.getStatus()).andRealnameLike("%"+form.getKeyword()+"%");
				criteria3.andStatusEqualTo(form.getStatus()).andIdCardLike("%"+form.getKeyword()+"%");
			}else{
				criteria1.andMemberNameLike("%"+form.getKeyword()+"%");
				criteria2.andRealnameLike("%"+form.getKeyword()+"%");
				criteria3.andIdCardLike("%"+form.getKeyword()+"%");
			}
			example.or(criteria1);
			example.or(criteria2);
			example.or(criteria3);
		}else{
			if(form.getStatus() != null){
				criteria.andStatusEqualTo(form.getStatus());
			}
		}
		return memberRealnameMapper.selectByExample(example);
	}

	@Override
	public UmsMemberRealname getMemberRealnameByMemberId(Integer memberId) {
		UmsMemberRealnameExample example = new UmsMemberRealnameExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<UmsMemberRealname> list = memberRealnameMapper.selectByExample(example);
		return CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteMemberRealname(Integer id) {
		UmsMemberRealname realname = memberRealnameMapper.selectByPrimaryKey(id);
		memberRealnameMapper.deleteByPrimaryKey(id);

		//删除实名后, 用户变成未实名
		UmsMember updateMember = new UmsMember();
		updateMember.setId(realname.getMemberId());
		updateMember.setRealname("");
		updateMember.setRealnameStatus(CommonConstant.NO_INT);
		memberMapper.updateByPrimaryKeySelective(updateMember);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyMemberRealnameSus(Integer id) throws ServiceException {
		UmsMemberRealname realname = memberRealnameMapper.selectByPrimaryKey(id);
		if(realname == null || realname.getStatus() != CommonConstant.WAIT_INT){
			throw new ServiceException("待审核状态才能进行审批");
		}

		UmsMemberRealname updateRealname = new UmsMemberRealname();
		updateRealname.setId(id);
		updateRealname.setStatus(CommonConstant.SUS_INT);
		updateRealname.setUpdTime(DateUtil.date());
		memberRealnameMapper.updateByPrimaryKeySelective(updateRealname);

		UmsMember updateMember = new UmsMember();
		updateMember.setId(realname.getMemberId());
		updateMember.setRealnameStatus(CommonConstant.SUS_INT);
		updateMember.setRealname(realname.getRealname());
		memberService.updateMember(updateMember);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void verifyMemberRealnameFail(Integer id, String remark) throws ServiceException {
		UmsMemberRealname realname = memberRealnameMapper.selectByPrimaryKey(id);
		if(realname == null || realname.getStatus() != CommonConstant.WAIT_INT){
			throw new ServiceException("待审核状态才能进行审批");
		}

		UmsMemberRealname updateRealname = new UmsMemberRealname();
		updateRealname.setId(id);
		updateRealname.setStatus(CommonConstant.FAIL_INT);
		updateRealname.setUpdTime(DateUtil.date());
		updateRealname.setReason(remark);
		memberRealnameMapper.updateByPrimaryKeySelective(updateRealname);
	}

	@Override
	public boolean validateRealname(Integer memberId) {
		UmsMemberRealnameExample example = new UmsMemberRealnameExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(CommonConstant.SUS_INT);
		return memberRealnameMapper.countByExample(example) > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public UmsMemberRealname applyRealname(UmsMember member, String realname, String idCard, String idcardFace, String idcardBack, String idcardFaceHand) throws ServiceException {
		UmsMemberRealnameExample example = new UmsMemberRealnameExample();
		example.createCriteria().andMemberIdEqualTo(member.getId());
		List<UmsMemberRealname> list = memberRealnameMapper.selectByExample(example);
		UmsMemberRealname memberRealname;
		if(CollectionUtil.isNotEmpty(list)){
			memberRealname = list.get(0);

			if(memberRealname.getStatus() == CommonConstant.SUS_INT){
				throw new ServiceException("您已完成过实名认证");
			}
			if(memberRealname.getStatus() == CommonConstant.WAIT_INT){
				throw new ServiceException("您的实名认证正在审核中,请耐心等待");
			}
		}else{
			memberRealname = new UmsMemberRealname();
			memberRealname.setNum(0);
		}
		//判断身份证号码是否已认证
		example = new UmsMemberRealnameExample();
		example.createCriteria().andIdCardEqualTo(idCard).andStatusIn(Arrays.asList(0,1));
		if(memberRealnameMapper.countByExample(example) > 0){
			throw new ServiceException("该身份证已被占用");
		}
		ConfigRealname config = configRealnameService.getConfigRealnameByGroupId(member.getGroupId());
		if(config != null && config.getRealnameBankSame() != null && config.getRealnameBankSame() == CommonConstant.YES_INT){
			UmsMemberBankExample bankExample = new UmsMemberBankExample();
			bankExample.createCriteria().andStatusIn(Arrays.asList(0,1)).andMemberIdEqualTo(member.getId());
			List<UmsMemberBank> bankList = memberBankMapper.selectByExample(bankExample);
			if(CollectionUtil.isNotEmpty(bankList)){
				UmsMemberBank bank = bankList.get(0);
				if(!bank.getAccountName().equals(realname)){
					throw new ServiceException("收款姓名与实名不一致");
				}
			}
		}
		//查看实名认证配置
		int realnameStatus = CommonConstant.WAIT_INT;
		Integer num = memberRealname.getNum();
		if(config != null && config.getThirdRealnameStatus() == CommonConstant.YES_INT){
			//第三方接口实时校验, 超过次数需要收费
			if(memberRealname.getNum() >= config.getChargeNum() && config.getChargeFee().compareTo(BigDecimal.ZERO) > 0){
				memberAccountLogService.outAccount(member, AccountEnums.MemberAccountTypes.ACCOUNT_REALNAME_FEE, StringUtils.genOrderNo(member.getId()), config.getChargeFee(), "实名认证超过次数扣费", null);
			}
			//第三方认证API
			String msg = thirdManager.getThidFactory(thirdConfigService.getThirdConfigByNid(ThirdEnums.IDCARD.getKey()))
					.approveRealname(idCard, realname);
			if(msg != null){
				realnameStatus = CommonConstant.FAIL_INT;
				memberRealname.setReason(msg);
			}else{
				realnameStatus = CommonConstant.SUS_INT;
			}
			num++;
		}
		memberRealname.setMemberId(member.getId());
		memberRealname.setMemberName(member.getCode());
		memberRealname.setRealname(realname);
		memberRealname.setIdCard(idCard);
		memberRealname.setIdcardFace(idcardFace);
		memberRealname.setIdcardBack(idcardBack);
		memberRealname.setIdcardFaceHand(idcardFaceHand);
		memberRealname.setNum(num);
		memberRealname.setStatus(realnameStatus);
		memberRealname.setUpdTime(DateUtil.date());
		if(memberRealname.getId() == null){
			memberRealname.setAddTime(memberRealname.getUpdTime());
			memberRealnameMapper.insertSelective(memberRealname);
		}else{
			memberRealnameMapper.updateByPrimaryKeySelective(memberRealname);
		}

		if(memberRealname.getStatus() != null && memberRealname.getStatus() == CommonConstant.SUS_INT){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(member.getId());
			updateMember.setRealnameStatus(CommonConstant.SUS_INT);
			updateMember.setRealname(realname);
			memberService.updateMember(updateMember);
		}
		if(memberRealname.getStatus() != null && memberRealname.getStatus() == CommonConstant.WAIT_INT){
			ChatNotice notice = new ChatNotice();
			notice.setTitle("「"+member.getCode()+"」申请了实名认证, 请尽快审批");
			notice.setContent(JSONUtil.toJsonStr(realname));
			notice.setType(AdminRemindTypes.WAIT_VERIFY_REALNAME.getType());
			chatService.sendNoticeMsg(CustomerConstant.ADMIN_MEMBER_ID, JSONUtil.toJsonStr(notice));
//			memberRemindService.addAdminRemind(AdminRemindTypes.WAIT_VERIFY_REALNAME, "「"+member.getCode()+"」申请了实名认证, 请尽快审批");
		}

		return memberRealname;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(UmsMemberRealname realname) throws ServiceException {
		realname.setUpdTime(DateUtil.date());
		memberRealnameMapper.updateByPrimaryKeySelective(realname);

		if(realname.getStatus() == CommonConstant.SUS_INT){
			UmsMember updateMember = new UmsMember();
			updateMember.setId(realname.getMemberId());
			updateMember.setRealnameStatus(CommonConstant.SUS_INT);
			memberService.updateMember(updateMember);
		}
	}
}