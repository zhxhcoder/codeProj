package com.zhxh.codeproj.leetcode.top145;

import com.zhxh.codeproj.leetcode.__base.ListNode;

/*
给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。

第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。

请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。

你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。



示例 1:



输入: head = [1,2,3,4,5]
输出: [1,3,5,2,4]
示例 2:



输入: head = [2,1,3,5,6,4,7]
输出: [2,3,6,7,1,5,4]


提示:

n ==  链表中的节点数
0 <= n <= 104
-106 <= Node.val <= 106

 */
public class LeetCode328 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().oddEvenList(ListNode.buildNode(new int[]{1, 2, 3, 4, 5})));
    }

    /*
    方法一：分离节点后合并
     */
    static class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null) {
                return head;
            }
            //偶数头节点，头部
            ListNode evenHead = head.next;
            //奇数头节点
            ListNode odd = head;
            //偶数链表
            ListNode even = evenHead;
            while (even != null && even.next != null) {
                //删除偶数节点
                odd.next = even.next;
                odd = odd.next;
                //连接偶数节点
                even.next = odd.next;
                even = even.next;
            }
            //拼接
            odd.next = evenHead;
            return head;
        }
    }
}
