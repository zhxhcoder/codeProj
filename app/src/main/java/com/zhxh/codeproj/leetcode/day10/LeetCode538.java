package com.zhxh.codeproj.leetcode.day10;

import com.zhxh.codeproj.leetcode._base.TreeNode;

/*

给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。



例如：

输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13
 */
public class LeetCode538 {
    public static void main(String[] args) {
        TreeNode.prettyPrintTree(new Solution().convertBST(TreeNode.buildBinaryTree(new Integer[]{4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8})));
    }

    static class Solution {
        private int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            if (root != null) {
                convertBST(root.right);
                sum += root.val;
                root.val = sum;
                convertBST(root.left);
            }
            return root;
        }
    }
}
