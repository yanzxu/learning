package com.concurrency.chaper1;

public class BankVersion2 {
    public static void main(String[] args) {
        Thread window1 = new Thread(new TicketWindowRunnable(), "一号窗口");
        Thread window2 = new Thread(new TicketWindowRunnable(), "二号窗口");
        Thread window3 = new Thread(new TicketWindowRunnable(), "三号窗口");

        window1.start();
        window2.start();
        window3.start();
    }
}
