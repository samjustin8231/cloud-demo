package com.example.nettydemo.discard_server;

import com.example.nettydemo.discard_server.DiscardServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author sunyajun
 * @date 2022/8/8 上午12:07
 */
@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Resource
    private DiscardServerHandler discardServerHandler;

    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("监听到 accept 事件, initChannel ...");
        socketChannel.pipeline().addLast(discardServerHandler);
    }
}

