package com.zhxh.codeproj.leetcode.day8;

import com.zhxh.codeproj.leetcode.__base.TreeNode;

import java.util.HashMap;

/*
小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。

除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。

给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。



示例 1:



输入: root = [3,2,3,null,3,null,1]
输出: 7 
解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
示例 2:



输入: root = [3,4,5,1,3,null,1]
输出: 9
解释:小偷一晚能够盗取的最高金额 4 + 5 = 9


提示：

树的节点数在[1, 104] 范围内
0 <= Node.val <= 104

 */
public class LeetCode337 {
    public static void main(String[] args) {
        System.out.println(new Solution().rob(TreeNode.buildBinaryTree(new Integer[]{3, 2, 3, null, 3, null, 1})));
        System.out.println(new Solution2().rob(TreeNode.buildBinaryTree(new Integer[]{3, 2, 3, null, 3, null, 1})));
        System.out.println(new Solution3().rob(TreeNode.buildBinaryTree(new Integer[]{3, 2, 3, null, 3, null, 1})));
    }

    static class Solution {
        public int rob(TreeNode root) {
            if (root == null) return 0;

            int money = root.val;
            if (root.left != null) {
                money += (rob(root.left.left) + rob(root.left.right));
            }

            if (root.right != null) {
                money += (rob(root.right.left) + rob(root.right.right));
            }

            return Math.max(money, rob(root.left) + rob(root.right));
        }
    }


    static class Solution2 {
        public int rob(TreeNode root) {
            HashMap<TreeNode, Integer> memo = new HashMap<>();
            return robInternal(root, memo);
        }

        public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
            if (root == null) return 0;
            if (memo.containsKey(root)) return memo.get(root);
            int money = root.val;

            if (root.left != null) {
                money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
            }
            if (root.right != null) {
                money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
            }
            int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
            memo.put(root, result);
            return result;
        }
    }

    static class Solution3 {
        public int rob(TreeNode root) {
            int[] result = robInternal(root);
            return Math.max(result[0], result[1]);
        }

        public int[] robInternal(TreeNode root) {
            if (root == null) return new int[2];
            int[] result = new int[2];

            int[] left = robInternal(root.left);
            int[] right = robInternal(root.right);

            result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            result[1] = left[0] + right[0] + root.val;

            return result;
        }
    }
}
