package com.chengyu.core.service.im.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberRemindEnums;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.mapper.BaseMapper;
import com.chengyu.core.mapper.ImChatLogMapper;
import com.chengyu.core.mapper.ImChatSessionMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.im.ChatService;
import com.chengyu.core.service.member.MemberService;
import com.chengyu.core.util.RedisUtil;
import com.chengyu.core.util.netty.NettyPushUtil;
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
			session.setUnReadNum(0);
			session.setAddTime(now);
			session.setUpdTime(now);
			chatSessionMapper.insertSelective(session);
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
	}

	@Override
	public ImChatLog sendMsg(UmsMember member, Integer targetMemberId, Integer msgType, String content) {
		//查询发送人的会话
		UmsMember targetMember = this.getMemberByRds(targetMemberId);
		Long sessionId = this.initChatSession(member, targetMember);
		Long targetSessionId = this.initChatSession(targetMember, member);
		ImChatLog chatLog = new ImChatLog();
		chatLog.setMsgType(msgType);
		chatLog.setMsgContent(content);
		chatLog.setReadStatus(CommonConstant.NO_INT);
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
		chatLogMapper.insertSelective(targetLog);

		//更新最新消息
		baseMapper.update("update im_chat_session set un_read_num = un_read_num+1, upd_time = now(), last_msg="+content+" where id="+sessionId);
		baseMapper.update("update im_chat_session set un_read_num = un_read_num+1, upd_time = now(), last_msg="+content+" where id="+targetSessionId);

		//通过netty发送
		Map<String,String> extras = new HashMap<>(16);
		extras.put("reqId", chatLog.getId().toString());
		extras.put("rid", "member-"+targetMember.getId().toString());
		extras.put("type", MemberRemindEnums.MemberRemindTypes.CHAT.getType().toString());
		extras.put("content", content);
		extras.put("addTime", DateUtil.current(false)+"");
		nettyPushUtil.sendMsg(JSONUtil.toJsonStr(extras));
		return chatLog;
	}

	private UmsMember getMemberByRds(Integer memberId){
		UmsMember member = (UmsMember) redisUtil.getRedisValue(RedisEnums.CHAT_MEMBER.getKey()+"-"+memberId);
		if(member != null){
			return member;
		}
		member = memberService.getMemberById(memberId);
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
}