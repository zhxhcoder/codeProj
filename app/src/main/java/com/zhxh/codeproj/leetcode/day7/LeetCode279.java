package com.zhxh.codeproj.leetcode.day7;

/*
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。



示例1：

输入：n = 12
输出：3
解释：12 = 4 + 4 + 4
示例 2：

输入：n = 13
输出：2
解释：13 = 4 + 9

提示：

1 <= n <= 104

 */
public class LeetCode279 {
    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(12));
        System.out.println(new Solution2().numSquares(12));
    }

    /*
    动态规划
     */
    static class Solution {
        public int numSquares(int n) {
            int[] f = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int minn = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    minn = Math.min(minn, f[i - j * j]);
                }
                f[i] = minn + 1;
            }
            return f[n];
        }
    }

    /*
    数学
     */
    static class Solution2 {
        public int numSquares(int n) {
            if (isPerfectSquare(n)) {
                return 1;
            }
            if (checkAnswer4(n)) {
                return 4;
            }
            for (int i = 1; i * i <= n; i++) {
                int j = n - i * i;
                if (isPerfectSquare(j)) {
                    return 2;
                }
            }
            return 3;
        }

        // 判断是否为完全平方数
        public boolean isPerfectSquare(int x) {
            int y = (int) Math.sqrt(x);
            return y * y == x;
        }

        // 判断是否能表示为 4^k*(8m+7)
        public boolean checkAnswer4(int x) {
            while (x % 4 == 0) {
                x /= 4;
            }
            return x % 8 == 7;
        }
    }
}