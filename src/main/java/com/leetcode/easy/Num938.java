package com.leetcode.easy;

import com.leetcode.TreeNode;

import java.util.Stack;

public class Num938 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);
        TreeNode node6 = new TreeNode(18);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node6;

        System.out.println(rangeSumBST(node, 7, 15));
    }

    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null){
            return 0;
        }

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }
    }


    public static int inOrderWithoutRecursion(TreeNode node, int L, int R) {
        int sum = 0;

        if (node == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                TreeNode leftNode = stack.pop();

                if (leftNode.val > R) {
                    break;
                }
                if (leftNode.val >= L) {
                    sum += leftNode.val;
                }


                node = leftNode.right;
            }
        }

        return sum;
    }
}
