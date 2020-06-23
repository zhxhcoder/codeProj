package com.zhxh.codeproj.leetcode.tree;

/*

给定一个二叉树，检查它是否是镜像对称的。



例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3


但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3


进阶：

你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class LeetCode101 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode node0 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3});
        TreeNode node1 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, 3, 4, 4, 3});

        System.out.println(solution.isSymmetric(node0));
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
    }

}
