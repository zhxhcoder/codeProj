package com.zhxh.codeproj.java.javatest5;

/**
 * Created by zhxh on 2019/4/4
 */
public class ListNode {
    int val;
    ListNode next;

    boolean hasNext() {
        return next != null;
    }

    ListNode(int x) {
        val = x;
    }
}
