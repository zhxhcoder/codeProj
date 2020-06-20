package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.TreeNode;

public class LeetCode226 {



    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode right = invertTree(root.right);
            TreeNode left = invertTree(root.left);
            root.left = right;
            root.right = left;
            return root;
        }
    }
}
