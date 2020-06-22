package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode._bean.TreeNode;

/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。

 */
class LeetCode98 {
    public static void main(String[] args) {
        TreeNode node = TreeNode.buildBinaryTree(new Integer[]{2, 1, 5, null, 5});
        System.out.println(new Solution().isValidBST(node));
    }

    /*
    要解决这道题首先我们要了解二叉搜索树有什么性质可以给我们利用，
    由题目给出的信息我们可以知道：如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
     若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树。
     */
    static class Solution {
        public boolean helper(TreeNode node, Integer lower, Integer upper) {
            if (node == null) return true;

            int val = node.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;

            if (!helper(node.right, val, upper)) return false;
            if (!helper(node.left, lower, val)) return false;
            return true;
        }

        public boolean isValidBST(TreeNode root) {
            return helper(root, null, null);
        }
    }

}
