package com.java8.algorithm.binarytree;

import lombok.Data;

@Data
public class TreeNode {
    public TreeNode(int data) {
        this.data = data;
    }

    public int data;
    public TreeNode leftChild;
    public TreeNode rightChild;
}
