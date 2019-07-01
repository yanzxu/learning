package com.java8.exception;

public class NumFormatException {
    public static void main(String[] args){
        if (args.length <1) {
            System.out.println("Please input:");
            return;
        }

        int i = Integer.parseInt(args[0]);
        System.out.println("i=: " + i);
    }
}