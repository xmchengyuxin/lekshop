package com.chengyu.core.service.member.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.BlockTypeEnums;
import com.chengyu.core.domain.enums.ConfigEnums;
import com.chengyu.core.domain.enums.MemberTypes;
import com.chengyu.core.domain.form.MemberSearchForm;
import com.chengyu.core.domain.result.MemberDetails;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.UmsMemberLoginLogMapper;
import com.chengyu.core.mapper.UmsMemberMapper;
import com.chengyu.core.mapper.UmsMemberRelMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.security.util.JwtTokenUtil;
import com.chengyu.core.service.config.ConfigMissionService;
import com.chengyu.core.service.member.*;
import com.chengyu.core.service.system.ConfigService;
import com.chengyu.core.service.system.SysInviteCodeService;
import com.chengyu.core.service.walk.WalkMemberService;
import com.chengyu.core.util.ip.IpSearch;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title  用户实现类
 * @author LeGreen
 * @date   2021/1/7
 */
@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ConfigService configService;
	@Autowired
	private UmsMemberMapper memberMapper;
	@Autowired
	private MemberRelService memberRelService;
	@Autowired
	private MemberGroupService memberGroupService;
	@Autowired
	private ConfigMissionService configMissionService;
	@Autowired
	private MemberAccountService memberAccountService;
	@Autowired
	private UmsMemberLoginLogMapper memberLoginLogMapper;
	@Autowired
	private UmsMemberRelMapper memberRelMapper;
	@Autowired
	private MemberWithdrawService withdrawService;
	@Autowired
	private SysInviteCodeService sysInviteCodeService;
	@Autowired
	private WalkMemberService walkMemberService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void register(UmsMember member) throws ServiceException {
		//校验code是否已被注册
		UmsMember haveMember = this.getMemberByParams(member.getCode(), MemberTypes.CODE);
		if(haveMember != null) {
			throw new ServiceException("该账号已被注册");
		}
		if(StringUtils.isBlank(member.getHeadImg())){
			member.setHeadImg(configService.getValueByNid(ConfigEnums.ConfigTypes.HEADIMG.getNid()));
		}
		if(StringUtils.isBlank(member.getNickname())){
			member.setNickname("U"+StringUtils.getSixCode());
		}
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		if(member.getMethod() == null){
			member.setMethod(1);
		}
		member.setStatus(CommonConstant.YES_INT);
		member.setAddTime(DateUtil.date());
		//获取会员默认组别
		UmsMemberGroup memberGroup;
		if(member.getType() == CommonConstant.BUYER){
			memberGroup = memberGroupService.getDefaultBuyerGroup();
		}else{
			memberGroup = memberGroupService.getDefaultSellerGroup();
		}
		if(memberGroup == null){
			throw new ServiceException("系统尚未设置默认会员组, 注册失败");
		}
		member.setGroupId(memberGroup.getId());
		member.setGroupIcon(memberGroup.getIcon());
		member.setGroupName(memberGroup.getName());
		//获取会员默认佣金组
		ConfigMission configMission = configMissionService.getDefaultConfigMission();
		member.setMissionConfigId(configMission == null ? null : configMission.getId());
		if(StringUtils.isNotBlank(member.getPhone())){
			member.setPhoneStatus(CommonConstant.SUS_INT);
		}
		member.setInviteCode(sysInviteCodeService.genInviteCode());
		if(member.getTjrId() != null && member.getTjrId() == 0){
			member.setTjrId(null);
		}
		//获取初始化服务费
		memberMapper.insertSelective(member);

		UmsMember updateMember = new UmsMember();
		updateMember.setId(member.getId());
		String beginIndexStr = configService.getValueByNid("id_begin_index");
		updateMember.setUid((StringUtils.isNotBlank(beginIndexStr) ? Integer.parseInt(beginIndexStr) : 1000000 )+member.getId());
		memberMapper.updateByPrimaryKeySelective(updateMember);

		sysInviteCodeService.updateUserId(member.getInviteCode(), CommonConstant.INVITE_CODE_MEMBER, member.getId());

		//注册用户分销关系
		if(member.getTjrId() != null){
			memberRelService.addMemberRel(member.getTjrId(), member.getId());
		}

		//初始化用户账户
		memberAccountService.initAccount(member, memberGroup);

		//初始化创作号
		member.setUid(updateMember.getUid());
		walkMemberService.addWalkMember(member);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateMember(UmsMember updateMember) {
		if(StringUtils.isNotBlank(updateMember.getPassword())){
			updateMember.setPassword(passwordEncoder.encode(updateMember.getPassword()));
		}else{
			updateMember.setPassword(null);
		}
		if(StringUtils.isNotBlank(updateMember.getPayPassword())){
			updateMember.setPayPassword(SecureUtil.md5(updateMember.getPayPassword()));
		}else{
			updateMember.setPayPassword(null);
		}
		if(updateMember.getGroupId() != null){
			UmsMemberGroup group = memberGroupService.getGroup(updateMember.getGroupId());
			updateMember.setType(group.getType());
			updateMember.setGroupName(group.getName());
		}
		updateMember.setUpdTime(DateUtil.date());
		memberMapper.updateByPrimaryKeySelective(updateMember);
	}

	@Override
	public List<UmsMember> getMemberList(MemberSearchForm form, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		UmsMemberExample example = new UmsMemberExample();
		if(StringUtils.isNotBlank(form.getSort())){
			example.setOrderByClause(form.getSort());
		}else{
			example.setOrderByClause("add_time desc");
		}
		UmsMemberExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(form.getCode())){
			criteria.andCodeLike("%"+form.getCode()+"%");
		}
		if(StringUtils.isNotBlank(form.getRealname())){
			criteria.andRealnameLike("%"+form.getRealname()+"%");
		}
		if(StringUtils.isNotBlank(form.getNickname())){
			criteria.andNicknameLike("%"+form.getNickname()+"%");
		}
		if(StringUtils.isNotBlank(form.getPhone())){
			criteria.andPhoneLike("%"+form.getPhone()+"%");
		}
		if(form.getMethod() != null){
			criteria.andMethodEqualTo(form.getMethod());
		}
		if(form.getStatus() != null){
			criteria.andStatusEqualTo(form.getStatus());
		}
		if(form.getPhoneStatus() != null){
			criteria.andPhoneStatusEqualTo(form.getPhoneStatus());
		}
		if(form.getRealnameStatus() != null){
			criteria.andRealnameStatusEqualTo(form.getRealnameStatus());
		}
		if(form.getBankStatus() != null){
			criteria.andBankStatusEqualTo(form.getBankStatus());
		}
		if(form.getTjrId() != null){
			criteria.andTjrIdEqualTo(form.getTjrId());
		}
		if(form.getDateFrom() != null && form.getDateTo() != null){
			criteria.andAddTimeBetween(form.getDateFrom(), form.getDateTo());
		}
		if(form.getGroupId() != null){
			criteria.andGroupIdEqualTo(form.getGroupId());
		}
		if(form.getType() != null){
			criteria.andTypeEqualTo(form.getType());
		}
		if(StringUtils.isNotBlank(form.getRegisterIp())){
			criteria.andRegisterIpEqualTo(form.getRegisterIp());
		}
		if(form.getMemberId() != null){
			criteria.andIdEqualTo(form.getMemberId());
		}
		if(StringUtils.isNotBlank(form.getQq())){
			criteria.andQqEqualTo(form.getQq());
		}
		return memberMapper.selectByExample(example);
	}

	@Override
	public UmsMember getMemberById(Integer id) {
		return memberMapper.selectByPrimaryKey(id);
	}

	@Override
	public UmsMember getMemberByParams(String params, MemberTypes type) {
		UmsMemberExample example = new UmsMemberExample();
		UmsMemberExample.Criteria criteria = example.createCriteria();
		switch (type) {
		case CODE:
			criteria.andCodeEqualTo(params);
			break;

		case KF_OPEN_ID:
			criteria.andKfOpenIdEqualTo(params);
			break;
			
		case XCX_OPEN_ID:
			criteria.andXcxOpenIdEqualTo(params);
			break;
			
		case GZH_OPEN_ID:
			criteria.andGzhOpenIdEqualTo(params);
			break;
			
		case UNIONID:
			criteria.andUnionidEqualTo(params);
			break;

		case PHONE:
			criteria.andPhoneEqualTo(params);
			break;

		case INVITE_CODE:
			criteria.andInviteCodeEqualTo(params);
			break;
		case UID:
			criteria.andUidEqualTo(Integer.parseInt(params));
			break;
		default:
			break;
		}
		List<UmsMember> list = memberMapper.selectByExample(example);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}

	@Override
	public UmsMember getCurrentMember() {
		try {
			SecurityContext ctx = SecurityContextHolder.getContext();
	        Authentication auth = ctx.getAuthentication();
	        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
	        return memberDetails.getUmsMember();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		
		return null;
	}
	
	@Override
	public UmsMember getCurrentMemberOrNull() {
		try {
			SecurityContext ctx = SecurityContextHolder.getContext();
			Authentication auth = ctx.getAuthentication();
			String nullKey = "anonymousUser";
			if(nullKey.equals(auth.getPrincipal())) {
				return null;
			}
			
			MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
			return memberDetails.getUmsMember();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username){
		UmsMember member = getMemberByParams(username, MemberTypes.CODE);
		if(member == null){
			member = getMemberByParams(username, MemberTypes.PHONE);
		}
		if(member!=null){
            return new MemberDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
	}

	@Override
	public String login(String username, String password, String ip) {
		String token = null;
        //密码需要客户端加密后传递
		UserDetails userDetails = null;
		UmsMember member = getMemberByParams(username, MemberTypes.CODE);
		if(member == null){
			member = getMemberByParams(username, MemberTypes.PHONE);
		}
		if(member!=null){
			userDetails = new MemberDetails(member);
		}
		if(userDetails == null){
			throw new UsernameNotFoundException("用户名或密码错误");
		}
        try {
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            this.insertLoginLog(member, ip);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}

	@Override
	public String loginByNoPassword(String username) {
		String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void bindPhone(Integer deleteId, UmsMember member) {
		if(deleteId != null){
			memberMapper.deleteByPrimaryKey(deleteId);
		}
		memberMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public UmsMemberLoginLog getLastLoginLog(Integer memberId) {
		UmsMemberLoginLogExample example = new UmsMemberLoginLogExample();
		example.setOrderByClause("add_time desc limit 2");
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<UmsMemberLoginLog> list = memberLoginLogMapper.selectByExample(example);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}else if(list.size() == 1){
			return list.get(0);
		}else{
			return list.get(1);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateInvite(Integer memberId, String inviteUid) throws ServiceException {
		UmsMember member = this.getMemberByParams(inviteUid, MemberTypes.UID);
		if(member == null){
			throw new ServiceException("推荐人ID不存在");
		}
		if(member.getId().equals(memberId)){
			throw new ServiceException("推荐人不能是自己");
		}

		UmsMember updateMember = new UmsMember();
		updateMember.setId(memberId);
		updateMember.setTjrId(member.getId());
		updateMember.setTjrUid(member.getUid());
		memberMapper.updateByPrimaryKeySelective(updateMember);

		UmsMemberRelExample example = new UmsMemberRelExample();
		example.createCriteria().andDownMemberIdEqualTo(memberId);
		memberRelMapper.deleteByExample(example);

		memberRelService.addMemberRel(updateMember.getTjrId(), updateMember.getId());

		//调整人的下级也要跟着调整
		List<UmsMember> memberList = memberRelService.getAllDownMemberByMemberId(memberId);
		if(CollectionUtil.isNotEmpty(memberList)){
			for(UmsMember adMember : memberList){
				example = new UmsMemberRelExample();
				example.createCriteria().andDownMemberIdEqualTo(adMember.getId());
				memberRelMapper.deleteByExample(example);

				memberRelService.addMemberRel(adMember.getTjrId(), adMember.getId());
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void limitWithdraw(Integer memberId, Integer status) throws ServiceException {
		UmsMember updateMember = new UmsMember();
		updateMember.setId(memberId);
		updateMember.setWithdrawLimit(status);
		memberMapper.updateByPrimaryKeySelective(updateMember);

		//驳回当前申请的提现
		if(status == CommonConstant.YES_INT){
			withdrawService.rejectAllWithdraw(memberId);
		}
	}

	/**
	 * 添加登录记录
	 * @author LeGreen
	 * @date   2021/1/6
	 * @param  member
	 * @param  ip
	 */
	private void insertLoginLog(UmsMember member, String ip) {
		UmsMemberLoginLog loginLog = new UmsMemberLoginLog();
		loginLog.setMemberId(member.getId());
		loginLog.setMemberName(member.getCode());
		loginLog.setAddTime(new Date());
		loginLog.setAddBy(member.getCode());
		loginLog.setIp(ip);
		loginLog.setAddress(IpSearch.getInstance().getIpAddress(ip));
		memberLoginLogMapper.insert(loginLog);
	}
}