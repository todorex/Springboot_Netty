package com.todorex.handler;

import com.todorex.service.UserService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据接受Handler
 * @Author rex
 * 2018/7/25
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class DataAcceptHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.fireChannelActive();
        if (log.isDebugEnabled()) {
            log.debug(ctx.channel().remoteAddress() + "");
        }
        ctx.writeAndFlush("Your channel is build" +"\r\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        String stringMessage = (String) msg;
        if (log.isDebugEnabled()) {
            log.debug(stringMessage);
        }
        // 去回车和换行，也可以添加换行解码器(DelimiterBasedFrameDecoder(1024*1024, Delimiters.lineDelimiter())
        String[] splitMessage = stringMessage.replaceAll("\r|\n*","").split("::");

        if ( splitMessage.length != 2 ) {
            ctx.writeAndFlush(stringMessage + "\n\r");
            return;
        } else {
            // 保存用户
            int id = userService.saveUser(splitMessage);
            ctx.writeAndFlush(Unpooled.copiedBuffer("OK, UserId=" + id, CharsetUtil.UTF_8));
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        log.debug(ctx.channel().remoteAddress() + "inactive");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            log.error("timeout");
            ctx.channel().close();
        }
    }
}
