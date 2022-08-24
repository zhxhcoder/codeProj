package com.zhxh.codeproj.leetcode.day6;

import com.zhxh.codeproj.leetcode._base.ListNode;

/*
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。


示例 1：


输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
示例 2：


输入：head = [1,2]
输出：[2,1]
示例 3：

输入：head = []
输出：[]


提示：

链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000


进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？

 */
public class LeetCode206 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().reverseList(ListNode.buildNode(new int[]{1, 2, 3, 4, 5})));
        ListNode.printNode(new Solution2().reverseList(ListNode.buildNode(new int[]{1, 2, 3, 4, 5})));
    }

    /*
    迭代
     */
    static class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return p;
        }
    }

    /*
    循环
     */
    static class Solution2 {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }
    }
}
