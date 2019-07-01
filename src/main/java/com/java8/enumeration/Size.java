package com.java8.enumeration;

public enum Size {
    SMALL("aa", 0),
    MEDIUM("bb", 1),
    LARGGE("cc", 2);

    String name;
    int ordianl;

    Size(String name, int ordinal){
        this.name =name;
        this.ordianl = ordinal;
    }

    String valueOf(Size size){
        return size.name;
    }
}
