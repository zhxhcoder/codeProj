package com.zhxh.codeproj.algorithm;

public class ReverseLinkedList {

    class Node {
        int val;
        Node next;

        Node(int x) {
            val = x;
            next = null;
        }
    }

    // 1.就地反转法
    /*
     把当前链表的下一个节点pCur插入到头结点dummy的下一个节点中，就地反转。

     dummy->1->2->3->4->5的就地反转过程：

     dummy->2->1->3->4->5
     dummy->3->2->1->4->5
     dummy->4>-3->2->1->5
     dummy->5->4->3->2->1
     */
    public Node reverseList1(Node head) {
        if (head == null)
            return head;
        Node dummy = new Node(-1);
        dummy.next = head;
        Node prev = dummy.next;
        Node pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;
    }

    // 2.新建链表,头节点插入法
    //新建一个头结点，遍历原链表，把每个节点用头结点插入到新建链表中。最后，新建的链表就是反转后的链表。
    public Node reverseList2(Node head) {
        Node dummy = new Node(-1);
        Node pCur = head;
        while (pCur != null) {
            Node pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;
    }
}
