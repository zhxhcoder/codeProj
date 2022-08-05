package com.zhxh.codeproj.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。



示例：
二叉树：[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

[[1], [4, 3], [5, 2, 6], [9]]

 */
class LeetCode102 {

    public static void main(String[] args) {

        TreeNode root = TreeNode.buildBinaryTree(new Integer[]{1, 3, 4, 5, 2, 6, null, 9});

        List<List<Integer>> arr = new Solution().levelOrder(root);

        System.out.println(arr);
    }

    /*
    https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/

     */
    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();

            Queue<TreeNode> queue = new ArrayDeque<>();
            if (root != null) {
                queue.add(root);
            }
            while (!queue.isEmpty()) {
                int n = queue.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    TreeNode node = queue.poll(); //删除头部
                    level.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left); //添加到队尾
                    }
                    if (node.right != null) {
                        queue.add(node.right); //添加到队尾
                    }
                }
                res.add(level);
            }
            return res;
        }
    }
}
