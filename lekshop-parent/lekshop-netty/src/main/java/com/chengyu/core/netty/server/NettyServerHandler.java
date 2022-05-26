package com.chengyu.core.netty.server;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.chengyu.core.netty.common.Command;
import com.chengyu.core.netty.common.MessageBase;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @类描述   	逻辑处理Handler
 * @作者   		LeGreen
 * @创建时间  	2020年8月12日
 * @版本   		1.00
 * @修改记录
 * <pre>
 *     版本          修改人    		修改日期 			修改内容描述
 *     -----------------------------------------------------------
 *     1.00   	LeGreen    	2020年8月12日
 *     -----------------------------------------------------------
 * </pre>
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	private final AttributeKey<String> clientInfo = AttributeKey.valueOf("clientInfo");
	private final static Map<String, Channel> channelCache = new ConcurrentHashMap<String, Channel>();
//	private final static List<String> reqIdCache = new ArrayList<>();

    @SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			MessageBase msgBase = null;
			Content c = null;
			if (msg instanceof WebSocketFrame) {
				TextWebSocketFrame text = (TextWebSocketFrame) msg;
				msgBase = JSONUtil.toBean(text.text(), MessageBase.class);
			}else if (msg instanceof FullHttpRequest) {
				FullHttpRequest req  = (FullHttpRequest) msg;
				Map<String,Object> params = this.getRequestParams(req);
				msgBase = BeanUtil.mapToBean(params, MessageBase.class, true);
				// 接收到Http请求后优先返回内容
				// 1.获取URI
				String uri = req.uri();
				// 2.获取请求体
				ByteBuf buf = req.content();
				String content = buf.toString(CharsetUtil.UTF_8);
				c = new Content();
				c.setUri(uri);
				c.setContent(content);
			}

    		String clientId = msgBase.getClientId();
			if(!StringUtils.isEmpty(clientId)){
				Channel ch = channelCache.get(clientId);
				if(null == ch){
					ch = ctx.channel();
					channelCache.put(clientId, ch);
				}
			}

    		if(msgBase.getCmd() == Command.CommandType.AUTH.value){
				log.info("client auth>>> "+clientId);
				Attribute<String> attr = ctx.attr(clientInfo);
				attr.set(clientId);
				channelCache.put(clientId, ctx.channel());

				ctx.writeAndFlush(createData(clientId, Command.CommandType.AUTH_BACK, "auth_back>>> auth success"));

			}else if(msgBase.getCmd() == Command.CommandType.PING.value){
				//处理ping消息
				ctx.writeAndFlush(createData(clientId, Command.CommandType.PONG, "pong>>> This is pong data"));
			}else{
				if(JSONUtil.isJson(msgBase.getData())){
					Map<String, String> map = JSONUtil.toBean(msgBase.getData(), Map.class);
					String rid = MapUtil.getStr(map, "rid");

					if(!StringUtils.isEmpty(rid)){
						response(ctx, c);

						Channel otherCh = channelCache.get(rid);
						if(null == otherCh){
							log.info("client is not found!");
							return;
						}
						log.info("push_data>>> "+rid+" >>> "+msgBase.getData());
						/*//为防止重复推送, 暂时先缓存
						String reqId = MapUtil.getStr(map, "reqId");
						if(!StringUtils.isEmpty(reqId)){
							if(reqIdCache.contains(reqId)){
								return;
							}else{
								reqIdCache.add(reqId);q
							}
						}*/
						otherCh.writeAndFlush(
								createData(rid, Command.CommandType.PUSH_DATA, msgBase.getData())
							);
					}
				}
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
    }

	private TextWebSocketFrame createData(String clientId, Command.CommandType cmd, String data){
		MessageBase msg = new MessageBase();
		msg.setClientId(clientId);
		msg.setCmd(cmd.value);
		msg.setData(data);
		return new TextWebSocketFrame(JSONUtil.toJsonStr(msg));
	}

	private Map<String, Object> getRequestParams(FullHttpRequest request) {
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
		List<InterfaceHttpData> httpPostData = decoder.getBodyHttpDatas();
		Map<String, Object> params = new HashMap<>();

		for (InterfaceHttpData data : httpPostData) {
			if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
				MemoryAttribute attribute = (MemoryAttribute) data;
				params.put(attribute.getName(), attribute.getValue());
			}
		}
		return params;
	}

	private void response(ChannelHandlerContext ctx, Content c) {

		// 1.设置响应
		FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK,
				Unpooled.copiedBuffer(JSONObject.toJSONString(c), CharsetUtil.UTF_8));

		resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

		// 2.发送
		// 注意必须在使用完之后，close channel
		ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
	}

	@Data
	class Content{
		String uri;
		String content;
	}
}
