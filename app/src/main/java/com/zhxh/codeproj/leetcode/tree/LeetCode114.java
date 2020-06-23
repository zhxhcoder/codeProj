package com.zhxh.codeproj.leetcode.tree;

import java.util.Stack;

/*
给定一个二叉树，原地将它展开为一个单链表。

 

例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

 */
class LeetCode114 {
    public static void main(String[] args) {

    }

    /*
    可以发现展开的顺序其实就是二叉树的先序遍历。算法和 94 题中序遍历的 Morris 算法有些神似，我们需要两步完成这道题。

将左子树插入到右子树的地方
将原来的右子树接到左子树的最右边节点
考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null

     */
    public void flatten1(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    /*
    题目中，要求说是 in-place，之前一直以为这个意思就是要求空间复杂度是 O(1)O(1)。
    偶然看见评论区大神的解释， in-place 的意思可能更多说的是直接在原来的节点上改变指向，空间复杂度并没有要求。所以这道题也可以用递归解一下。


     */

    public void flatten2(TreeNode root) {
        Stack<TreeNode> toVisit = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null || !toVisit.isEmpty()) {
            while (cur != null) {
                toVisit.push(cur); // 添加根节点
                cur = cur.right; // 递归添加右节点
            }
            cur = toVisit.peek(); // 已经访问到最右的节点了
            // 在不存在左节点或者右节点已经访问过的情况下，访问根节点
            if (cur.left == null || cur.left == pre) {
                toVisit.pop();
                /**************修改的地方***************/
                cur.right = pre;
                cur.left = null;
                /*************************************/
                pre = cur;
                cur = null;
            } else {
                cur = cur.left; // 左节点还没有访问过就先访问左节点
            }
        }
    }

    /*
    解法二中提到如果用先序遍历的话，会丢失掉右孩子，除了用后序遍历，还有没有其他的方法避免这个问题。在 Discuss 又发现了一种解法。

为了更好的控制算法，所以我们用先序遍历迭代的形式，正常的先序遍历代码如下，

     */
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;
        while (!s.isEmpty()) {
            TreeNode temp = s.pop();
            /***********修改的地方*************/
            if (pre != null) {
                pre.right = temp;
                pre.left = null;
            }
            /********************************/
            if (temp.right != null) {
                s.push(temp.right);
            }
            if (temp.left != null) {
                s.push(temp.left);
            }
            /***********修改的地方*************/
            pre = temp;
            /********************************/
        }
    }


}
