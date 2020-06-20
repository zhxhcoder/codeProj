package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.ListNode;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

 */
public class LeetCode02 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode node1 = ListNode.buildNode(new int[]{2, 4, 3});
        ListNode node2 = ListNode.buildNode(new int[]{5, 6, 4});

        ListNode.printNode(node1);
        ListNode.printNode(node2);

        ListNode.printNode(solution.addTwoNumbers(node1, node2));
    }

    /*
a. 两条链表相加，返回的结果要求是链表，每次插值的时候都要在链表的尾端添加。
需要一个返回结果的链表res，需要一个指针，每次添加后都指向最后一个节点。
b. 如果有进数(两数想和大于十进1)，下次相加时一起加上。
c. 两条链表长度可能不相等，因此结束循环后，需要判断是否有未遍历完的链表，有则单独遍历，没有则进入下一步。
d. 当两条链表都遍历结束，还需要判断进数是否为0，为0什么也不做，不为0在运行结果的链表尾部添加值为1的节点。
c. 最后可以直接返回res链表

     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode p, ListNode q) {
            ListNode dummyHead = new ListNode(0);
            ListNode curr = dummyHead;
            int carry = 0;
            while (p != null || q != null) {
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (p != null) p = p.next;
                if (q != null) q = q.next;
            }
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;
        }
    }
}
