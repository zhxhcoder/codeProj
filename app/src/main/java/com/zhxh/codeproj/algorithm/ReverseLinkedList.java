package com.zhxh.codeproj.algorithm;

public class ReverseLinkedList {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 1.就地反转法
    public ListNode reverseList1(ListNode head) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;
    }

    // 2.新建链表,头节点插入法
    public ListNode reverseList2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pCur = head;
        while (pCur != null) {
            ListNode pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;
    }
}
