package com.zhxh.codeproj.leetcode.hot100;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

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
        System.out.println(new Solution().isSymmetric(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3})));
        System.out.println(new Solution().isSymmetric(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, 3, 4, 4, 3})));

        System.out.println(new Solution2().isSymmetric(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, null, 3, null, 3})));
        System.out.println(new Solution2().isSymmetric(TreeNode.buildBinaryTree(new Integer[]{1, 2, 2, 3, 4, 4, 3})));
    }

    /*
    递归
    如果一个树的左子树与右子树镜像对称，那么这个树是对称的
    因此，该问题可以转化为：两个树在什么情况下互为镜像
    如果同时满足下面的条件，两个树互为镜像：
    - 他们的两个根节点具有相同的值
    - 每个树的右子树都与另一个树的左子树镜像对称
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
    方法一 中我们用递归的方法实现了对称性的判断，如何用迭代实现，首先我们引入一个队列，
    这是把递归程序写成迭代程序的常用方法。初始化时我们把根节点入队两次。每次提取两个结点
    并比较他们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），然后将两个结点
    的左右字节点按相反的顺序插入队列中。
    当队列为空时，或者我们检测不到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。

     */
    static class Solution2 {
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
