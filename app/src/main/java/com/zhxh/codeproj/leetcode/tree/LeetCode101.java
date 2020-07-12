package com.zhxh.codeproj.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

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
        Solution1 solution1 = new Solution1();

        TreeNode node0 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3});
        TreeNode node1 = TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, 3, 4, 4, 3});

        System.out.println(solution.isSymmetric(node0));
        System.out.println(solution.isSymmetric(node1));

        System.out.println(solution1.isSymmetric(node0));
        System.out.println(solution1.isSymmetric(node1));
    }

    /*
    递归
     */
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

    /*
    迭代
     */
    static class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            return check(root, root);
        }

        public boolean check(TreeNode u, TreeNode v) {
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.offer(u);
            q.offer(v);
            while (!q.isEmpty()) {
                u = q.poll();
                v = q.poll();
                if (u == null && v == null) {
                    continue;
                }
                if ((u == null || v == null) || (u.val != v.val)) {
                    return false;
                }

                q.offer(u.left);
                q.offer(v.right);

                q.offer(u.right);
                q.offer(v.left);
            }
            return true;
        }
    }
}
