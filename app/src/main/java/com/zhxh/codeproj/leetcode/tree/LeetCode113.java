package com.zhxh.codeproj.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/*
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

 */
class LeetCode113 {
    public static void main(String[] args) {
        TreeNode node = TreeNode.buildBinaryTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        System.out.println(new Solution().pathSum(node, 22));
    }

    /*
    这一题是很典型的回溯算法
思路：
从根节点出发，使用一个temp变量和List容器记录走过路径
在走该条路径之前将节点的值加入List容器，走完之后（返回之后）将加入的值移除
当节点为空时，返回继续查找
当节点的左孩子和右孩子都为空并且temp变量等于sum时，说明这个节点是叶子节点且找到了一条路径，将List加入答案中

     */
    static class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            pathSum(ans, path, root, 0, sum);
            return ans;
        }

        public void pathSum(List<List<Integer>> ans, List<Integer> path, TreeNode root, int temp, int sum) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null && temp + root.val == sum) {
                path.add(root.val);
                ans.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                return;
            }
            path.add(root.val);
            pathSum(ans, path, root.left, temp + root.val, sum);
            pathSum(ans, path, root.right, temp + root.val, sum);
            path.remove(path.size() - 1);
        }
    }
}
