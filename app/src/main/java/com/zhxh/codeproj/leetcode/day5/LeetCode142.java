package com.zhxh.codeproj.leetcode.day5;

import com.zhxh.codeproj.leetcode.__base.ListNode;

import java.util.HashSet;
import java.util.Set;

/*
给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

不允许修改 链表。

示例 1：

输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
示例2：

输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。
示例 3：

输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。

提示：

链表中节点的数目范围在范围 [0, 104] 内
-105 <= Node.val <= 105
pos 的值为 -1 或者链表中的一个有效索引

 */
public class LeetCode142 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().detectCycle(ListNode.buildNode(new int[]{3, 2, 0, -4})));
        ListNode.printNode(new Solution2().detectCycle(ListNode.buildNode(new int[]{3, 2, 0, -4})));
        ListNode.printNode(new Solution3().detectCycle(ListNode.buildNode(new int[]{3, 2, 0, -4})));
    }

    /*
    哈希表
    我们遍历链表中的每个节点，并将它记录下来；
    一旦遇到了此前遍历过的节点，就可以判定链表中存在环。
    借助哈希表可以很方便地实现。
     */
    static class Solution {
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> visited = new HashSet<ListNode>();

            ListNode node = head;
            while (node != null) {
                if (visited.contains(node)) {
                    return node;
                }
                visited.add(node);
                node = node.next;
            }

            return null;
        }
    }

    /*
    快慢指针
     */
    static class Solution2 {
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head;
            while (fast != null) {
                slow = slow.next;
                if (fast.next != null) {
                    fast = fast.next.next;
                } else {
                    return null;
                }
                if (fast == slow) {
                    ListNode ptr = head;
                    while (ptr != slow) {
                        ptr = ptr.next;
                        slow = slow.next;
                    }
                    return ptr;
                }
            }
            return null;
        }
    }


    static class Solution3 {
        private ListNode getIntersect(ListNode head) {
            ListNode tortoise = head;
            ListNode hare = head;
            // 快指针要么循环循环并遇到慢指针，要么到达非循环列表末尾的 `null`。
            while (hare != null && hare.next != null) {
                tortoise = tortoise.next;
                hare = hare.next.next;
                if (tortoise == hare) {
                    return tortoise;
                }
            }
            return null;
        }

        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            // 如果有一个循环，快/慢指针将在某个节点处相交。 否则，就没有循环，所以我们找不到循环的本质。
            ListNode intersect = getIntersect(head);
            if (intersect == null) {
                return null;
            }
            // 为了找到循环的位置，我们有两个指针以相同的速度遍历——一个来自列表的前面，另一个来自交叉点。
            ListNode ptr1 = head;
            ListNode ptr2 = intersect;
            while (ptr1 != ptr2) {
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }
            return ptr1;
        }
    }
}
