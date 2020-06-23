package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode._bean.TreeNode;

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
        TreeNode node1 = TreeNode.buildBinaryTree(new Integer[]{1, 3, 2, 5});
        TreeNode node2 = TreeNode.buildBinaryTree(new Integer[]{2, 1, 3, null, 4, null, 7});

        TreeNode node = new Solution().mergeTrees(node1, node2);

        TreeNode.printBinaryTree(node);
    }

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
}
