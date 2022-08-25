package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.__base.ListNode;

/*
25. K 个一组翻转链表

给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。

k是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。


示例：

给你这个链表：1->2->3->4->5

当k= 2 时，应当返回: 2->1->4->3->5

当k= 3 时，应当返回: 3->2->1->4->5


说明：

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

 */
class LeetCode25 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ListNode head = ListNode.buildNode(nums);
        ListNode.printNode(new Solution().reverseKGroup(head, 3));
    }

    /*
    本题的目标非常清晰易懂，不涉及复杂的算法，但是实现过程中需要考虑的细节比较多，容易写出冗长的代码。主要考察面试者设计的能力。

我们需要把链表结点按照 k 个一组分组，所以可以使用一个指针 head 依次指向每组的头结点。这个指针每次向前移动 k 步，直至链表结尾。
对于每个分组，我们先判断它的长度是否大于等于 k。若是，我们就翻转这部分链表，否则不需要翻转。

接下来的问题就是如何翻转一个分组内的子链表。翻转一个链表并不难，过程可以参考 206. 反转链表。但是对于一个子链表，除了翻转其本身之外，还需要将子链表的头部与上一个子链表连接，
以及子链表的尾部与下一个子链表连接。如下图所示：

     */
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (k == 1 || head == null)
                return head;
            return helper(head, k - 1);
        }

        public ListNode helper(ListNode head, int k) {
            if (head == null)
                return null;
            ListNode start = head, end = start;
            int index;
            for (index = 0; index < k; ++index) {//让end移动到第k个节点上
                if (end == null)
                    break;
                end = end.next;
            }
            if (index != k || end == null)//这种情况下，说明不够k个，直接返回head，即不翻转
                return head;
            ListNode node = end == null ? null : end.next;
            end.next = null;//这里先将k个节点与后面的连接断开，方便翻转链表
            reverse(start);//翻转这k个节点
            start.next = helper(node, k);//让前k个节点的头节点的next指向后k个节点的尾节点
            return end;
        }

        /**
         * 翻转链表
         *
         * @param head
         */
        public ListNode reverse(ListNode head) {
            ListNode pre = head, next = pre.next;
            while (next != null) {
                ListNode tmp = next.next;
                next.next = pre;
                pre = next;
                next = tmp;
            }
            return head;
        }
    }
}
