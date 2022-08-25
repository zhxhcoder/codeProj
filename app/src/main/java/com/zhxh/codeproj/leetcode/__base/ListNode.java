package com.zhxh.codeproj.leetcode.__base;

/**
 * Created by zhxh on 2020/6/18
 */
public class ListNode {
    public int val;
    public ListNode next;

    public boolean hasNext() {
        return next != null;
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }


    //以下为静态方法
    public static void printNode(ListNode head) {
        System.out.print("->" + head.val);
        if (head.hasNext()) {
            printNode(head.next);
        } else {
            System.out.print("\n");
        }
    }


    public static ListNode buildNode(int[] values) {
        if (values.length == 0) {
            return null;
        }
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new ListNode(values[i]);
        }
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        return nodes[0];
    }


    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    //以下为静态方法 addNote

    //插入curr后面
    public static ListNode inertNode(ListNode curr, int val) {
        ListNode last = new ListNode(val);
        if (curr == null) {
            return last;
        }
        //插入到尾部
        if (curr.next == null) {
            curr.next = last;
            return curr;
        } else {
            curr.next = last;
            last.next = curr.next;
            return curr;
        }
    }

}