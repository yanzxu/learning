package com.books.multithread;

public class TestThread {
    public static void main(String[] args) {
        try {
            Object o = new Object();

            MyThread thread1 = new MyThread(o);
            thread1.start();

            Thread.sleep(3000);

            MyThread2 thread2 = new MyThread2(o);
            thread2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
