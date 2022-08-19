package com.zhxh.codeproj.leetcode.day5;

import com.zhxh.codeproj.leetcode._base.Pair;
import com.zhxh.codeproj.leetcode._base.TreeNode;

import java.lang.Math;
import java.util.LinkedList;
import java.util.Queue;


/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明:叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度3 。

 */
public class LeetCode104 {
    public static void main(String[] args) {
        System.out.println(new Solution0().maxDepth(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3})));
        System.out.println(new Solution().maxDepth(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3})));
        System.out.println(new Solution2().maxDepth(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3})));
    }

    /*
    自己写的宽度优先，遍历
     */
    static class Solution0 {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 0;
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                depth++;
            }
            return depth;
        }
    }

    /*
    DFS 深度优先搜索策略
     */
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

    /*
    不用递归
     */
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
