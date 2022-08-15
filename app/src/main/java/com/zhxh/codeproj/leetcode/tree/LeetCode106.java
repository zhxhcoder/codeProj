package com.zhxh.codeproj.leetcode.tree;


import com.zhxh.codeproj.leetcode.TreeNode;

import java.util.HashMap;

/*

根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
 */
class LeetCode106 {
    public static void main(String[] args) {

    }

    static class Solution {
        int post_idx;
        int[] postorder;
        int[] inorder;
        HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // if there is no elements to construct subtrees
            if (in_left > in_right)
                return null;

            // pick up post_idx element as a root
            int root_val = postorder[post_idx];
            TreeNode root = new TreeNode(root_val);

            // root splits inorder list
            // into left and right subtrees
            int index = idx_map.get(root_val);

            // recursion
            post_idx--;
            // build right subtree
            root.right = helper(index + 1, in_right);
            // build left subtree
            root.left = helper(in_left, index - 1);
            return root;
        }

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.postorder = postorder;
            this.inorder = inorder;
            // start from the last postorder element
            post_idx = postorder.length - 1;

            // build a hashmap value -> its index
            int idx = 0;
            for (Integer val : inorder)
                idx_map.put(val, idx++);
            return helper(0, inorder.length - 1);
        }
    }

}
