package com.zhxh.codeproj.leetcode._tree;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

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
        System.out.println(new Solution().isBalanced(TreeNode.buildBinaryTree(new Integer[]{3, 9, 20, null, null, 15, 7})));
        System.out.println(new Solution2().isBalanced(TreeNode.buildBinaryTree(new Integer[]{3, 9, 20, null, null, 15, 7})));
    }

    /*
    方法一：自顶向下的递归
     */
    static class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            } else {
                return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
            }
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                return Math.max(height(root.left), height(root.right)) + 1;
            }
        }
    }

    /*
    方法二：自底向上的递归
     */
    static class Solution2 {
        public boolean isBalanced(TreeNode root) {
            return height(root) >= 0;
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }
    }
}
