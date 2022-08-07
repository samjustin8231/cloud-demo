package com.example.nettydemo.discard_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author sunyajun
 * @date 2022/8/8 上午12:07
 */

@Component
public class DiscardServer {

    @Resource
    private ChildChannelHandler childChannelHandler;

    public void run(int port) throws Exception {
        System.out.println("start server ...");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口：" + port);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(childChannelHandler);

            //绑定端口，同步等待成功
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("bind port success ...");

            //等待服务监听端口关闭
            System.out.println("wait client connect ...");
            f.channel().closeFuture().sync();

        } finally {
            //退出，释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

