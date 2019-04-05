package com.zhxh.codeproj.java.javatest5;

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
                node=node.next;
            }
        }
        System.out.println("---------------------");

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

        ListNode node = mergeTwoLists(node1, node2);

        printNodeList(node);




    }


}
