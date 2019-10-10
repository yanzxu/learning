package com.leetcode.easy;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Num1108 {
    public static void main(String[] args) {
        String addr = "1.1.1.1";
        System.out.println(new Num1108().defangIpAddr(addr));
    }

    public String defangIpAddr(String addr){
        return Arrays.asList(addr.split("\\.")).stream().collect(Collectors.joining("[.]"));
    }


}
