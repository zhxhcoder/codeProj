package com.zhxh.codeproj.leetcode.hot100;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

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
        TreeNode.prettyPrintTree(new Solution2().convertBST(TreeNode.buildBinaryTree(new Integer[]{4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8})));
    }

    /*
    二叉搜索树的中序遍历是一个单调递增的有序序列。如果我们反序地中序遍历该二叉搜索树，即可得到一个单调的一个递减的有序序列。
    反序中序遍历
    本题中要求我们将每个节点的值修改为原来的节点值加上所有大于它的节点值之和。
    这样我们只需要反序地中序遍历该二叉搜索树，即可得到一个单调递减的有序序列。
     */
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

    /*
    Morris 遍历
    只占用常数空间来实现中序遍历。
     */
    static class Solution2 {
        public TreeNode convertBST(TreeNode root) {
            int sum = 0;
            TreeNode node = root;

            while (node != null) {
                if (node.right == null) {
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                } else {
                    TreeNode succ = getSuccessor(node);
                    if (succ.left == null) {
                        succ.left = node;
                        node = node.right;
                    } else {
                        succ.left = null;
                        sum += node.val;
                        node.val = sum;
                        node = node.left;
                    }
                }
            }

            return root;
        }

        public TreeNode getSuccessor(TreeNode node) {
            TreeNode succ = node.right;
            while (succ.left != null && succ.left != node) {
                succ = succ.left;
            }
            return succ;
        }
    }
}
