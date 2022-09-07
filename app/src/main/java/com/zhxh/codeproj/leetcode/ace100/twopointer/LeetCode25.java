package com.zhxh.codeproj.leetcode.ace100.twopointer;

import com.zhxh.codeproj.leetcode.__base.ListNode;

/*
25. K 个一组翻转链表
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。



示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
示例 2：



输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]


提示：
链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000


进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
class LeetCode25 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().reverseKGroup(ListNode.buildNode(new int[]{1, 2, 3, 4, 5, 6}), 3));
        ListNode.printNode(new Solution2().reverseKGroup(ListNode.buildNode(new int[]{1, 2, 3, 4, 5, 6}), 3));
        ListNode.printNode(new Solution3().reverseKGroup(ListNode.buildNode(new int[]{1, 2, 3, 4, 5, 6}), 3));
    }

    /*
    方法一：模拟
    本题的目标非常清晰易懂，不涉及复杂的算法，但是实现过程中需要考虑的细节比较多，容易写出冗长的代码。主要考查面试者设计的能力。
     */
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode hair = new ListNode(0);
            hair.next = head;
            ListNode pre = hair;

            while (head != null) {
                ListNode tail = pre;
                // 查看剩余部分长度是否大于等于 k
                for (int i = 0; i < k; ++i) {
                    tail = tail.next;
                    if (tail == null) {
                        return hair.next;
                    }
                }
                ListNode nex = tail.next;
                ListNode[] reverse = myReverse(head, tail);
                head = reverse[0];
                tail = reverse[1];
                // 把子链表重新接回原链表
                pre.next = head;
                tail.next = nex;
                pre = tail;
                head = tail.next;
            }
            return hair.next;
        }

        public ListNode[] myReverse(ListNode head, ListNode tail) {
            ListNode prev = tail.next;
            ListNode p = head;
            while (prev != tail) {
                ListNode nex = p.next;
                p.next = prev;
                prev = p;
                p = nex;
            }
            return new ListNode[]{tail, head};
        }
    }

    /*
     * K个一组翻转链表的通用实现，快慢指针-链表反转。
     */
    static class Solution2 {
        public ListNode reverseKGroup(ListNode head, int k) {
            // 哑结点
            ListNode dummy = new ListNode(-1, head);
            // 子链表头结点的前驱结点
            ListNode prevSubHead = dummy;
            // 快慢指针
            // 慢指针指向头结点
            ListNode slow = head;
            // 快指针指向尾结点的next结点
            ListNode fast = head;
            while (fast != null) {
                // K个一组
                int nodeNum = 0;
                for (; nodeNum < k && fast != null; nodeNum++) {
                    fast = fast.next;
                }
                if (nodeNum == k) {
                    // 链表反转，slow头结点变为尾结点
                    ListNode subHead = reverse(slow, fast);
                    prevSubHead.next = subHead;
                    prevSubHead = slow;
                    slow = fast;
                }
            }
            return dummy.next;
        }

        /*
         * 链表反转的通用实现，迭代/顺序访问。
         *
         * 尾结点的指针可能为null，也可能不为null，如局部反转。
         * 反转后，链表还是连通的。
         */
        private ListNode reverse(ListNode head, ListNode tailNext) {
            // 头结点，初始化指向尾结点的next结点
            ListNode prev = tailNext;
            ListNode cur = head;
            while (cur != tailNext) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }
    }

    /*
    本题的目标非常清晰易懂，不涉及复杂的算法，但是实现过程中需要考虑的细节比较多，容易写出冗长的代码。主要考察面试者设计的能力。

    我们需要把链表结点按照 k 个一组分组，所以可以使用一个指针 head 依次指向每组的头结点。这个指针每次向前移动 k 步，直至链表结尾。
    对于每个分组，我们先判断它的长度是否大于等于 k。若是，我们就翻转这部分链表，否则不需要翻转。

    接下来的问题就是如何翻转一个分组内的子链表。翻转一个链表并不难，过程可以参考 206. 反转链表。但是对于一个子链表，除了翻转其本身之外，还需要将子链表的头部与上一个子链表连接，
    以及子链表的尾部与下一个子链表连接。如下图所示：

     */
    static class Solution3 {
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

        /*
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
