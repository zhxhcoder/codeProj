package com.zhxh.codeproj.leetcode.hot100;

import com.zhxh.codeproj.leetcode.__base.ListNode;

import java.util.HashSet;
import java.util.Set;

/*
141. 环形链表
给你一个链表的头节点 head ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。

如果链表中存在环 ，则返回 true 。 否则，返回 false 。

示例 1：

输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：

输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
示例 3：

输入：head = [1], pos = -1
输出：false
解释：链表中没有环。


提示：

链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。


进阶：你能用 O(1)（即，常量）内存解决此问题吗？

 */
public class LeetCode141 {
    public static void main(String[] args) {
        System.out.println(new Solution().hasCycle(ListNode.buildNode(new int[]{3, 2, 0, -4})));
        System.out.println(new Solution2().hasCycle(ListNode.buildNode(new int[]{3, 2, 0, -4})));
    }

    /*
     哈希表
     */
    static class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> nodesSeen = new HashSet<>();
            while (head != null) {
                if (nodesSeen.contains(head)) {
                    return true;
                } else {
                    nodesSeen.add(head);
                }
                head = head.next;
            }
            return false;
        }
    }

    /*
     我们设置快慢两个指针，快指针每次走2步，慢指针每次走1步
     如视频所示，跑道是链表，兔子（快指针）每次走2步，乌龟（慢指针）每次走1步，在走完跑道（链表）的前提下，如果兔子和乌龟（快慢指针）相遇，说明跑道（链表）有环，否则说明跑道没有环。
    */
    static class Solution2 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return true;
        }
    }
}
