package com.zhxh.codeproj.leetcode.day3;

import com.zhxh.codeproj.leetcode._base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的中序遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶:递归算法很简单，你可以通过迭代算法完成吗？

 */
class LeetCode94 {
    public static void main(String[] args) {
        TreeNode node1 = TreeNode.buildBinaryTree(new Integer[]{6, 4, 9, 2, 5, 7, 10, 1, 3, null, null, null, 8});

        TreeNode.prettyPrintTree(node1);

        System.out.println(new Solution1().inOrderTraversal(node1));

        System.out.println(new Solution2().inOrderTraversal(node1));
        System.out.println(new Solution2().inOrderTraversal(node1));

        System.out.println(new Solution1().inOrderTraversal(node1));

        System.out.println(new Solution3().inOrderTraversal(node1));

        //System.out.println(TreeNode.binaryTree2List(node1));
    }

    /*
    第一种解决方法是使用递归。这是经典的方法，直截了当。我们可以定义一个辅助函数来实现递归。

     */
    static class Solution1 {
        public List<Integer> inOrderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List<Integer> res) {
            if (root != null) {
                //先左子树
                if (root.left != null) {
                    helper(root.left, res);
                }
                res.add(root.val);
                //后右子树
                if (root.right != null) {
                    helper(root.right, res);
                }
            }
        }
    }

    /*
    方法二：基于栈的遍历
     */
    static class Solution2 {
        public List<Integer> inOrderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {//一直取左子树知道叶子节点
                    stack.push(curr);//从根节点到最左叶节点依次入栈
                    curr = curr.left;//一直移动指针到左子树
                }
                curr = stack.pop();//子节点先出栈
                res.add(curr.val);//值加入结果列表
                curr = curr.right;//移动指针到右子树
            }
            return res;
        }
    }

    // 莫里斯中序遍历 不破坏树结构
    static class Solution3 {
        public List<Integer> inOrderTraversal(TreeNode root) {
            List<Integer> ldr = new ArrayList<Integer>();
            TreeNode cur = root;
            TreeNode pre = null;
            while (cur != null) {
                if (cur.left == null) {//左子树为空，输出当前节点，将其右孩子作为当前节点
                    ldr.add(cur.val);
                    cur = cur.right;
                } else {
                    pre = cur.left;//左子树
                    while (pre.right != null && pre.right != cur) {//找到前驱节点，即左子树中的最右节点
                        pre = pre.right;
                    }
                    //如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                    if (pre.right == null) {
                        pre.right = cur;
                        cur = cur.left;
                    }
                    //如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
                    if (pre.right == cur) {
                        pre.right = null;
                        ldr.add(cur.val);
                        cur = cur.right;
                    }
                }
            }
            return ldr;
        }
    }
}
