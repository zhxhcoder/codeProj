package com.zhxh.codeproj.leetcode.__base;

/**
 * Created by zhxh on 2020/6/18
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode newHead = null;

        // 不断取出和向后移动头节点
        // 并将头节点连接到新头节点后面
        while (head != null) {
            // 单独取出下一个节点
            ListNode next = head.next;
            // 将头节点连接到新头节点后面
            head.next = newHead;
            newHead = head;
            // 向后移动头节点
            head = next;
        }
        return newHead;
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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
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
