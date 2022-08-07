package com.example.nettydemo;

import com.example.nettydemo.service.BaseService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author sunyajun
 * @date 2022/8/8 上午12:08
 */
@Component
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelHandlerAdapter {

    @Resource
    private BaseService baseService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("=====> channelRead start");
        try {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("传输内容是");
            System.out.println(in.toString(CharsetUtil.UTF_8));

            //这里调用service服务
            baseService.test();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }
}

