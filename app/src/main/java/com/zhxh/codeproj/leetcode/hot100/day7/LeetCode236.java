package com.zhxh.codeproj.leetcode.hot100.day7;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”



示例 1：


输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
示例 2：


输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
示例 3：

输入：root = [1,2], p = 1, q = 2
输出：1


提示：

树中节点数目在范围 [2, 105] 内。
-109 <= Node.val <= 109
所有 Node.val 互不相同 。
p != q
p 和 q 均存在于给定的二叉树中。

 */
public class LeetCode236 {
    public static void main(String[] args) {
        TreeNode.prettyPrintTree(new Solution().lowestCommonAncestor(
                TreeNode.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}),
                TreeNode.buildBinaryTree(new Integer[]{5, 1, 6, 2, 0, 8, null, null, 7, 4}),
                TreeNode.buildBinaryTree(new Integer[]{1, 6, 2, 0, 8, null, null, 7, 4})));

        TreeNode.prettyPrintTree(new Solution2().lowestCommonAncestor(
                TreeNode.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}),
                TreeNode.buildBinaryTree(new Integer[]{5, 1, 6, 2, 0, 8, null, null, 7, 4}),
                TreeNode.buildBinaryTree(new Integer[]{1, 6, 2, 0, 8, null, null, 7, 4})));
    }

    static class Solution {
        private TreeNode ans;

        public Solution() {
            this.ans = null;
        }

        private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return false;
            boolean lson = dfs(root.left, p, q);
            boolean rson = dfs(root.right, p, q);
            if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
                ans = root;
            }
            return lson || rson || (root.val == p.val || root.val == q.val);
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            this.dfs(root, p, q);
            return this.ans;
        }
    }

    /*
    递归-存储父节点
     */
    static class Solution2 {
        Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
        Set<Integer> visited = new HashSet<Integer>();

        public void dfs(TreeNode root) {
            if (root.left != null) {
                parent.put(root.left.val, root);
                dfs(root.left);
            }
            if (root.right != null) {
                parent.put(root.right.val, root);
                dfs(root.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root);
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            while (q != null) {
                if (visited.contains(q.val)) {
                    return q;
                }
                q = parent.get(q.val);
            }
            return null;
        }
    }
}
