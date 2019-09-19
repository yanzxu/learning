package com.java8.algorithm.binaryHeap;

import java.util.Arrays;

public class BinaryHeap {
    public static void main(String[] args) {
        BinaryHeap binaryHeap = new BinaryHeap();
        int[] arrays = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 6};

        System.err.println(Arrays.toString(binaryHeap.upAdjust(arrays)));;
    }

    public int[] upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (array.length - 1) / 2;
        int temp = array[childIndex];

        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }

        array[childIndex] = temp;

        return array;
    }

}
