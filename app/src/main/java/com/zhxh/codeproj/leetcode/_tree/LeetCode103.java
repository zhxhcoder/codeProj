package com.zhxh.codeproj.leetcode._tree;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.*;

/*
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层次遍历如下：

[
  [3],
  [20,9],
  [15,7]
]

 */
class LeetCode103 {
    public static void main(String[] args) {
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.buildBinaryTree(new Integer[]{3, 9, 20, null, null, 15, 7})));
        System.out.println(new Solution2().zigzagLevelOrder(TreeNode.buildBinaryTree(new Integer[]{3, 9, 20, null, null, 15, 7})));
        System.out.println(new Solution3().zigzagLevelOrder(TreeNode.buildBinaryTree(new Integer[]{3, 9, 20, null, null, 15, 7})));
    }

    /*
    算法

    实现 BFS 的几种算法。
    使用两层嵌套循环。外层循环迭代树的层级，内层循环迭代每层上的节点。
    也可以使用一层循环实现 BFS。将要访问的节点添加到队列中，使用 分隔符（例如：空节点）把不同层的节点分隔开。分隔符表示一层结束和新一层开始。
    这里采用第二种方法。在此算法的基础上，借助双端队列实现锯齿形顺序。在每一层，使用一个空的双端队列保存该层所有的节点。根据每一层的访问顺序，即从左到右或从右到左，决定从双端队列的哪一端插入节点。
     */
    static class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> results = new ArrayList<>();
            //添加带有分隔符的根元素以启动 BFS 循环
            LinkedList<TreeNode> node_queue = new LinkedList<>();
            node_queue.addLast(root);
            node_queue.addLast(null);

            LinkedList<Integer> level_list = new LinkedList<>();
            boolean is_order_left = true;

            while (node_queue.size() > 0) {
                TreeNode curr_node = node_queue.pollFirst();
                if (curr_node != null) {
                    if (is_order_left) {
                        level_list.addLast(curr_node.val);
                    } else {
                        level_list.addFirst(curr_node.val);
                    }

                    if (curr_node.left != null) {
                        node_queue.addLast(curr_node.left);
                    }
                    if (curr_node.right != null) {
                        node_queue.addLast(curr_node.right);
                    }
                } else {
                    //完成了一层的扫描
                    results.add(level_list);
                    level_list = new LinkedList<>();
                    //为下一层做准备
                    if (node_queue.size() > 0)
                        node_queue.addLast(null);
                    is_order_left = !is_order_left;
                }
            }
            return results;
        }
    }

    /*
    方法一：广度优先遍历
    此题是「102. 二叉树的层序遍历」的变种，最后输出的要求有所变化，要求我们按层数的奇偶来决定每一层的输出顺序。
    规定二叉树的根节点为第 00 层，如果当前层数是偶数，从左至右输出当前层的节点值，否则，从右至左输出当前层的节点值。

    我们依然可以沿用第 102 题的思想，修改广度优先搜索，对树进行逐层遍历，用队列维护当前层的所有元素，当队列不为空的时候，
    求得当前队列的长度 \textit{size}size，每次从队列中取出 \textit{size}size 个元素进行拓展，然后进行下一次迭代。
     */
    static class Solution2 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList<List<Integer>>();
            if (root == null) {
                return ans;
            }
            Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
            nodeQueue.offer(root);
            boolean isOrderLeft = true;

            while (!nodeQueue.isEmpty()) {
                Deque<Integer> levelList = new LinkedList<Integer>();
                int size = nodeQueue.size();
                for (int i = 0; i < size; ++i) {
                    TreeNode curNode = nodeQueue.poll();
                    if (isOrderLeft) {
                        levelList.offerLast(curNode.val);
                    } else {
                        levelList.offerFirst(curNode.val);
                    }
                    if (curNode.left != null) {
                        nodeQueue.offer(curNode.left);
                    }
                    if (curNode.right != null) {
                        nodeQueue.offer(curNode.right);
                    }
                }
                ans.add(new LinkedList<Integer>(levelList));
                isOrderLeft = !isOrderLeft;
            }
            return ans;
        }
    }

    /*
    递归实现-深度优先遍历
    - 相同层序的节点归入同一个数组
    - 传入辅助的level参数，决定层序
     */
    static class Solution3 {
        private void dfs(TreeNode node, int level, List<List<Integer>> res) {
            if (level == res.size()) {
                LinkedList<Integer> newLevel = new LinkedList<Integer>();
                newLevel.add(node.val);
                res.add(newLevel);
            } else {
                if (level % 2 == 0) {
                    res.get(level).add(node.val);
                } else {
                    res.get(level).add(0, node.val);
                }
            }
            if (node.left != null) {
                dfs(node.left, level + 1, res);
            }
            if (node.right != null) {
                dfs(node.right, level + 1, res);
            }
        }

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            dfs(root, 0, res);
            return res;
        }
    }
}
