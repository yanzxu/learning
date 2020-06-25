package com.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 客户端只需要一个时间循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

       try {
           // 客户端使用的是Bootstrap
           Bootstrap bootstrap = new Bootstrap();

           bootstrap.group(eventExecutors)
                   .channel(NioSocketChannel.class)
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel ch) throws Exception {
                           ch.pipeline().addLast(new NettyClientHandler());
                       }
                   });

           System.out.println("客户端 ok");

           //启动客户端连接服务器
           ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
           channelFuture.channel().closeFuture().sync();
       } finally {
           eventExecutors.shutdownGracefully();
       }
    }
}
