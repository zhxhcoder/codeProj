package com.zhxh.codeproj.leetcode.ace100.twopointer;

import com.zhxh.codeproj.leetcode.__base.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/*
19. 删除链表的倒数第 N 个结点

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？
 */
public class LeetCode19 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().removeNthFromEnd(ListNode.buildNode(new int[]{1, 2, 3, 4, 5}), 3));
        ListNode.printNode(new Solution2().removeNthFromEnd(ListNode.buildNode(new int[]{1, 2, 3, 4, 5}), 3));
        ListNode.printNode(new Solution3().removeNthFromEnd(ListNode.buildNode(new int[]{1, 2, 3, 4, 5}), 3));
        ListNode.printNode(new Solution4().removeNthFromEnd(ListNode.buildNode(new int[]{1, 2, 3, 4, 5}), 3));
    }

    /*
    暴力解法
    - 找到列表的长度
    - 删除从列表开头数起的第（L-n+1）个节点
     */
    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            int length = getLength(head);
            ListNode cur = dummy;
            for (int i = 1; i < length - n + 1; ++i) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            ListNode ans = dummy.next;
            return ans;
        }

        public int getLength(ListNode head) {
            int length = 0;
            while (head != null) {
                ++length;
                head = head.next;
            }
            return length;
        }
    }

    /*
    回溯法后序遍历
     */
    static class Solution2 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int traverse = traverse(head, n);
            if (traverse == n)
                return head.next;
            return head;
        }

        private int traverse(ListNode node, int n) {
            if (node == null)
                return 0;
            int num = traverse(node.next, n);
            if (num == n)
                node.next = node.next.next;
            return num + 1;
        }
    }


    /*
     双指针算法

     上述算法可以优化为只使用一次遍历。我们可以使用两个指针而不是一个指针。
     第一个指针从列表的开头向前移动 n+1 步，而第二个指针将从列表的开头出发。现在，这两个指针被 n 个结点分开。
     我们通过同时移动两个指针向前来保持这个恒定的间隔，直到第一个指针到达最后一个结点。此时第二个指针将指向从最后一个结点数起的第 n 个结点。
     我们重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。
    */
    static class Solution3 {
        /*
        -关键词：倒数第N个
        -模式识别：
          - 涉及链表的特殊位置，考虑快慢指针
          - 要删除链表的节点，找到它的前驱
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode first = dummy;
            ListNode second = dummy;
            //向进移动第一个指针，使第一个和第二个之间的间隙为 n 个节点
            for (int i = 1; i <= n + 1; i++) {
                first = first.next;
            }
            //先移动到最后，保持间隙
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;
            return dummy.next;
        }
    }

    static class Solution4 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            Deque<ListNode> stack = new LinkedList<ListNode>();
            ListNode cur = dummy;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            for (int i = 0; i < n; ++i) {
                stack.pop();
            }
            ListNode prev = stack.peek();
            prev.next = prev.next.next;
            ListNode ans = dummy.next;
            return ans;
        }
    }
}
