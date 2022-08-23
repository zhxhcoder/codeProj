package com.zhxh.codeproj.leetcode.day7;

import com.zhxh.codeproj.leetcode._base.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

 */
public class LeetCode234 {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(ListNode.buildNode(new int[]{1, 2, 2, 1})));
        System.out.println(new Solution2().isPalindrome(ListNode.buildNode(new int[]{1, 2, 2, 1})));
        System.out.println(new Solution3().isPalindrome(ListNode.buildNode(new int[]{1, 2, 2, 1})));
    }

    /*
    将值复制到数组中后用双指针法
     */
    static class Solution {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<Integer>();

            // 将链表的值复制到数组中
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.val);
                currentNode = currentNode.next;
            }

            // 使用双指针判断是否回文
            int front = 0;
            int back = vals.size() - 1;
            while (front < back) {
                if (!vals.get(front).equals(vals.get(back))) {
                    return false;
                }
                front++;
                back--;
            }
            return true;
        }
    }

    /*
    递归
     */
    static class Solution2 {
        private ListNode frontPointer;

        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) {
                    return false;
                }
                if (currentNode.val != frontPointer.val) {
                    return false;
                }
                frontPointer = frontPointer.next;
            }
            return true;
        }

        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }
    }

    /*
    快慢指针
     */
    static class Solution3 {
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }
            // 找到前半部分链表的尾节点并反转后半部分链表
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // 判断是否回文
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) {
                    result = false;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
            // 还原链表并返回结果
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        private ListNode reverseList(ListNode head) {
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

        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }
}
