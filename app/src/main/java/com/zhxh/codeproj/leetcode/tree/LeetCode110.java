package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode.TreeNode;

/*
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回false 。

 */
class LeetCode110 {
    public static void main(String[] args) {

    }

    static class Solution {
        // Recursively obtain the height of a tree. An empty tree has -1 height
        private int height(TreeNode root) {
            // An empty tree has height -1
            if (root == null) {
                return -1;
            }
            return 1 + Math.max(height(root.left), height(root.right));
        }

        public boolean isBalanced(TreeNode root) {
            // An empty tree satisfies the definition of a balanced tree
            if (root == null) {
                return true;
            }

            // Check if subtrees have height within 1. If they do, check if the
            // subtrees are balanced
            return Math.abs(height(root.left) - height(root.right)) < 2
                    && isBalanced(root.left)
                    && isBalanced(root.right);
        }
    }

}
