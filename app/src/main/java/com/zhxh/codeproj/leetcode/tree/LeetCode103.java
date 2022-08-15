package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode._base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

        TreeNode node0 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 3, null, 4, null, 5, null, 6});

        List<List<Integer>> arr = new Solution().zigzagLevelOrder(node0);

        System.out.println(arr);
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

            // add the root element with a delimiter to kick off the BFS loop
            LinkedList<TreeNode> node_queue = new LinkedList<>();
            node_queue.addLast(root);
            node_queue.addLast(null);

            LinkedList<Integer> level_list = new LinkedList<>();
            boolean is_order_left = true;

            while (node_queue.size() > 0) {
                TreeNode curr_node = node_queue.pollFirst();
                if (curr_node != null) {
                    if (is_order_left)
                        level_list.addLast(curr_node.val);
                    else
                        level_list.addFirst(curr_node.val);

                    if (curr_node.left != null)
                        node_queue.addLast(curr_node.left);
                    if (curr_node.right != null)
                        node_queue.addLast(curr_node.right);

                } else {
                    // we finish the scan of one level
                    results.add(level_list);
                    level_list = new LinkedList<>();
                    // prepare for the next level
                    if (node_queue.size() > 0)
                        node_queue.addLast(null);
                    is_order_left = !is_order_left;
                }
            }
            return results;
        }
    }

}
