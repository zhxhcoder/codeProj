package com.zhxh.codeproj.leetcode;

/*
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */
class LeetCode96 {
    public static void main(String[] args) {
        System.out.println(new Solution1().numTrees(3));
    }

    /*
    本问题可以用动态规划求解。
给定一个有序序列 1 ... n，为了根据序列构建一棵二叉搜索树。我们可以遍历每个数字 i，将该数字作为树根，1 ... (i-1) 序列将成为左子树，(i+1) ... n 序列将成为右子树。于是，我们可以递归地从子序列构建子树。
在上述方法中，由于根各自不同，每棵二叉树都保证是独特的。

可见，问题可以分解成规模较小的子问题。因此，我们可以存储并复用子问题的解，而不是递归的（也重复的）解决这些子问题，这就是动态规划法。

     */
    static class Solution1 {
        public int numTrees(int n) {
            int[] G = new int[n + 1];
            G[0] = 1;
            G[1] = 1;

            for (int i = 2; i <= n; ++i) {
                for (int j = 1; j <= i; ++j) {
                    G[i] += G[j - 1] * G[i - j];
                }
            }
            return G[n];
        }
    }

    /*
    事实上 G(n)G(n)函数的值被称为 卡塔兰数
 。卡塔兰数更便于计算的定义如下:

证明过程可以参考上述文献，此处略去。

     */
    static class Solution2 {
        public int numTrees(int n) {
            // Note: we should use long here instead of int, otherwise overflow
            long C = 1;
            for (int i = 0; i < n; ++i) {
                C = C * 2 * (2 * i + 1) / (i + 2);
            }
            return (int) C;
        }
    }

}
