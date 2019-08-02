package com.concurrency.chaper1;


public class TicketWindowRunnable implements Runnable {
    private static final int MAX = 50;
    private static int index =1;


    @Override
    public void run() {
        while (index <= MAX){
            System.out.println(Thread.currentThread().getName() + "当前票号是：" + (index++));
        }
    }
}
