package com.spring.thread;

public class TestThread {
    public static void main(String[] args){
        Thread myThread = new MyThread();
        myThread.start();
        myThread.run();
    }
}
