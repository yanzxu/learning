package com.mars.enums;

public enum  Direction {
    // 东
    E("E","N","W"),
    // 西
    S("S","W","N"),
    // 南
    W("W","E","S"),
    // 北
    N("N","S","E");

    String value;
    String leftDir;
    String rightDir;

    Direction(String value, String left, String right){
        this.value = value;
        this.leftDir = left;
        this.rightDir = right;
    }


}
