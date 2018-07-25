package com.todorex.handler;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 初始化ChannelPipeline
 * @Author rex
 * 2018/7/25
 */
@Component
public class DataAcceptInitializer extends ChannelInitializer<SocketChannel> {
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder(Charset.forName("us-ascii"));

    @Autowired
    private ChannelInboundHandlerAdapter dataAcceptHandler;


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 设置编解码器
        pipeline.addLast(DECODER);
        pipeline.addLast(ENCODER);

        // 添加超时Handler(超时时间20s，可调)
        pipeline.addLast(new IdleStateHandler(0, 0, 20, TimeUnit.SECONDS));

        // 添加核心Handler
        pipeline.addLast(dataAcceptHandler);
    }
}
