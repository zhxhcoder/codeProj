package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.ListNode;

//两个相加
public class LeetCode02 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 8, 15};
        int target = 17;
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode p, ListNode q) {
            ListNode dummyHead = new ListNode(0);
            ListNode curr = dummyHead;
            int carry = 0;
            while (p != null || q != null) {
                int x = (p != null) ? p.value : 0;
                int y = (q != null) ? q.value : 0;
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
