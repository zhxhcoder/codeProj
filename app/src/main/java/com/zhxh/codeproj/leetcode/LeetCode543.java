package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.TreeNode;

public class LeetCode543 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode node0 = TreeNode.buildTree(new Integer[]{1, 2, 2, null, 3, null, 3});


        System.out.println(solution.depth(node0));
        System.out.println(solution.diameterOfBinaryTree(node0));
    }

    static class Solution {
        int ans;

        public int diameterOfBinaryTree(TreeNode root) {
            ans = 1;
            depth(root);
            return ans - 1;
        }

        public int depth(TreeNode node) {
            if (node == null) return 0;
            int L = depth(node.left);
            int R = depth(node.right);
            ans = Math.max(ans, L + R + 1);
            return Math.max(L, R) + 1;
        }

    }
}
