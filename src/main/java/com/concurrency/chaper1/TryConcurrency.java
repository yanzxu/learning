package com.concurrency.chaper1;

public class TryConcurrency {

    public static void main(String[] args){
        new Thread("Read data"){
            @Override
            public void run() {
                readData();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread("write data"){
            @Override
            public void run() {
                writeData();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private static void readData(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("=== read data =====");
        }
    }

    private static void writeData(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("++++ write data ++++");
        }
    }
}
