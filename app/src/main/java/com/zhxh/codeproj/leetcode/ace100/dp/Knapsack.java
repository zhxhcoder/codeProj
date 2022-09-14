package com.zhxh.codeproj.leetcode.ace100.dp;

/*
给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
 */
public class Knapsack {
    public static void main(String[] args) {
        System.out.println(new Solution().knapsack(
                new int[]{10, 20, 30},
                new int[]{60, 100, 120}, 50));
    }

    static class Solution {
        public int knapsack(int[] wt, int[] val, int W) {
            int N = wt.length;
            int[][] dp = new int[N + 1][W + 1];
            //初始化，其实默认为0
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= W; j++) {
                    dp[i][j] = 0;
                }
            }
            //对N个物品遍历
            for (int i = 1; i <= N; i++) {
                //最大载重
                for (int j = 0; j <= W; j++) {
                    //上一行的值赋给下一行
                    dp[i][j] = dp[i - 1][j];
                    //在“上一个结果价值”和“把当前第i个物品装入背包里所得到价值”二者里选价值较大的
                    if (j >= wt[i - 1]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - wt[i - 1]] + val[i - 1]);
                    }
                }
            }

            //存储最大值
            int maxV = dp[N][W];

            System.out.println("最大值:\t" + maxV);
            System.out.println("所选物品: ");

            while (N != 0) {
                if (dp[N][W] != dp[N - 1][W]) {
                    System.out.println("\t物品 " + N + " with wt = " + wt[N - 1] + " and Value = " + val[N - 1]);
                    W = W - wt[N - 1];
                }
                N--;
            }
            //返回问题解
            return maxV;
        }
    }
}
