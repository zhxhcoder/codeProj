package com.zhxh.codeproj.leetcode.tree;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

 */
public class LeetCode104 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node0 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3});
        System.out.println(solution.maxDepth(node0));
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int left_height = maxDepth(root.left);
                int right_height = maxDepth(root.right);
                return java.lang.Math.max(left_height, right_height) + 1;
            }
        }
    }
}
