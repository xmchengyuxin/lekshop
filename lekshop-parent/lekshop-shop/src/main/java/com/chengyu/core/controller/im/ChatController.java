package com.chengyu.core.controller.im;

import com.chengyu.core.controller.ShopBaseController;
import com.chengyu.core.domain.form.AccountSearchForm;
import com.chengyu.core.entity.CommonPage;
import com.chengyu.core.entity.CommonResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.ImChatLog;
import com.chengyu.core.model.ImChatSession;
import com.chengyu.core.service.im.ChatService;
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
@RequestMapping("/member")
public class ChatController extends ShopBaseController {
	
	@Autowired
	private ChatService chatService;

	@ApiOperation(value = "会话列表")
	@ResponseBody
	@RequestMapping(value="/chatSession/getList", method=RequestMethod.GET)
	public CommonResult<CommonPage<ImChatSession>> getSessionList(AccountSearchForm form, Integer page, Integer pageSize) throws ServiceException {
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
		return CommonResult.success(CommonPage.restPage(list));
	}

	@ApiOperation(value = "发消息")
	@ResponseBody
	@RequestMapping(value="/chat/send", method=RequestMethod.POST)
	public CommonResult<String> send(Integer targetMemberId, Integer msgType, String content) throws ServiceException {
		chatService.sendMsg(getCurrentMember(), targetMemberId, msgType, content);
		return CommonResult.success(null);
	}

}