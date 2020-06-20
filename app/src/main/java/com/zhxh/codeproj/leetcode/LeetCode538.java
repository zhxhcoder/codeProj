package com.zhxh.codeproj.leetcode;

import com.zhxh.codeproj.leetcode.support.TreeNode;

public class LeetCode538 {


    class Solution {
        private int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            if (root != null) {
                convertBST(root.right);
                sum += root.val;
                root.val = sum;
                convertBST(root.left);
            }
            return root;
        }
    }
}
