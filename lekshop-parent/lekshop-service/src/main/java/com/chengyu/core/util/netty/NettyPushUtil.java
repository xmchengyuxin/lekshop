package com.chengyu.core.util.netty;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.socket.SocketUtil;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.enums.MemberRemindEnums;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.netty.Command;
import com.chengyu.core.domain.netty.MessageBase;
import com.chengyu.core.model.ImChatLog;
import com.chengyu.core.utils.StringUtils;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @title  netty工具类
 * @author LeGreen
 * @date   2021/1/6
 */
@Service("nettyPushUtil")
public class NettyPushUtil {

	@Value("${netty.url}")
	private String URL;

	public void sendMsg(String msg){
		ThreadUtil.excAsync(() -> {
			try {
				MessageBase message = new MessageBase(null, Command.CommandType.PUSH_DATA, msg);
				Map<String,Object> params = BeanUtil.beanToMap(message);
				if(URL == null){
					URL = "http://127.0.0.1:8008";
				}
				HttpClientUtil.httpPostRequest(URL, params);
			} catch (Exception ignored) {

			}
		}, true);
	}

	public static void main1(String[] args) throws UnsupportedEncodingException {
		for(int i = 0; i<=1; i++){
			System.out.println(i);
			try {
				Map<String,String> extras = new HashMap<>(16);
				extras.put("reqId", StringUtils.genenrateInd());
				extras.put("rid", "member-4");
				extras.put("type", "1");
				extras.put("content", "「13666011848」申请了实名认证, 请尽快审批");
				extras.put("addTime", DateUtil.current(false)+"");

				MessageBase message = new MessageBase(null, Command.CommandType.PUSH_DATA, JSONUtil.toJsonStr(extras));
				Map<String,Object> params = BeanUtil.beanToMap(message);
				HttpClientUtil.httpPostRequest("http://127.0.0.1:8008", params);
				} catch (Exception ignored) {
				}
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		ImChatLog targetLog = new ImChatLog();
		targetLog.setSessionId(1L);
		targetLog.setMemberId(4);
		targetLog.setMemberNickname("LEKSHOP旗舰店");
		targetLog.setMemberHeadImg("https://qiniu.chengyuwb.com/1652321436403.png");
		targetLog.setTargetId(8);
		targetLog.setTargetNickname("yuwei21");
		targetLog.setTargetHeadImg("https://qiniu.chengyuwb.com/1653449194680.jpeg");
		targetLog.setSendType(2);
		targetLog.setMsgType("text");
		targetLog.setMsgContent("看到有求必应群在讨论中医AI开方，价格第一年3-5万");
		targetLog.setReadStatus(CommonConstant.NO_INT);
		targetLog.setSendStatus(CommonConstant.YES_INT);
		targetLog.setSendTime(DateUtil.date());
		targetLog.setUpdTime(targetLog.getSendTime());


		//通过netty发送
		Map<String,String> extras = new HashMap<>(16);
		extras.put("reqId", "99999");
		extras.put("rid", "member-"+targetLog.getMemberId().toString());
		extras.put("type", MemberRemindEnums.MemberRemindTypes.CHAT.getType().toString());
		extras.put("content", JSONUtil.toJsonStr(targetLog));
		extras.put("addTime", DateUtil.current(false)+"");
		MessageBase message = new MessageBase(null, Command.CommandType.PUSH_DATA, JSONUtil.toJsonStr(extras));
		Map<String,Object> params = BeanUtil.beanToMap(message);
		for(int i = 0; i <5; i++){
			HttpClientUtil.httpPostRequest("http://127.0.0.1:8008", params);
		}
	}
}
