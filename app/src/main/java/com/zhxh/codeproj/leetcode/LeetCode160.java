package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.ListNode;

public class LeetCode160 {

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;
            ListNode pA = headA, pB = headB;
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }
            return pA;
        }
    }
}
