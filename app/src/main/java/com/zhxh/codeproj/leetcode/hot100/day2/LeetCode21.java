package com.zhxh.codeproj.leetcode.hot100.day2;

import com.zhxh.codeproj.leetcode.__base.ListNode;

/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例 1：

输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]
示例 2：

输入：l1 = [], l2 = []
输出：[]
示例 3：

输入：l1 = [], l2 = [0]
输出：[0]

提示：

两个链表的节点数目范围是 [0, 50]
-100 <= Node.val <= 100
l1 和 l2 均按 非递减顺序 排列
 */
public class LeetCode21 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().mergeTwoLists(ListNode.buildNode(new int[]{1, 2, 4}), ListNode.buildNode(new int[]{1, 3, 4})));
        ListNode.printNode(new Solution2().mergeTwoLists(ListNode.buildNode(new int[]{1, 2, 4}), ListNode.buildNode(new int[]{1, 3, 4})));
    }

    /*
    迭代
     */
    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            //在返回节点之前保持对节点的不变引用。
            ListNode prehead = new ListNode(-1);

            ListNode prev = prehead;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    prev.next = l1;
                    l1 = l1.next;
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                prev = prev.next;
            }
            //此时 l1 和 l2 中的一个可以是非空的，因此将非空列表连接到合并列表的末尾。
            prev.next = l1 == null ? l2 : l1;
            return prehead.next;
        }
    }

    /*
    递归
     */
    static class Solution2 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

}
