package com.chengyu.core.util.netty;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.socket.SocketUtil;
import com.chengyu.core.domain.enums.RedisEnums;
import com.chengyu.core.domain.netty.Command;
import com.chengyu.core.domain.netty.MessageBase;
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

	public static void main(String[] args) throws UnsupportedEncodingException {
		for(int i = 0; i<=1; i++){
			System.out.println(i);
			try {
				Map<String,String> extras = new HashMap<>(16);
				extras.put("reqId", StringUtils.genenrateInd());
				extras.put("rid", "shop-4");
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
}
