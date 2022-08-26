package com.zhxh.codeproj.leetcode.__base;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public boolean hasNext() {
        return next != null;
    }

    //以下为静态方法
    public static void printNode(Node head) {
        System.out.print("->" + head.val);
        if (head.hasNext()) {
            printNode(head.next);
        } else {
            System.out.print("\n");
        }
    }
}
