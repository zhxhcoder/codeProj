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

    }

    class Solution {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<>();

            // 转换 LinkedList 为 ArrayList.
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.val);
                currentNode = currentNode.next;
            }

            // 双指针发
            int front = 0;
            int back = vals.size() - 1;
            while (front < back) {
                // 注意是用 ! .equals 而不是 !=
                // 因为我们比较的是Integer,而不是int.
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
