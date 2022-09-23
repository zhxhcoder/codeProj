package com.zhxh.codeproj.leetcode.vip200;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.Stack;

/*
156.上下翻转二叉树
给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
例子:
输入: [1,2,3,4,5]
    1
   / \
  2   3
 / \
4   5

输出: 返回二叉树的根 [4,5,2,#,#,3,1]
   4
  / \
 5   2
    / \
   3   1
说明:

对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
这里有一个例子:
   1
  / \
 2   3
    /
   4
    \
     5
上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].

 */
public class LeetCode156 {
    public static void main(String[] args) {
        TreeNode.prettyPrintTree(new Solution().upsideDownBinaryTree(TreeNode.buildBinaryTree(new Integer[]{1, 2, 3, 4, 5})));
        TreeNode.prettyPrintTree(new Solution2().upsideDownBinaryTree(TreeNode.buildBinaryTree(new Integer[]{1, 2, 3, 4, 5})));
    }

    /*
    递归，只要找到最左子树就行
    然后反转
    唯一要注意的一点是最后原本的根节点root的左右字数需要置null，否则会形成环
     */
    static class Solution {
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null || root.left == null) return root;
            TreeNode ans = helper(root.left, root);
            //防止形成环
            root.left = null;
            root.right = null;
            return ans;
        }

        public TreeNode helper(TreeNode left, TreeNode p) {
            TreeNode ans;
            if (left.left == null) {
                ans = left;
            } else {
                ans = helper(left.left, left);
            }
            left.left = p.right;
            left.right = p;
            return ans;
        }
    }

    /*
    迭代
     */
    static class Solution2 {
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null) return null;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode ans = null;
            TreeNode cur = root;
            //找最左子树
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            ans = stack.pop();
            cur = ans;
            //反转
            while (!stack.isEmpty()) {
                cur.right = stack.pop();
                cur.left = cur.right.right;
                cur = cur.right;
            }
            //防止形成环
            cur.left = null;
            cur.right = null;
            return ans;
        }
    }
}
