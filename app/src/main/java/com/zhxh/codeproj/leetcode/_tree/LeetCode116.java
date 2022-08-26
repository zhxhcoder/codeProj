package com.zhxh.codeproj.leetcode._tree;

import com.zhxh.codeproj.leetcode.__base.Node;

import java.util.LinkedList;
import java.util.Queue;

/*

给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。



示例：


输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。


提示：

你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
class LeetCode116 {
    public static void main(String[] args) {
        Node.printNode(new Solution().connect(new Node(3)));
        Node.printNode(new Solution2().connect(new Node(3)));
    }

    /*
    方法一：层次遍历
     */
    static class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            // 初始化队列同时将第一层节点加入队列中，即根节点
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            // 外层的 while 循环迭代的是层数
            while (!queue.isEmpty()) {
                // 记录当前队列大小
                int size = queue.size();
                // 遍历这一层的所有节点
                for (int i = 0; i < size; i++) {
                    // 从队首取出元素
                    Node node = queue.poll();
                    // 连接
                    if (i < size - 1) {
                        node.next = queue.peek();
                    }
                    // 拓展下一层节点
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            // 返回根节点
            return root;
        }
    }

    /*
    方法二：使用已建立的
     */
    static class Solution2 {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            // 从根节点开始
            Node leftmost = root;
            while (leftmost.left != null) {
                // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
                Node head = leftmost;
                while (head != null) {
                    // CONNECTION 1
                    head.left.next = head.right;
                    // CONNECTION 2
                    if (head.next != null) {
                        head.right.next = head.next.left;
                    }
                    // 指针向后移动
                    head = head.next;
                }
                // 去下一层的最左的节点
                leftmost = leftmost.left;
            }
            return root;
        }
    }
}
