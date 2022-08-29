package com.zhxh.codeproj.leetcode.day1;

import com.zhxh.codeproj.leetcode.__base.ListNode;

/*
给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

 */
public class LeetCode02 {
    public static void main(String[] args) {
        ListNode.printNode(new Solution().addTwoNumbers(ListNode.buildNode(new int[]{2, 4, 5}), ListNode.buildNode(new int[]{5, 6, 4})));
        ListNode.printNode(new Solution2().addTwoNumbers(ListNode.buildNode(new int[]{2, 4, 5}), ListNode.buildNode(new int[]{5, 6, 4})));
        ListNode.printNode(new Solution3().addTwoNumbers(ListNode.buildNode(new int[]{2, 4, 5}), ListNode.buildNode(new int[]{5, 6, 4})));
    }

    /*
     a. 两条链表相加，返回的结果要求是链表，每次插值的时候都要在链表的尾端添加。
     需要一个返回结果的链表res，需要一个指针，每次添加后都指向最后一个节点。
     b. 如果有进数(两数想和大于十进1)，下次相加时一起加上。
     c. 两条链表长度可能不相等，因此结束循环后，需要判断是否有未遍历完的链表，有则单独遍历，没有则进入下一步。
     d. 当两条链表都遍历结束，还需要判断进数是否为0，为0什么也不做，不为0在运行结果的链表尾部添加值为1的节点。
     c. 最后可以直接返回res链表
     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode p, ListNode q) {
            ListNode dummyHead = new ListNode(0);
            ListNode curr = dummyHead;
            int carry = 0;
            while (p != null || q != null) {
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (p != null) p = p.next;
                if (q != null) q = q.next;
            }
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;
        }

    }

    /*
     * 用最常规的方法求解
     * step1:遍历两个链表，两者之和为新的链表res，如果长度不一样，则默认为0
     * step2：遍历新链表res，如果大于10则，下一个新加1
     */
    static class Solution2 {
        public ListNode addTwoNumbers(ListNode p, ListNode q) {
            ListNode dummyHead = new ListNode(0);
            //指针指到原来的位置
            ListNode res = dummyHead;
            while (p != null || q != null) {
                int a = p != null ? p.val : 0;
                int b = q != null ? q.val : 0;

                res.next = new ListNode(a + b);
                //移动到尾部结点
                res = res.next;

                if (p != null) p = p.next;
                if (q != null) q = q.next;
            }

            //上面res指针已经到了，所以重新指回原来的位置
            res = dummyHead;
            int carry = 0;
            while (res != null) {
                //保存原来的值
                int value = res.val;
                //加上上个节点的carry
                res.val = (carry + value) % 10;
                //新的 carry
                carry = (carry + value) / 10;

                //链表末尾时且 carry>0
                if (res.next == null && carry > 0) {
                    res.next = new ListNode(carry);
                    //carry已经利用过了，重新初始化
                    carry = 0;
                }

                if (res != null) res = res.next;
            }

            return dummyHead.next;
        }
    }

    /*
    自己写的
     */
    static class Solution3 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            ListNode p1 = l1;
            ListNode p2 = l2;
            ListNode root = new ListNode(0); // 头结点
            ListNode r = root;
            root.next = l1;

            int carry = 0; // 初始进位
            int sum;
            while (p1 != null && p2 != null) {
                sum = p1.val + p2.val + carry;
                p1.val = sum % 10; // 本位的结果
                carry = sum / 10; // 本次进位

                r.next = p1;
                r = p1; // 指向最后一个相加的结点
                p1 = p1.next;
                p2 = p2.next;
            }

            if (p1 == null) {
                r.next = p2;
            } else {
                r.next = p1;
            }

            // 最后一次相加还有进位
            if (carry == 1) {
                // 开始时r.next是第一个要相加的结点
                while (r.next != null) {
                    sum = r.next.val + carry;
                    r.next.val = sum % 10;
                    carry = sum / 10;
                    r = r.next;
                }
                // 都加完了还有进位，就要创建一个新的结点
                if (carry == 1) {
                    r.next = new ListNode(1);
                }
            }
            return root.next;
        }
    }
}
