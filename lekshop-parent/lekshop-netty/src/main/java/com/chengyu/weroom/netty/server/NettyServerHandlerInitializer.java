package com.chengyu.weroom.netty.server;

import com.chengyu.weroom.netty.common.MessageBase;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) {
        /*ch.pipeline()
                //空闲检测
                .addLast(new ServerIdleStateHandler())
                *//*.addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(MessageBase.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())*//*
                .addLast(new DelimiterBasedFrameDecoder(1024 * 1024, Delimiters.lineDelimiter()))
                .addLast(stringDecoder)
                .addLast(stringEncoder)
                .addLast(new NettyServerHandler());*/

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new ServerIdleStateHandler());
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ChunkedWriteHandler());

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new NettyServerHandler());
    }
}
