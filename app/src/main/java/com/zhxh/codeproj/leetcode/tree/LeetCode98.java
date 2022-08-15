package com.zhxh.codeproj.leetcode.tree;

import com.zhxh.codeproj.leetcode.TreeNode;

import java.util.Stack;

/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例1:

输入:
    2
   / \
  1   3
输出: true
示例2:

输入:
    5
   / \
  1   4
    / \
   3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
    根节点的值为 5 ，但是其右子节点值为 4 。

 */
class LeetCode98 {
    public static void main(String[] args) {
        TreeNode node = TreeNode.buildBinaryTree(new Integer[]{2, 1, 5, null, 5});
        System.out.println(new Solution().isValidBST(node));
        System.out.println(new Solution1().isValidBST(node));
    }

    /*
    要解决这道题首先我们要了解二叉搜索树有什么性质可以给我们利用，
    由题目给出的信息我们可以知道：如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
     若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树。
     */
    static class Solution {
        public boolean helper(TreeNode node, Integer lower, Integer upper) {
            if (node == null) return true;

            int val = node.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;

            if (!helper(node.right, val, upper)) return false;
            if (!helper(node.left, lower, val)) return false;
            return true;
        }

        public boolean isValidBST(TreeNode root) {
            return helper(root, null, null);
        }
    }

    /*
    方法二：中序遍历
 思路和算法

 基于方法一中提及的性质，我们可以进一步知道二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。如果均大于说明这个序列是升序的，整棵树是二叉搜索树，
 否则不是，下面的代码我们使用栈来模拟中序遍历的过程。

 可能由读者不知道中序遍历是什么，我们这里简单提及一下，
 中序遍历是二叉树的一种遍历方式，它先遍历左子树，再遍历根节点，最后遍历右子树。而我们二叉搜索树保证了左子树的节点的值均小于根节点的值，根节点的值均小于右子树的值，因此中序遍历以后得到的序列一定是升序序列。

     */
    static class Solution1 {
        public boolean isValidBST(TreeNode root) {
            Stack<TreeNode> stack = new Stack();
            double inorder = -Double.MAX_VALUE;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                if (root.val <= inorder) return false;
                inorder = root.val;
                root = root.right;
            }
            return true;
        }
    }
}
