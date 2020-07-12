package com.zhxh.codeproj.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;

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
        TreeNode node1 = TreeNode.deserialize("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        System.out.println(new Solution().pathSum(node, 22));
        System.out.println(new Solution1().dfs(node1, 22));
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
            //叶子节点 且 匹配
            if (root.left == null && root.right == null && temp + root.val == sum) {
                path.add(root.val);/*添加该叶子节点*/
                ans.add(new ArrayList<>(path));//加入结果数组
                path.remove(path.size() - 1);//删除最后一项 相当与恢复到叶子节点上一层位置
                return;
            }
            path.add(root.val);/*添加该节点*/
            pathSum(ans, path, root.left, temp + root.val, sum);
            pathSum(ans, path, root.right, temp + root.val, sum);
            path.remove(path.size() - 1);/*回到该节点*/
        }
    }


    static class Solution1 {

        public List<List<Integer>> dfs(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            // Java 文档中 Stack 类建议使用 Deque 代替 Stack，注意：只使用栈的相关接口
            Deque<Integer> path = new ArrayDeque<>();
            dfs(root, sum, path, res);
            return res;
        }

        private void dfs(TreeNode node, int sum, Deque<Integer> path, List<List<Integer>> res) {
            if (node == null) {
                return;
            }
            if (node.val == sum && node.left == null && node.right == null) {
                path.addLast(node.val);
                res.add(new ArrayList<>(path));
                path.removeLast();
                return;
            }

            path.addLast(node.val);
            dfs(node.left, sum - node.val, path, res);
            dfs(node.right, sum - node.val, path, res);
            path.removeLast();
        }
    }

}
