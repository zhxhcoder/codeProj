package com.zhxh.codeproj.leetcode.hot100.day10;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

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
        System.out.println(new Solution().diameterOfBinaryTree(TreeNode.buildBinaryTree(new Integer[]{1, 2, 3, 4, 5, null, null})));
        System.out.println(new Solution2().diameterOfBinaryTree(TreeNode.buildBinaryTree(new Integer[]{1, 2, 3, 4, 5, null, null})));
    }

    /*
    深度优先搜索
     */
    static class Solution {
        int ans;

        public int diameterOfBinaryTree(TreeNode root) {
            ans = 1;
            depth(root);
            return ans - 1;
        }

        /*
         递归函数三要素：
         1. 子问题原问题做同样的事
         2. 需要有一个让递归结束的出口
         3. 递归表达式
         */
        public int depth(TreeNode node) {
            if (node == null) {
                return 0; // 访问到空节点了，返回0
            }
            int L = depth(node.left); // 左儿子为根的子树的深度
            int R = depth(node.right); // 右儿子为根的子树的深度
            ans = Math.max(ans, L + R + 1); // 计算d_node 节点数 即L+R+1 并更新ans
            return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
        }
    }

    static class Solution2 {
        int ans;

        public int diameterOfBinaryTree(TreeNode root) {
            ans = 0;
            depth(root);
            return ans;
        }

        /*
         递归函数三要素：
         1. 子问题原问题做同样的事
         2. 需要有一个让递归结束的出口
         3. 递归表达式
         */
        public int depth(TreeNode node) {
            if (node == null) {
                return 0; // 访问到空节点了，返回0
            }
            int L = depth(node.left); // 左儿子为根的子树的深度
            int R = depth(node.right); // 右儿子为根的子树的深度
            ans = Math.max(ans, L + R); // 并更新ans
            return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
        }
    }
}
