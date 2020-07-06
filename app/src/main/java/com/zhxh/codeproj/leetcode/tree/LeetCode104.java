package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode.Pair;

import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;


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
        System.out.println(new Solution2().maxDepth(node0));
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


    static class Solution2 {
        public int maxDepth(TreeNode root) {
            Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
            if (root != null) {
                stack.add(new Pair(root, 1));
            }

            int depth = 0;
            while (!stack.isEmpty()) {
                Pair<TreeNode, Integer> current = stack.poll();
                root = current.first;
                int current_depth = current.second;
                if (root != null) {
                    depth = Math.max(depth, current_depth);
                    stack.add(new Pair(root.left, current_depth + 1));
                    stack.add(new Pair(root.right, current_depth + 1));
                }
            }
            return depth;
        }
    }
}
