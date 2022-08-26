package com.zhxh.codeproj.leetcode._tree;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明:叶子节点是指没有子节点的节点。

示例:
输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
输出：true
解释：等于目标和的根节点到叶节点路径如上图所示。

给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

 */
class LeetCode112 {
    public static void main(String[] args) {
        System.out.println(new Solution().hasPathSum(TreeNode.buildBinaryTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1}), 22));
        System.out.println(new Solution2().hasPathSum(TreeNode.buildBinaryTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1}), 22));
    }

    /*
    方法一：广度优先搜索
    首先我们可以想到使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
    这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。
     */
    static class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            Queue<TreeNode> queNode = new LinkedList<TreeNode>();
            Queue<Integer> queVal = new LinkedList<Integer>();
            queNode.offer(root);
            queVal.offer(root.val);
            while (!queNode.isEmpty()) {
                TreeNode now = queNode.poll();
                int temp = queVal.poll();
                if (now.left == null && now.right == null) {
                    if (temp == sum) {
                        return true;
                    }
                    continue;
                }
                if (now.left != null) {
                    queNode.offer(now.left);
                    queVal.offer(now.left.val + temp);
                }
                if (now.right != null) {
                    queNode.offer(now.right);
                    queVal.offer(now.right.val + temp);
                }
            }
            return false;
        }
    }

    /*
    方法二：递归
     */
    static class Solution2 {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            if (root.left == null && root.right == null) {
                return sum == root.val;
            }
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
}
