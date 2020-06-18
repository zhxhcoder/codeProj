package com.zhxh.codeproj.leetcode.support;

/**
 * Created by zhxh on 2020/6/18
 */
public class ListNode {
    public int value;
    public ListNode next;

    public boolean hasNext() {
        return next != null;
    }

    public ListNode(int x) {
        value = x;
        next = null;
    }
}
