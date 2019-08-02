package com.concurrency.chaper1;

public class Bank {
    public static void main(String[] args){
        TicketWindow window1 = new TicketWindow("柜台一");
        window1.start();

        TicketWindow window2 = new TicketWindow("柜台二");
        window2.start();

        TicketWindow window3 = new TicketWindow("柜台三");
        window3.start();
    }
}
