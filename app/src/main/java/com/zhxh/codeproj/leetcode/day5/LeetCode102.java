package com.zhxh.codeproj.leetcode.day5;

import com.zhxh.codeproj.leetcode._base.TreeNode;

import java.util.*;

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
        System.out.println(new Solution().levelOrder(TreeNode.buildBinaryTree(new Integer[]{1, 3, 4, 5, 2, 6, null, 9})));
        System.out.println(new Solution2().levelOrder(TreeNode.buildBinaryTree(new Integer[]{1, 3, 4, 5, 2, 6, null, 9})));
        System.out.println(new Solution3().levelOrder(TreeNode.buildBinaryTree(new Integer[]{1, 3, 4, 5, 2, 6, null, 9})));
    }

    /*
    递归
     */
    static class Solution {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            dns(root, 0);
            return list;
        }

        public void dns(TreeNode node, int lever) {
            if (node == null) return;
            if (list.size() == lever) list.add(new ArrayList<Integer>());

            list.get(lever).add(node.val);

            dns(node.left, lever + 1);
            dns(node.right, lever + 1);
        }
    }

    /*
    广度优先搜索
    我们可以想到最朴素的方法是用一个二元组（node，level）来表示状态，它表示某个节点和它所在的层数。
    考虑如何优化空间开销：如何不用哈希映射，并且只用一个变量node表示状态，实现这个功能？

    关键词：层次遍历
    模式识别：一旦出现树的层次遍历，都可以用队列作为辅助结构。

    我们可以用一种巧妙的方法修改广度优先搜索：
    - 首先根元素入队
    - 当队列不为空的时候
     - 求当前队列的长度si
     - 依次从队列中取si个元素进行拓展，然后进入下一次迭代
     */
    static class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            if (root == null) {
                return ret;
            }

            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<Integer>();
                int currentLevelSize = queue.size();
                for (int i = 1; i <= currentLevelSize; ++i) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                ret.add(level);
            }
            return ret;
        }
    }

    /*
    BFS的使用场景总结：层序遍历、最短路径问题
    本文将会讲解为什么这道题适合用广度优先搜索（BFS），以及BFS适用于什么样的场景。
    https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
     */
    static class Solution3 {
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
