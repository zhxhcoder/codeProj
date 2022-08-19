package com.zhxh.codeproj.leetcode.day5;

import com.zhxh.codeproj.leetcode._base.TreeNode;

/*
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

       1
      / \
     2   3

输出: 6
示例2:

输入: [-10,9,20,null,null,15,7]

  -10
 / \
 9 20
  / \
 15  7

输出: 42

 */
class LeetCode124 {
    public static void main(String[] args) {
        TreeNode node = TreeNode.buildBinaryTree(new Integer[]{-10, 9, 20, null, null, 15, 7});
        System.out.println(new Solution().maxPathSum(node));
    }

    /*
    首先，考虑实现一个简化的函数 maxGain(node)，
    该函数计算二叉树中的一个节点的最大贡献值，具体而言，就是在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
    具体而言，该函数的计算如下。

空节点的最大贡献值等于 00。

非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和（对于叶节点而言，最大贡献值等于节点值）。

例如，考虑如下二叉树。
 -10
   / \
  9  20
    /  \
   15   7
叶节点 99、1515、77 的最大贡献值分别为 99、1515、77。

得到叶节点的最大贡献值之后，再计算非叶节点的最大贡献值。节点 2020 的最大贡献值等于 20+\max(15,7)=3520+max(15,7)=35，节点 -10−10 的最大贡献值等于 -10+\max(9,35)=25−10+max(9,35)=25。

上述计算过程是递归的过程，因此，对根节点调用函数 maxGain，即可得到每个节点的最大贡献值。

根据函数 maxGain 得到每个节点的最大贡献值之后，如何得到二叉树的最大路径和？对于二叉树中的一个节点，
该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，如果子节点的最大贡献值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和。
维护一个全局变量 maxSum 存储最大路径和，在递归过程中更新 maxSum 的值，最后得到的 maxSum 的值即为二叉树中的最大路径和。

     */
    static class Solution {
        int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }

        public int maxGain(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // 递归计算左右子节点的最大贡献值
            // 只有在最大贡献值大于 0 时，才会选取对应子节点
            int leftGain = Math.max(maxGain(node.left), 0);
            int rightGain = Math.max(maxGain(node.right), 0);

            // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
            int priceNewpath = node.val + leftGain + rightGain;

            // 更新答案
            maxSum = Math.max(maxSum, priceNewpath);

            // 返回节点的最大贡献值
            return node.val + Math.max(leftGain, rightGain);
        }
    }
}
