package com.chengyu.core.netty.server;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author LeGreen
 * @date 2021/12/27
 */
public class RpcEncoder extends MessageToByteEncoder {

    //目标对象类型进行编码
    private Class<?> target;

    public RpcEncoder(Class target) {
        this.target = target;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (target.isInstance(msg)) {
            byte[] data = JSON.toJSONBytes(msg); //使用fastJson将对象转换为byte
            out.writeInt(data.length); //先将消息长度写入，也就是消息头
            out.writeBytes(data); //消息体中包含我们要发送的数据
        }
    }
}
