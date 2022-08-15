package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode._base.TreeNode;

/*
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。



示例 :
给定二叉树

          1
         / \
        2   3
       / \
      4   5
返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。



注意：两结点之间的路径长度是以它们之间边的数目表示。

 */
public class LeetCode543 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode node0 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3});

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
