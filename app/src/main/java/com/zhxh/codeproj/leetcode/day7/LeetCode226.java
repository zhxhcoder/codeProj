package com.zhxh.codeproj.leetcode.day7;

import com.zhxh.codeproj.leetcode._base.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/*
翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 */
public class LeetCode226 {
    public static void main(String[] args) {
        TreeNode.prettyPrintTree(new Solution().invertTree(TreeNode.buildBinaryTree(new Integer[]{4, 2, 7, 1, 3, 6, 9})));
        TreeNode.prettyPrintTree(new Solution2().invertTree(TreeNode.buildBinaryTree(new Integer[]{4, 2, 7, 1, 3, 6, 9})));
        TreeNode.prettyPrintTree(new Solution3().invertTree(TreeNode.buildBinaryTree(new Integer[]{4, 2, 7, 1, 3, 6, 9})));
    }

    /*
    递归
     */
    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode right = invertTree(root.right);
            TreeNode left = invertTree(root.left);
            root.left = right;
            root.right = left;
            return root;
        }
    }

    /*
     * 层序遍历方式反转
     */
    static class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

    /*
     * 深度优先遍历的方式反转
     */
    static class Solution3 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                int size = stack.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = stack.pop();
                    TreeNode temp = cur.left;
                    cur.left = cur.right;
                    cur.right = temp;
                    if (cur.right != null) {
                        stack.push(cur.right);
                    }
                    if (cur.left != null) {
                        stack.push(cur.left);
                    }
                }
            }
            return root;
        }
    }
}
