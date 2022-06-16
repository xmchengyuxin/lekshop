package com.chengyu.core.controller.im;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.AdminBaseController;
import com.chengyu.core.domain.result.CustomerConstant;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.ImChatLog;
import com.chengyu.core.model.ImChatSession;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.im.ChatService;
import com.chengyu.core.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "聊天")
@Controller
@RequestMapping("/system")
public class ChatController extends AdminBaseController {
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "会话列表")
	@ResponseBody
	@RequestMapping(value="/chatSession/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ImChatSession>> getSessionList(Integer targetMemberId, Integer page, Integer pageSize) {
		UmsMember member = getChatMember();
		if(targetMemberId != null){
			chatService.initChatSession(member, memberService.getMemberById(targetMemberId));
		}
		List<ImChatSession> list = chatService.getChatSessionList(member.getId(), page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "删除聊天会话")
	@ResponseBody
	@RequestMapping(value="/chatSession/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(Long sessionId) throws ServiceException {
		chatService.deleteChatSession(getChatMember(), sessionId);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "获取聊天记录")
	@ResponseBody
	@RequestMapping(value="/chat/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ImChatLog>> getCahtList(Integer targetMemberId, Integer page, Integer pageSize) {
		List<ImChatLog> list = chatService.getChatLogList(getChatMember().getId(), targetMemberId, page, pageSize);
		return CommonResult.success(CommonPage.restPage(CollectionUtil.reverse(list)));
	}

	@ApiOperation(value = "发消息")
	@ResponseBody
	@RequestMapping(value="/chat/send", method=RequestMethod.POST)
	public CommonResult<String> send(Integer targetMemberId, String msgType, String content) {
		chatService.sendMsg(getChatMember(), targetMemberId, msgType, content);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "当前会话置为已读")
	@ResponseBody
	@RequestMapping(value="/chat/read", method=RequestMethod.POST)
	public CommonResult<String> read(Long sessionId) {
		chatService.readBySessionId(getChatMember(), sessionId);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "统计总未读数量")
	@ResponseBody
	@RequestMapping(value="/chat/countUnReadNum", method=RequestMethod.GET)
	public CommonResult<Long> countUnReadNum() {
		Long num = chatService.countUnReadNum(getChatMember().getId());
		return CommonResult.success(num);
	}

	private UmsMember getChatMember() {
		UmsMember member = new UmsMember();
		member.setId(CustomerConstant.ADMIN_MEMBER_ID);
		member.setUid(CustomerConstant.ADMIN_UID);
		member.setNickname(CustomerConstant.ADMIN_NICKNAME);
		member.setHeadImg(CustomerConstant.ADMIN_AVATAR);
		return member;
	}
}