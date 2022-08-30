package com.zhxh.codeproj.leetcode.hot100.day10;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
输出:
合并后的树:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
注意: 合并必须从两个树的根节点开始。
 */
public class LeetCode617 {
    public static void main(String[] args) {
        TreeNode.prettyPrintTree(new Solution().mergeTrees(
                TreeNode.buildBinaryTree(new Integer[]{1, 3, 2, 5}),
                TreeNode.buildBinaryTree(new Integer[]{2, 1, 3, null, 4, null, 7})));

        TreeNode.prettyPrintTree(new Solution2().mergeTrees(
                TreeNode.buildBinaryTree(new Integer[]{1, 3, 2, 5}),
                TreeNode.buildBinaryTree(new Integer[]{2, 1, 3, null, 4, null, 7})));
    }

    /*
    递归-深度优先搜索
     */
    static class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null)
                return t2;
            if (t2 == null)
                return t1;
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }
    }

    /*
    广度优先搜索
     */
    static class Solution2 {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            TreeNode merged = new TreeNode(t1.val + t2.val);
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
            Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
            queue.offer(merged);
            queue1.offer(t1);
            queue2.offer(t2);
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node = queue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
                TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
                if (left1 != null || left2 != null) {
                    if (left1 != null && left2 != null) {
                        TreeNode left = new TreeNode(left1.val + left2.val);
                        node.left = left;
                        queue.offer(left);
                        queue1.offer(left1);
                        queue2.offer(left2);
                    } else if (left1 != null) {
                        node.left = left1;
                    } else if (left2 != null) {
                        node.left = left2;
                    }
                }
                if (right1 != null || right2 != null) {
                    if (right1 != null && right2 != null) {
                        TreeNode right = new TreeNode(right1.val + right2.val);
                        node.right = right;
                        queue.offer(right);
                        queue1.offer(right1);
                        queue2.offer(right2);
                    } else if (right1 != null) {
                        node.right = right1;
                    } else {
                        node.right = right2;
                    }
                }
            }
            return merged;
        }
    }
}
