package com.zhxh.codeproj.javatest.javatest5;

import com.zhxh.codeproj.leetcode.support.ListNode;

/**
 * Created by zhxh on 2019/4/4
 */
public class JavaTest5 {

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

    public static void printNodeList(ListNode node) {
        if (node != null) {
            System.out.println(node.val);
            while (node.hasNext()) {
                System.out.println(node.next.val);
                node = node.next;
            }
        }
        System.out.println("---------------------");
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode root = new ListNode(0); // 头结点
        ListNode r = root;
        root.next = l1;

        int carry = 0; // 初始进位
        int sum;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            p1.val = sum % 10; // 本位的结果
            carry = sum / 10; // 本次进位

            r.next = p1;
            r = p1; // 指向最后一个相加的结点
            p1 = p1.next;
            p2 = p2.next;
        }

        if (p1 == null) {
            r.next = p2;
        } else {
            r.next = p1;
        }

        // 最后一次相加还有进位
        if (carry == 1) {
            // 开始时r.next是第一个要相加的结点
            while (r.next != null) {
                sum = r.next.val + carry;
                r.next.val = sum % 10;
                carry = sum / 10;
                r = r.next;
            }
            // 都加完了还有进位，就要创建一个新的结点
            if (carry == 1) {
                r.next = new ListNode(1);
            }
        }

        return root.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node3;
        node3.next = node4;

        printNodeList(node1);

        ListNode node2 = new ListNode(2);
        ListNode node5 = new ListNode(5);

        node2.next = node5;

        printNodeList(node2);

        printNodeList(addTwoNumbers(node1, node2));

        ListNode node = mergeTwoLists(node1, node2);
        printNodeList(node);
        printNodeList(reverseList(node));

    }
}
