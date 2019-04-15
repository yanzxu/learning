package com.spring.reflection;

public class TestJDKClassLoader {
    public static void main(String[] args) {
        // String是JDK的一个核心类，String的classLoader就属于bootstrap classoader，该classLoader没有名字
        System.out.println("String类的classLoad：" + String.class.getClassLoader() + "\n");

        // HmacMD5是jre/lib/ext下的一个工具类，它的classLoader属于extension classLoader
        System.out.println("HmacMD5类的classLoad：" + com.sun.crypto.provider.HmacMD5.class.getClassLoader() + "\n");

        // 当前类是一个自己编写的应用程序诶=类，它的classLoader属于 application classLoader
        System.out.println("当前类类的classLoad：" + TestJDKClassLoader.class.getClassLoader() + "\n");
        System.out.println("系统classLoader：" + ClassLoader.getSystemClassLoader());

    }
}
