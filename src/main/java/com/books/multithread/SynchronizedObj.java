package com.books.multithread;

public class SynchronizedObj {
    synchronized public void print(){
        System.out.println("== begin ==");
        System.out.println("=== thread is suspend ===");
        Thread.currentThread().suspend();
        System.out.println("=== end ===");
    }
}
