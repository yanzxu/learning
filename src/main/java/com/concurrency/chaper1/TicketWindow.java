package com.concurrency.chaper1;

public class TicketWindow extends Thread {
    private static final int MAX = 50;
    private String name;

    private static int index = 1;

    public TicketWindow(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("=== 当前柜台名字是：" + name + "， 票号是：" + (index++) +" ===");
        }
    }
}
