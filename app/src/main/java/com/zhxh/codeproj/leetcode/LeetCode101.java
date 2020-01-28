package com.zhxh.codeproj.leetcode;

public class LeetCode101 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        Solution.TreeNode node1 = new Solution.TreeNode(1);
        Solution.TreeNode node2 = new Solution.TreeNode(2);
        Solution.TreeNode node3 = new Solution.TreeNode(3);
        Solution.TreeNode node4 = new Solution.TreeNode(4);
        Solution.TreeNode node5 = new Solution.TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;


        System.out.println(solution.isSymmetric(node1));

    }

    static class Solution {
        public boolean isSymmetric(TreeNode root) {
            return isMirror(root, root);
        }

        public boolean isMirror(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) return true;
            if (t1 == null || t2 == null) return false;
            return (t1.val == t2.val)
                    && isMirror(t1.right, t2.left)
                    && isMirror(t1.left, t2.right);
        }

        static public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }
    }

}
