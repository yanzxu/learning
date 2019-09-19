package com.java8.algorithm.binarytree;

import java.util.*;

public class BinaryTree {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        LinkedList nodeList = new LinkedList<>(Arrays.asList(3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode treeNode = binaryTree.createTree(nodeList);

//        System.out.println("===== pre order ======");
//        preOrderTravel(treeNode);
//
//        System.out.println("===== in order ======");
//        inOrderTravel(treeNode);
//
//        System.out.println("===== post order ======");
//        postOrderTravel(treeNode);
//
//        System.out.println("===== without recursion ======");
//        preOrderWithoutRecursion(treeNode);

//        System.out.println("==== without recursion in order ====");
//        inOrderWithoutRecursion(treeNode);
//        System.out.println("==== without recursion post order ====");
//        postOrderWithoutRecursion(treeNode);

        printTreeByFloor(treeNode);
    }

    public static void printTreeByFloor(TreeNode node) {
        if (node == null) {
            return;
        }

        Deque<TreeNode> queue = new ArrayDeque();
        queue.addLast(node);

        while (!queue.isEmpty()){
            node = queue.removeFirst();
            System.out.println(node.getData());

            if (node.getLeftChild() != null)
                queue.addLast(node.getLeftChild());

            if (node.getRightChild() != null)
                queue.addLast(node.getRightChild());
        }
    }

    public static void preOrderWithoutRecursion(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.data);
                stack.push(node);
                node = node.getLeftChild();
            }

            if (!stack.isEmpty()) {
                TreeNode parentNode = stack.pop();
                node = parentNode.getRightChild();
            }
        }
    }

    public static void inOrderWithoutRecursion(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeftChild();
            }

            if (!stack.isEmpty()) {
                TreeNode leftNode = stack.pop();
                System.out.println(leftNode.data);

                node = leftNode.getRightChild();
            }
        }
    }

    public static void postOrderWithoutRecursion(TreeNode node) {
        if (node == null)
            return;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode curNode = node;
        TreeNode lastVisitNode = null;

        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.getLeftChild();
        }

        while (!stack.empty()) {
            curNode = stack.pop();
            if (curNode.getRightChild() != null && curNode.getRightChild() != lastVisitNode) {
                stack.push(curNode);
                curNode = curNode.getRightChild();
                while (curNode != null) {
                    stack.push(curNode);
                    curNode = curNode.getLeftChild();
                }
            } else {
                System.out.println(curNode.getData());
                lastVisitNode = curNode;
            }
        }
    }


    public TreeNode createTree(LinkedList<Integer> inputList) {
        TreeNode treeNode = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();

        if (data != null) {
            treeNode = new TreeNode(data);
            treeNode.leftChild = createTree(inputList);
            treeNode.rightChild = createTree(inputList);
        }

        return treeNode;
    }

    public static void preOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        preOrderTravel(node.leftChild);
        preOrderTravel(node.rightChild);
    }

    public static void inOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrderTravel(node.leftChild);
        System.out.println(node.data);
        inOrderTravel(node.rightChild);
    }

    public static void postOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrderTravel(node.leftChild);
        postOrderTravel(node.rightChild);
        System.out.println(node.data);
    }
}
