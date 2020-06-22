package com.zhxh.codeproj.leetcode.tree;


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
        System.out.println(new Solution().numTrees(3));
    }


    /*
    算法

问题是计算不同二叉搜索树的个数。为此，我们可以定义两个函数：

G(n): 长度为n的序列的不同二叉搜索树个数。

F(i,n): 以i为根的不同二叉搜索树个数(1 <= i<=n)。

     */
    static class Solution {
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
}
