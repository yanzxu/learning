package com.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端启动了");

        while (true){
            System.out.println("线程信息 id= " + Thread.currentThread().getId() + "  线程名字 name= " + Thread.currentThread().getName());
            System.out.println("等待连接。。。。。。");

            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handle(socket);
                }
            });

        }


    }

    private static void handle(Socket socket) {
       try{
           System.out.println("线程信息 id= " + Thread.currentThread().getId() + "  线程名字 name= " + Thread.currentThread().getName());

           byte[] bytes = new byte[1024];
           InputStream inputStream = socket.getInputStream();

           while (true){
               System.out.println("线程信息 id= " + Thread.currentThread().getId() + "  线程名字 name= " + Thread.currentThread().getName());
               System.out.println("read ....");

               int read = inputStream.read(bytes);
               if (read != -1){
                   System.out.println(new String(bytes));
               }else {
                   System.out.println("结束while循环");
                   break;
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           System.out.println("关闭socker连接");
           try {
               socket.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
}
