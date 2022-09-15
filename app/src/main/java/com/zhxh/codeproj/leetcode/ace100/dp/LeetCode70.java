package com.zhxh.codeproj.leetcode.ace100.dp;

/*
70. 爬楼梯
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？



示例 1：

输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶
示例 2：

输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶


提示：

1 <= n <= 45

 */
public class LeetCode70 {
    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(30));
        System.out.println(new Solution2().climbStairs(30));
        System.out.println(new Solution3().climbStairs(30));
        System.out.println(new Solution4().climbStairs(30));
        System.out.println(new Solution5().climbStairs(30));
        System.out.println(new Solution6().climbStairs(30));
    }

    /*
    官方方法一：动态规划
     */
    static class Solution {
        public int climbStairs(int n) {
            int p = 0, q = 0, r = 1;
            for (int i = 1; i <= n; ++i) {
                p = q;
                q = r;
                r = p + q;
            }
            return r;
        }
    }

    /*
    动态规划-空间换时间
     */
    static class Solution2 {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            //长度为n会越界
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }

    /*
     递归算法-超时
     */
    static class Solution3 {
        public int climbStairs(int n) {
            if (n == 1) return 1;
            if (n == 2) return 2;
            return climbStairs(n - 2) + climbStairs(n - 1);
        }
    }

    /*
    循环的方式
     */
    static class Solution4 {
        public int climbStairs(int n) {
            int p = 0, q = 0, r = 1;
            for (int i = 1; i <= n; ++i) {
                p = q;
                q = r;
                r = p + q;
            }
            return r;
        }
    }

    static class Solution5 {
        public int climbStairs(int n) {
            int num = 0;
            int m = 1;
            int k = 2;
            if (n == 1) {
                return m;
            }
            if (n == 2) {
                return k;
            }
            for (int i = 3; i <= n; i++) {
                num = m + k;
                m = k;
                k = num;
            }
            return num;
        }
    }

    /*
    dp用法
     */
    static class Solution6 {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            //初始化已知值
            dp[0] = 1;
            dp[1] = 1;
            if (n == 1) return dp[1];
            for (int i = 2; i <= n; i++) {
                //变换方程
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}
