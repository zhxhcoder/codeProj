package com.zhxh.codeproj.leetcode;

public class LeetCode437 {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public int pathSum(TreeNode root, int sum) {
            return pathSum(root, sum, new int[1000], 0);
        }

        public int pathSum(TreeNode root, int sum, int[] array/*保存路径*/, int p/*指向路径终点*/) {
            if (root == null) {
                return 0;
            }
            int tmp = root.val;
            int n = root.val == sum ? 1 : 0;
            for (int i = p - 1; i >= 0; i--) {
                tmp += array[i];
                if (tmp == sum) {
                    n++;
                }
            }
            array[p] = root.val;
            int n1 = pathSum(root.left, sum, array, p + 1);
            int n2 = pathSum(root.right, sum, array, p + 1);
            return n + n1 + n2;
        }
    }


    static class Solution2 {

        /**
         * 求以 root 为根的二叉树，所有和为 sum 的路径；
         * 路径的开头不一定是 root，结尾也不一定是叶子节点；
         *
         * @param root
         * @param sum
         * @return
         */
        public int pathSum(TreeNode root, int sum) {

            if (root == null) {
                return 0;
            }

            return paths(root, sum)
                    + pathSum(root.left, sum)
                    + pathSum(root.right, sum);
        }

        private int paths(TreeNode root, int sum) {

            if (root == null) {
                return 0;
            }

            int res = 0;
            if (root.val == sum) {
                res += 1;
            }

            res += paths(root.left, sum - root.val);
            res += paths(root.right, sum - root.val);

            return res;
        }

    }

}
