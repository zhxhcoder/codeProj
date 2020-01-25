package com.zhxh.codeproj.leetcode;

public class LeetCode70 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.climbStairs(9));
    }

    static class Solution {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}
