package com.zhxh.codeproj.leetcode.hot100;

import com.zhxh.codeproj.leetcode.__base.ListNode;

import java.util.PriorityQueue;

/*
23. 合并K个升序链表
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。


示例 1：

输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：

输入：lists = []
输出：[]
示例 3：

输入：lists = [[]]
输出：[]


提示：

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4
 */
public class LeetCode23 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().mergeKLists(new ListNode[]{
                ListNode.buildNode(new int[]{1, 4, 5}),
                ListNode.buildNode(new int[]{1, 3, 4}),
                ListNode.buildNode(new int[]{2, 6})}
        ));

        ListNode.printNode(new Solution2().mergeKLists(new ListNode[]{
                ListNode.buildNode(new int[]{1, 4, 5}),
                ListNode.buildNode(new int[]{1, 3, 4}),
                ListNode.buildNode(new int[]{2, 6})}
        ));

        ListNode.printNode(new Solution3().mergeKLists(new ListNode[]{
                ListNode.buildNode(new int[]{1, 4, 5}),
                ListNode.buildNode(new int[]{1, 3, 4}),
                ListNode.buildNode(new int[]{2, 6})}
        ));

        ListNode.printNode(new Solution4().mergeKLists(new ListNode[]{
                ListNode.buildNode(new int[]{1, 4, 5}),
                ListNode.buildNode(new int[]{1, 3, 4}),
                ListNode.buildNode(new int[]{2, 6})}
        ));
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            return merge(lists, 0, lists.length - 1);
        }

        private ListNode merge(ListNode[] lists, int left, int right) {
            if (left == right) return lists[left];
            int mid = left + (right - left) / 2;
            ListNode l1 = merge(lists, left, mid);
            ListNode l2 = merge(lists, mid + 1, right);
            return mergeTwoLists(l1, l2);
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    /*
    方法一：顺序合并
    我们可以想到一种最朴素的方法：用一个变量 ans 来维护以及合并的链表，
    第 i 次循环把第 i 个链表和 ans 合并，答案保存到 ans 中。
     */
    static class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode ans = null;
            for (int i = 0; i < lists.length; ++i) {
                ans = mergeTwoLists(ans, lists[i]);
            }
            return ans;
        }

        public ListNode mergeTwoLists(ListNode a, ListNode b) {
            if (a == null || b == null) {
                return a != null ? a : b;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head, aPtr = a, bPtr = b;
            while (aPtr != null && bPtr != null) {
                if (aPtr.val < bPtr.val) {
                    tail.next = aPtr;
                    aPtr = aPtr.next;
                } else {
                    tail.next = bPtr;
                    bPtr = bPtr.next;
                }
                tail = tail.next;
            }
            tail.next = (aPtr != null ? aPtr : bPtr);
            return head.next;
        }
    }

    /*
    方法二：分治合并
     */
    static class Solution3 {
        public ListNode mergeKLists(ListNode[] lists) {
            return merge(lists, 0, lists.length - 1);
        }

        public ListNode merge(ListNode[] lists, int l, int r) {
            if (l == r) {
                return lists[l];
            }
            if (l > r) {
                return null;
            }
            int mid = (l + r) >> 1;
            return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
        }

        public ListNode mergeTwoLists(ListNode a, ListNode b) {
            if (a == null || b == null) {
                return a != null ? a : b;
            }
            ListNode head = new ListNode(0);
            ListNode tail = head, aPtr = a, bPtr = b;
            while (aPtr != null && bPtr != null) {
                if (aPtr.val < bPtr.val) {
                    tail.next = aPtr;
                    aPtr = aPtr.next;
                } else {
                    tail.next = bPtr;
                    bPtr = bPtr.next;
                }
                tail = tail.next;
            }
            tail.next = (aPtr != null ? aPtr : bPtr);
            return head.next;
        }
    }

    /*
    方法三：使用优先队列合并
     */
    static class Solution4 {
        static class Status implements Comparable<Status> {
            int val;
            ListNode ptr;

            Status(int val, ListNode ptr) {
                this.val = val;
                this.ptr = ptr;
            }

            public int compareTo(Status status2) {
                return this.val - status2.val;
            }
        }

        PriorityQueue<Status> queue = new PriorityQueue<Status>();

        public ListNode mergeKLists(ListNode[] lists) {
            for (ListNode node : lists) {
                if (node != null) {
                    queue.offer(new Status(node.val, node));
                }
            }
            ListNode head = new ListNode(0);
            ListNode tail = head;
            while (!queue.isEmpty()) {
                Status f = queue.poll();
                tail.next = f.ptr;
                tail = tail.next;
                if (f.ptr.next != null) {
                    queue.offer(new Status(f.ptr.next.val, f.ptr.next));
                }
            }
            return head.next;
        }
    }
}
