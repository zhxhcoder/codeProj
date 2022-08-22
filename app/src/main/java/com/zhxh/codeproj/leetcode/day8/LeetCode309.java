package com.zhxh.codeproj.leetcode.day8;

/*

给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
class LeetCode309 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(new Solution2().maxProfit(new int[]{1, 2, 3, 0, 2}));
    }

    /*
    动态规划
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) return 0;

            //0表示本不持有，1表示持有，2表示当天卖出，不持有
            int[][] dp = new int[len][3];  //用二维数组记录当天各种情况的最优解(收益的最大值)
            dp[0][1] = -prices[0];  //第一天若持有，则收益为负；不持有则收益为零

            for (int i = 1; i < len; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);  //当天的本不持有可以由前一天本不持有或前一天卖出得到
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);  //当天的持有可以由前一天的持有或前一天的本不持有-当天的股票价格得到, 即买进一只股票
                dp[i][2] = dp[i - 1][1] + prices[i];  //当天卖出可以由前一天的持有+当天的股票价格得到, 即卖出手中的股票
            }
            return Math.max(dp[len - 1][0], dp[len - 1][2]);  //返回最后一天不持有股票的状态，此处可以得到收益的最大值
        }
    }

    static class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int f0 = -prices[0];
            int f1 = 0;
            int f2 = 0;
            for (int i = 1; i < n; ++i) {
                int newf0 = Math.max(f0, f2 - prices[i]);
                int newf1 = f0 + prices[i];
                int newf2 = Math.max(f1, f2);
                f0 = newf0;
                f1 = newf1;
                f2 = newf2;
            }
            return Math.max(f1, f2);
        }
    }
}
