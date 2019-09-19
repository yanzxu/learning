package com.leetcode;

public class ReverseNumber {
    public static void main(String[] args) {
        int reverse = reverse(2147483647);

        System.out.println("======  "+ reverse +  "=======");


    }
    private static int reverse (int x){
        String s = String.valueOf(x);

        if (s.startsWith("-")) {
            String substring = s.substring(1);
             String reverse = "-" + new StringBuffer(substring).reverse().toString();
            return Integer.parseInt(reverse);
        }else {
            String reverse = new StringBuffer(s).reverse().toString();
            return Integer.parseInt(reverse);
        }
    }
}
