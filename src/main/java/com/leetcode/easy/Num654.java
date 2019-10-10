package com.leetcode.easy;


import java.util.Arrays;

public class Num654 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};

        constructMaximumBinaryTree(nums);
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        int max = 0;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        TreeNode node = new TreeNode(max);
        node.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, index));
        node.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, index + 1, nums.length));

        return node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
