package com.zhxh.codeproj.leetcode.tree;


/*
给定一个整数 n，求以1 ...n为节点组成的二叉搜索树有多少种？

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
        System.out.println(new Solution().numTrees(3));
    }


    /*
    直觉

本问题可以用动态规划求解。

给定一个有序序列 1 ... n，为了根据序列构建一棵二叉搜索树。我们可以遍历每个数字 i，将该数字作为树根，1 ... (i-1) 序列将成为左子树，(i+1) ... n 序列将成为右子树。
于是，我们可以递归地从子序列构建子树。
在上述方法中，由于根各自不同，每棵二叉树都保证是独特的。

可见，问题可以分解成规模较小的子问题。因此，我们可以存储并复用子问题的解，而不是递归的（也重复的）解决这些子问题，这就是动态规划法。

算法：

问题是计算不同二叉搜索树的个数。为此，我们可以定义两个函数：

G(n): 长度为n的序列的不同二叉搜索树个数。

F(i,n): 以i为根的不同二叉搜索树个数(1 <= i<=n)。

G(n)= ∑G(i−1)⋅G(n−i)
     */
    static class Solution {
        public int numTrees(int n) {
            /*长度为n的序列的不同二叉搜索树的个数*/
            int[] G = new int[n + 1];
            G[0] = 1;
            G[1] = 1;

            for (int k = 2; k <= n; ++k) {
                for (int i = 1; i <= k; ++i) {
                    G[k] += G[i - 1] * G[k - i];
                }
            }
            return G[n];
        }
    }
}
