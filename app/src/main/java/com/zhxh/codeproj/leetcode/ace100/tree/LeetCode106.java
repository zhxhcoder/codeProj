package com.zhxh.codeproj.leetcode.ace100.tree;


import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        TreeNode.prettyPrintTree(new Solution().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
        TreeNode.prettyPrintTree(new Solution2().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    }

    /*
    递归
     */
    static class Solution {
        int post_idx;
        int[] postorder;
        int[] inorder;
        Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // 如果这里没有节点构造二叉树了，就结束
            if (in_left > in_right) {
                return null;
            }

            // 选择 post_idx 位置的元素作为当前子树根节点
            int root_val = postorder[post_idx];
            TreeNode root = new TreeNode(root_val);

            // 根据 root 所在位置分成左右两棵子树
            int index = idx_map.get(root_val);

            // 下标减一
            post_idx--;
            // 构造右子树
            root.right = helper(index + 1, in_right);
            // 构造左子树
            root.left = helper(in_left, index - 1);
            return root;
        }

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.postorder = postorder;
            this.inorder = inorder;
            // 从后序遍历的最后一个元素开始
            post_idx = postorder.length - 1;

            // 建立（元素，下标）键值对的哈希表
            int idx = 0;
            for (Integer val : inorder) {
                idx_map.put(val, idx++);
            }

            return helper(0, inorder.length - 1);
        }
    }

    /*
    迭代
     */
    static class Solution2 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (postorder == null || postorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = inorder.length - 1;
            for (int i = postorder.length - 2; i >= 0; i--) {
                int postorderVal = postorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.right = new TreeNode(postorderVal);
                    stack.push(node.right);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex--;
                    }
                    node.left = new TreeNode(postorderVal);
                    stack.push(node.left);
                }
            }
            return root;
        }
    }
}
