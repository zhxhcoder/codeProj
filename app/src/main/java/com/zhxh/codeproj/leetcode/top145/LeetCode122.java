package com.zhxh.codeproj.leetcode.top145;

/*
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。



示例 1：

输入：prices = [7,1,5,3,6,4]
输出：7
解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     总利润为 4 + 3 = 7 。
示例 2：

输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     总利润为 4 。
示例 3：

输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。


提示：

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104

 */
public class LeetCode122 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution2().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution3().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /*
    方法一：动态规划
    考虑到不能同时参与多笔交易，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
    定义dp[i][0]表示第i天交易完后手里没有股票的最大利润，
    定义dp[i][1]表示第i天交易完成后手里持有一支股票的最大利润（i从0开始）

     */
    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; ++i) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            return dp[n - 1][0];
        }
    }

    /*
    方法二：贪心
     */
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int ans = 0;
            int n = prices.length;
            for (int i = 1; i < n; ++i) {
                ans += Math.max(0, prices[i] - prices[i - 1]);
            }
            return ans;
        }
    }

    static class Solution3 {
        public int maxProfit(int[] prices) {
            int i = 0;
            int valley = prices[0];
            int peak = prices[0];
            int maxprofit = 0;
            while (i < prices.length - 1) {
                while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {//跌时
                    i++;
                }
                valley = prices[i];
                while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {//涨时
                    i++;
                }
                peak = prices[i];
                //峰谷之差
                maxprofit += peak - valley;
            }
            return maxprofit;
        }
    }
}
