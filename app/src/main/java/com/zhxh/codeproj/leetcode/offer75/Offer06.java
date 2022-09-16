package com.zhxh.codeproj.leetcode.offer75;

import com.zhxh.codeproj.leetcode.__base.ListNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
剑指 Offer 06. 从尾到头打印链表
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]

限制：

0 <= 链表长度 <= 10000

 */
public class Offer06 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().reversePrint(ListNode.buildNode(new int[]{1, 3, 2}))));
    }

    /*
    自己编写
     */
    static class Solution {
        public int[] reversePrint(ListNode head) {
            Deque<Integer> stack = new LinkedList<>();
            while (head != null) {
                stack.push(head.val);
                head = head.next;
            }
            int[] arr = new int[stack.size()];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = stack.pop();
            }
            return arr;
        }
    }
}
