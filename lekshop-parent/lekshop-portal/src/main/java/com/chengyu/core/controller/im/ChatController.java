package com.chengyu.core.controller.im;

import cn.hutool.core.collection.CollectionUtil;
import com.chengyu.core.controller.UserBaseController;
import com.chengyu.core.domain.result.CustomerConstatnt;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.ImChatLog;
import com.chengyu.core.model.ImChatSession;
import com.chengyu.core.model.UmsMember;
import com.chengyu.core.service.im.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "聊天")
@Controller
@RequestMapping("/member")
public class ChatController extends UserBaseController {
	
	@Autowired
	private ChatService chatService;

	@ApiOperation(value = "获取人工客服和智能客服的ID")
	@ResponseBody
	@RequestMapping(value="/chat/getCustomerId", method=RequestMethod.GET)
	public CommonResult<Map<String,Object>> getCustomerId() {
		Map<String,Object> result = new HashMap<>();
		result.put("customerMemberId", CustomerConstatnt.MEMBER_ID);
		result.put("customerAdminId", CustomerConstatnt.ADMIN_MEMBER_ID);
		return CommonResult.success(result);
	}

	@ApiOperation(value = "初始化聊天")
	@ResponseBody
	@RequestMapping(value="/chatSession/init", method=RequestMethod.POST)
	public CommonResult<Long> init(Integer memberId) throws ServiceException {
		UmsMember targetMember = memberService.getMemberById(memberId);
		Long sessionId = chatService.initChatSession(getCurrentMember(), targetMember);
		return CommonResult.success(sessionId);
	}

	@ApiOperation(value = "会话列表")
	@ResponseBody
	@RequestMapping(value="/chatSession/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ImChatSession>> getSessionList(Integer page, Integer pageSize) throws ServiceException {
		List<ImChatSession> list = chatService.getChatSessionList(getCurrentMemberId(), page, pageSize);
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "删除聊天会话")
	@ResponseBody
	@RequestMapping(value="/chatSession/delete", method=RequestMethod.POST)
	public CommonResult<String> delete(Long sessionId) throws ServiceException {
		chatService.deleteChatSession(getCurrentMember(), sessionId);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "获取聊天记录")
	@ResponseBody
	@RequestMapping(value="/chat/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ImChatLog>> getSessionList(Integer targetMemberId, Integer page, Integer pageSize) throws ServiceException {
		List<ImChatLog> list = chatService.getChatLogList(getCurrentMemberId(), targetMemberId, page, pageSize);
		return CommonResult.success(CommonPage.restPage(CollectionUtil.reverse(list)));
	}

	@ApiOperation(value = "发消息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "targetMemberId", value = "接收人ID"),
		@ApiImplicitParam(name = "msgType", value = "text,image,file"),
		@ApiImplicitParam(name = "content", value = "内容")
	})
	@ResponseBody
	@RequestMapping(value="/chat/send", method=RequestMethod.POST)
	public CommonResult<String> send(Integer targetMemberId, String msgType, String content) throws ServiceException {
		chatService.sendMsg(getCurrentMember(), targetMemberId, msgType, content);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "当前会话置为已读")
	@ResponseBody
	@RequestMapping(value="/chat/read", method=RequestMethod.POST)
	public CommonResult<String> read(Long sessionId) throws ServiceException {
		chatService.readBySessionId(getCurrentMember(), sessionId);
		return CommonResult.success(null);
	}

	@ApiOperation(value = "统计总未读数量")
	@ResponseBody
	@RequestMapping(value="/chat/countUnReadNum", method=RequestMethod.GET)
	public CommonResult<Long> countUnReadNum() throws ServiceException {
		Long num = chatService.countUnReadNum(getCurrentMemberId());
		return CommonResult.success(num);
	}
}