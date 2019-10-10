package com.books.multithread;

public class MyThread extends Thread {
    private Object lock;

    public MyThread(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println(" start wait, time+ " + System.currentTimeMillis());
                lock.wait();
                System.out.println(" end wait time= " + System.currentTimeMillis());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
