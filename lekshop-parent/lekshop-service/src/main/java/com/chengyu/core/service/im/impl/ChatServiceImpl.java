package com.chengyu.core.service.im.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberRemindEnums;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.result.ChatNotice;
import com.chengyu.core.domain.result.CustomerConstant;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.CustomChatMapper;
import com.chengyu.core.mapper.ImChatLogMapper;
import com.chengyu.core.mapper.ImChatSessionMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.im.ChatService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.service.schedule.ScheduleService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.chat.QingyunkeUtil;
import com.chengyu.core.util.netty.NettyPushUtil;
import com.chengyu.core.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ImChatLogMapper chatLogMapper;
	@Autowired
	private ImChatSessionMapper chatSessionMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private MemberService memberService;
	@Autowired
	private NettyPushUtil nettyPushUtil;
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private CustomChatMapper customChatMapper;

	@Override
	public Long initChatSession(UmsMember member, UmsMember targetMember) {
		ImChatSessionExample example = new ImChatSessionExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andTargetIdEqualTo(targetMember.getId());
		List<ImChatSession> list = chatSessionMapper.selectByExample(example);
		Date now = DateUtil.date();
		if(CollectionUtil.isEmpty(list)){
			ImChatSession session = new ImChatSession();
			session.setMemberId(member.getId());
			session.setMemberNickname(member.getNickname());
			session.setMemberHeadImg(member.getHeadImg());
			session.setTargetId(targetMember.getId());
			session.setTargetNickname(targetMember.getNickname());
			session.setTargetHeadImg(targetMember.getHeadImg());
			if(targetMember.getId() == CustomerConstant.MEMBER_ID){
				session.setMsgType("text");
				session.setLastMsg(CustomerConstant.WELCOME);
			}
			session.setUnReadNum(0);
			session.setAddTime(now);
			session.setUpdTime(now);
			chatSessionMapper.insertSelective(session);
			if(targetMember.getId() == CustomerConstant.MEMBER_ID){
				this.initCustomerMsg(member, session.getId());
			}
			return session.getId();
		}else{
			ImChatSession session = list.get(0);
			ImChatSession updateSession = new ImChatSession();
			updateSession.setId(session.getId());
			updateSession.setUpdTime(now);
			chatSessionMapper.updateByPrimaryKeySelective(updateSession);
			return session.getId();
		}
	}

	private void initCustomerMsg(UmsMember member, Long sessionId) {
		ImChatLog chatLog = new ImChatLog();
		chatLog.setMsgType("text");
		chatLog.setMsgContent(CustomerConstant.WELCOME);
		chatLog.setReadStatus(CommonConstant.NO_INT);
		chatLog.setSendStatus(CommonConstant.NO_INT);
		chatLog.setSendTime(DateUtil.date());
		chatLog.setUpdTime(chatLog.getSendTime());
		chatLog.setSessionId(sessionId);
		chatLog.setMemberId(member.getId());
		chatLog.setMemberNickname(member.getNickname());
		chatLog.setMemberHeadImg(member.getHeadImg());
		chatLog.setTargetId(CustomerConstant.MEMBER_ID);
		chatLog.setTargetNickname(CustomerConstant.NICKNAME);
		chatLog.setTargetHeadImg(CustomerConstant.AVATAR);
		chatLog.setSendType(2);
		chatLogMapper.insertSelective(chatLog);
	}

	@Override
	public List<ImChatSession> getChatSessionList(Integer memberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);

		ImChatSessionExample example = new ImChatSessionExample();
		example.setOrderByClause("upd_time desc");
		example.createCriteria().andMemberIdEqualTo(memberId);
		return chatSessionMapper.selectByExample(example);
	}

	@Override
	public void deleteChatSession(UmsMember member, Long sessionId) {
		ImChatSessionExample example = new ImChatSessionExample();
		example.createCriteria().andMemberIdEqualTo(member.getId()).andIdEqualTo(sessionId);
		chatSessionMapper.deleteByExample(example);

		//删除相关聊天记录
		ImChatLogExample logExample = new ImChatLogExample();
		logExample.createCriteria().andMemberIdEqualTo(member.getId()).andSessionIdEqualTo(sessionId);
		chatLogMapper.deleteByExample(logExample);
	}

	@Override
	public ImChatLog sendMsg(UmsMember member, Integer targetMemberId, String msgType, String content) {
		//查询发送人的会话
		UmsMember targetMember = this.getMemberByRds(targetMemberId);
		Long sessionId = this.initChatSession(member, targetMember);
		ImChatLog chatLog = new ImChatLog();
		chatLog.setMsgType(msgType);
		chatLog.setMsgContent(content);
		chatLog.setReadStatus(CommonConstant.YES_INT);
		chatLog.setSendStatus(CommonConstant.NO_INT);
		chatLog.setSendTime(DateUtil.date());
		chatLog.setUpdTime(chatLog.getSendTime());
		chatLog.setSessionId(sessionId);
		chatLog.setMemberId(member.getId());
		chatLog.setMemberNickname(member.getNickname());
		chatLog.setMemberHeadImg(member.getHeadImg());
		chatLog.setTargetId(targetMember.getId());
		chatLog.setTargetNickname(targetMember.getNickname());
		chatLog.setTargetHeadImg(targetMember.getHeadImg());
		chatLog.setSendType(1);
		chatLogMapper.insertSelective(chatLog);
		if(targetMemberId == CustomerConstant.MEMBER_ID){
			ThreadUtil.execAsync(() -> {
				this.smartAnswer(member, chatLog);
			}, false);
			return chatLog;
		}

		Long targetSessionId = this.initChatSession(targetMember, member);
		ImChatLog targetLog = new ImChatLog();
		BeanUtil.copyProperties(chatLog, targetLog);
		targetLog.setSessionId(targetSessionId);
		targetLog.setMemberId(targetMember.getId());
		targetLog.setMemberNickname(targetMember.getNickname());
		targetLog.setMemberHeadImg(targetMember.getHeadImg());
		targetLog.setTargetId(member.getId());
		targetLog.setTargetNickname(member.getNickname());
		targetLog.setTargetHeadImg(member.getHeadImg());
		targetLog.setSendType(2);
		targetLog.setReadStatus(CommonConstant.NO_INT);
		chatLogMapper.insertSelective(targetLog);

		//更新最新消息
		if("notice".equals(msgType)){
			msgType = "text";
			ChatNotice chatNotice = JSONUtil.toBean(content, ChatNotice.class);
			content = chatNotice.getTitle();
		}
		baseMapper.update("update im_chat_session set upd_time = now(), msg_type='"+msgType+"', last_msg='"+content+"' where id="+sessionId);
		baseMapper.update("update im_chat_session set un_read_num = un_read_num+1, upd_time = now(), msg_type='"+msgType+"', last_msg='"+content+"' where id="+targetSessionId);

		//通过netty发送
		Map<String,String> extras = new HashMap<>(16);
		extras.put("reqId", targetLog.getId().toString());
		extras.put("rid", "member-"+targetMember.getId().toString());
		extras.put("type", MemberRemindEnums.MemberRemindTypes.CHAT.getType().toString());
		extras.put("content", JSONUtil.toJsonStr(targetLog));
		extras.put("addTime", DateUtil.current()+"");
		nettyPushUtil.sendMsg(JSONUtil.toJsonStr(extras));
		return chatLog;
	}

	@Override
	public void sendNoticeMsg(Integer targetMemberId, String content) {
		this.sendMsg(this.getMemberByRds(CustomerConstant.NOTICE_MEMBER_ID), targetMemberId, "notice", content);
	}

	/**
	 * 人工智能回答
	 * @author LeGreen
	 * @date   2022/5/26
	 * @param  member
	 * @param  chatLog
	 */
	private void smartAnswer(UmsMember member, ImChatLog chatLog) {
		ImChatLog targetLog = new ImChatLog();
		targetLog.setMsgType("text");
		targetLog.setMsgContent(!"text".equals(chatLog.getMsgType()) ? "对不起，目前我只看得懂文字~" : QingyunkeUtil.getAnswer(chatLog.getMsgContent()));
		targetLog.setSendStatus(CommonConstant.NO_INT);
		targetLog.setSendTime(DateUtil.offsetSecond(DateUtil.date(),5));
		targetLog.setUpdTime(targetLog.getSendTime());

		targetLog.setSessionId(chatLog.getSessionId());
		targetLog.setMemberId(member.getId());
		targetLog.setMemberNickname(member.getNickname());
		targetLog.setMemberHeadImg(member.getHeadImg());

		targetLog.setTargetId(CustomerConstant.MEMBER_ID);
		targetLog.setTargetNickname(CustomerConstant.NICKNAME);
		targetLog.setTargetHeadImg(CustomerConstant.AVATAR);
		targetLog.setSendType(2);
		targetLog.setReadStatus(CommonConstant.NO_INT);
		chatLogMapper.insertSelective(targetLog);

		//更新最新消息
		baseMapper.update("update im_chat_session set upd_time = now(), msg_type='"+targetLog.getMsgType()+"', last_msg='"+targetLog.getMsgContent()+"' where id="+chatLog.getSessionId());

		//通过netty发送
		Map<String,String> extras = new HashMap<>(16);
		extras.put("reqId", StringUtils.genenrateInd());
		extras.put("rid", "member-"+member.getId().toString());
		extras.put("type", MemberRemindEnums.MemberRemindTypes.CHAT.getType().toString());
		extras.put("content", JSONUtil.toJsonStr(targetLog));
		extras.put("addTime", DateUtil.current()+"");
		nettyPushUtil.sendMsg(JSONUtil.toJsonStr(extras));
//		scheduleService.scheduleFixTimeJob(RobotAnswerJob.class, DateUtil.offsetSecond(targetLog.getSendTime(), 5), JSONUtil.toJsonStr(extras));
	}

	private UmsMember getMemberByRds(Integer memberId){
		UmsMember member = (UmsMember) redisUtil.getRedisValue(RedisEnums.CHAT_MEMBER.getKey()+"-"+memberId);
		if(member != null){
			return member;
		}
		if(memberId == CustomerConstant.MEMBER_ID){
			member = new UmsMember();
			member.setId(CustomerConstant.MEMBER_ID);
			member.setNickname(CustomerConstant.NICKNAME);
			member.setHeadImg(CustomerConstant.AVATAR);
			member.setUid(CustomerConstant.UID);
		}else if(memberId == CustomerConstant.ADMIN_MEMBER_ID){
			member = new UmsMember();
			member.setId(CustomerConstant.ADMIN_MEMBER_ID);
			member.setNickname(CustomerConstant.ADMIN_NICKNAME);
			member.setHeadImg(CustomerConstant.ADMIN_AVATAR);
			member.setUid(CustomerConstant.ADMIN_UID);
		}else if(memberId == CustomerConstant.NOTICE_MEMBER_ID){
			member = new UmsMember();
			member.setId(CustomerConstant.NOTICE_MEMBER_ID);
			member.setNickname(CustomerConstant.NOTICE_NICKNAME);
			member.setHeadImg(CustomerConstant.NOTICE_AVATAR);
			member.setUid(CustomerConstant.NOTICE_UID);
		}else{
			member = memberService.getMemberById(memberId);
		}
		UmsMember rdsMember = new UmsMember();
		rdsMember.setId(member.getId());
		rdsMember.setUid(member.getUid());
		rdsMember.setNickname(member.getNickname());
		rdsMember.setHeadImg(member.getHeadImg());
		redisUtil.setRedisValue(RedisEnums.CHAT_MEMBER.getKey()+"-"+memberId, rdsMember);
		return rdsMember;
	}

	@Override
	public List<ImChatLog> getChatLogList(Integer memberId, Integer targetMemberId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		ImChatLogExample example = new ImChatLogExample();
		example.setOrderByClause("send_time desc");
		ImChatLogExample.Criteria criteria = example.createCriteria();
		if(memberId != null){
			criteria.andMemberIdEqualTo(memberId);
		}
		if(targetMemberId != null){
			criteria.andTargetIdEqualTo(targetMemberId);
		}
		return chatLogMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void readBySessionId(UmsMember member, Long sessionId) {
		ImChatSessionExample sessionExample = new ImChatSessionExample();
		ImChatSessionExample.Criteria criteria = sessionExample.createCriteria();
		criteria.andMemberIdEqualTo(member.getId());
		if(sessionId != null){
			criteria.andIdEqualTo(sessionId);
		}
		ImChatSession updateSession = new ImChatSession();
		updateSession.setUnReadNum(0);
		chatSessionMapper.updateByExampleSelective(updateSession, sessionExample);

		ImChatLogExample logExample = new ImChatLogExample();
		ImChatLogExample.Criteria logCriteria = logExample.createCriteria();
		logCriteria.andMemberIdEqualTo(member.getId()).andReadStatusEqualTo(CommonConstant.NO_INT);
		if(sessionId != null){
			logCriteria.andIdEqualTo(sessionId);
		}
		ImChatLog updateLog = new ImChatLog();
		updateLog.setReadStatus(CommonConstant.YES_INT);
		chatLogMapper.updateByExampleSelective(updateLog, logExample);
	}

	@Override
	public Long countUnReadNum(Integer memberId) {
		Long num = customChatMapper.countUnReadNum(memberId);
		return num == null ? 0 : num;
	}
}