package com.zhxh.codeproj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode234 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<>();

            // Convert LinkedList into ArrayList.
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.val);
                currentNode = currentNode.next;
            }

            // Use two-pointer technique to check for palindrome.
            int front = 0;
            int back = vals.size() - 1;
            while (front < back) {
                // Note that we must use ! .equals instead of !=
                // because we are comparing Integer, not int.
                if (!vals.get(front).equals(vals.get(back))) {
                    return false;
                }
                front++;
                back--;
            }
            return true;
        }
    }
}
