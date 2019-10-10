package com.books.multithread;

public class SyncTask {
    public void print() {
        try {
            for (int i = 0; i < 50; i++) {
                System.out.println("== " + Thread.currentThread().getName() + " i= " + i);
            }

            synchronized (this) {
                for (int i = 0; i < 50; i++) {
                    System.out.println("+++ " + Thread.currentThread().getName() + " i= " + i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
