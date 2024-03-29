package com.zhxh.codeproj.leetcode.ace100.dp;

/*
给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。
其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
 */
public class Knapsack {
    public static void main(String[] args) {
        System.out.println(new Solution().knapsack(
                new int[]{2, 4, 6},
                new int[]{60, 100, 120}, 9));
    }

    static class Solution {
        public int knapsack(int[] wt, int[] val, int W) {
            int N = wt.length;
            //dp[i][j]表示：对于前i个物品，当前背包的容量为j，这种情况下可以装的最大价值是dp[i][j]
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
                    int diff = j - wt[i - 1];
                    //在“上一个结果价值”和“把当前第i个物品装入背包里所得到价值”二者里选价值较大的
                    if (diff >= 0) {//因为从1个物品开始遍历
                        //差额+加上
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][diff] + val[i - 1]);
                    } else {
                        //不足以多个物品，上一行的值赋给下一行
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            //存储最大值
            int maxV = dp[N][W];
            //输出日志
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
