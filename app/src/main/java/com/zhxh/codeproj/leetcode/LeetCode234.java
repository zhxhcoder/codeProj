package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode234 {
    public static void main(String[] args) {

    }

    class Solution {
        public boolean isPalindrome(ListNode head) {
            List<Integer> vals = new ArrayList<>();

            // 转换 LinkedList 为 ArrayList.
            ListNode currentNode = head;
            while (currentNode != null) {
                vals.add(currentNode.value);
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
