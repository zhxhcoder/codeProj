package com.zhxh.codeproj.leetcode._tree;


import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.Random;

/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。

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
        TreeNode.prettyPrintTree(new Solution().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
        TreeNode.prettyPrintTree(new Solution2().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
        TreeNode.prettyPrintTree(new Solution3().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }

    /*
    方法一：中序遍历，总是选择中间位置左边的数字作为根节点
     */

    static class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        public TreeNode helper(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            // 总是选择中间位置左边的数字作为根节点
            int mid = (left + right) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, left, mid - 1);
            root.right = helper(nums, mid + 1, right);
            return root;
        }
    }

    /*
    方法二：中序遍历，总是选择中间位置右边的数字作为根节点
     */
    static class Solution2 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        public TreeNode helper(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }

            // 总是选择中间位置右边的数字作为根节点
            int mid = (left + right + 1) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, left, mid - 1);
            root.right = helper(nums, mid + 1, right);
            return root;
        }
    }

    /*
    方法三：中序遍历，选择任意一个中间位置数字作为根节点
     */
    static class Solution3 {
        Random rand = new Random();

        public TreeNode sortedArrayToBST(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        public TreeNode helper(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            // 选择任意一个中间位置数字作为根节点
            int mid = (left + right + rand.nextInt(2)) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, left, mid - 1);
            root.right = helper(nums, mid + 1, right);
            return root;
        }
    }
}
