package com.example.nettydemo.discard_server;

import com.example.nettydemo.service.BaseService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author sunyajun
 * @date 2022/8/8 上午12:08
 */
@Component
@ChannelHandler.Sharable
@Slf4j
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private BaseService baseService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("=====> 监听到读事件...");
        try {
            ByteBuf in = (ByteBuf) msg;
            log.info("传输内容是");
            log.info(in.toString(CharsetUtil.UTF_8));

            //这里调用service服务
            baseService.test();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}

