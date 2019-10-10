package com.leetcode.hard;

import com.leetcode.TreeNode;

public class Num124 {
    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
;
        node.left = node1;
        node.right = node2;

        Num124 num124 = new Num124();

        System.out.println(num124.maxPathSum(node));
    }

    public  int maxPathSum(TreeNode root) {
        getPathSum(root);
        return max;
    }

    private  Integer getPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Integer leftSum = getPathSum(node.left);
        Integer rightSum = getPathSum(node.right);

        Integer maxWithLeftAndRight = node.val + Math.max(0, leftSum) + Math.max(0, rightSum);
        Integer maxWithOneNode = node.val + Math.max(0, Math.max(leftSum, rightSum));

        max = Math.max(max, Math.max(maxWithLeftAndRight, maxWithOneNode));

        return maxWithOneNode;
    }
}
