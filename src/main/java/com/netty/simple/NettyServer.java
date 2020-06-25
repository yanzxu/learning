package com.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args)  {

          // 创建BossGroup和WorkerGroup
          NioEventLoopGroup bossGroup = new NioEventLoopGroup();//只处理连接请求，真正的和客户端业务处理在workerGroup
          NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
          // 创建服务端的启动对象，配置参数
          ServerBootstrap bootstrap = new ServerBootstrap();
          bootstrap.group(bossGroup, workerGroup).
                  channel(NioServerSocketChannel.class)
                  .option(ChannelOption.SO_BACKLOG,128)
                  .childOption(ChannelOption.SO_KEEPALIVE,true)
                  .childHandler(new ChannelInitializer<SocketChannel>() {
                      @Override
                      protected void initChannel(SocketChannel ch) throws Exception {
                          // 给pipeline设置处理器
                          ch.pipeline().addLast(new NettyServerHandler());
                      }
                  });

          System.out.println("....... 服务器 is ready ........");

          // 绑定端口并同步，启动服务器
          ChannelFuture channelFuture = bootstrap.bind(6668).sync();
          // 对关闭通道进行监听
          channelFuture.channel().closeFuture().sync();

      } catch (Exception e){
        e.printStackTrace();
      } finally {
          bossGroup.shutdownGracefully();
          workerGroup.shutdownGracefully();
      }
    }
}
