package com.zhxh.codeproj.leetcode.tree;


/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

 */
class LeetCode108 {
    public static void main(String[] args) {

    }

    /*
    方法二：中序遍历：始终选择中间位置右边元素作为根节点
     */

    class Solution {
        int[] nums;

        public TreeNode helper(int left, int right) {
            if (left > right) return null;

            // always choose right middle node as a root
            int p = (left + right) / 2;
            if ((left + right) % 2 == 1) ++p;

            // inorder traversal: left -> node -> right
            TreeNode root = new TreeNode(nums[p]);
            root.left = helper(left, p - 1);
            root.right = helper(p + 1, right);
            return root;
        }

        public TreeNode sortedArrayToBST(int[] nums) {
            this.nums = nums;
            return helper(0, nums.length - 1);
        }
    }


}
