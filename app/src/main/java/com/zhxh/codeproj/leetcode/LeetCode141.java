package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode141 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node1 = new ListNode(3);
    }

    static class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> nodesSeen = new HashSet<>();
            while (head != null) {
                if (nodesSeen.contains(head)) {
                    return true;
                } else {
                    nodesSeen.add(head);
                }
                head = head.next;
            }
            return false;
        }
    }
}
