package com.zhxh.codeproj.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
之字形打印二叉树
同103. 二叉树的锯齿形层次遍历
 */
class Interview32_03 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildBinaryTree(new Integer[]{1, 3, 4, 5, 2, 6, null, 9});
        System.out.println(new Solution().print(root));
        System.out.println(new Solution1().zigzagLevelOrder(root));
        System.out.println(new Solution2().zigzagLevelOrder(root));
    }

    static class Solution {
        public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            Stack<TreeNode> s1 = new Stack<TreeNode>();
            Stack<TreeNode> s2 = new Stack<TreeNode>();
            int flag = 1;
            if (pRoot == null)
                return res;
            s2.push(pRoot);
            ArrayList<Integer> temp = new ArrayList<Integer>();
            while (!s1.isEmpty() || !s2.isEmpty()) {
                if (flag % 2 != 0) {
                    while (!s2.isEmpty()) {
                        TreeNode node = s2.pop();
                        temp.add(node.val);
                        if (node.left != null) {
                            s1.push(node.left);
                        }
                        if (node.right != null) {
                            s1.push(node.right);
                        }
                    }
                }
                if (flag % 2 == 0) {
                    while (!s1.isEmpty()) {
                        TreeNode node = s1.pop();
                        temp.add(node.val);
                        if (node.right != null) {
                            s2.push(node.right);
                        }
                        if (node.left != null) {
                            s2.push(node.left);
                        }
                    }
                }
                res.add(new ArrayList<Integer>(temp));
                temp.clear();
                flag++;
            }
            return res;
        }
    }

    /*
    广度优先遍历
     */
    static class Solution1 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }
            List<List<Integer>> results = new ArrayList<List<Integer>>();

            // add the root element with a delimiter to kick off the BFS loop
            LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
            node_queue.addLast(root);
            node_queue.addLast(null);

            LinkedList<Integer> level_list = new LinkedList<Integer>();
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
                    level_list = new LinkedList<Integer>();
                    // prepare for the next level
                    if (node_queue.size() > 0)
                        node_queue.addLast(null);
                    is_order_left = !is_order_left;
                }
            }
            return results;
        }
    }


    /*
    深度优先遍历
     */
    static class Solution2 {
        protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
            if (level >= results.size()) {
                LinkedList<Integer> newLevel = new LinkedList<Integer>();
                newLevel.add(node.val);
                results.add(newLevel);
            } else {
                if (level % 2 == 0)
                    results.get(level).add(node.val);
                else
                    results.get(level).add(0, node.val);
            }

            if (node.left != null) DFS(node.left, level + 1, results);
            if (node.right != null) DFS(node.right, level + 1, results);
        }

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            DFS(root, 0, results);
            return results;
        }
    }

}
