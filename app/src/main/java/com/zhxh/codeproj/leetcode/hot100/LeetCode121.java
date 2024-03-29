package com.zhxh.codeproj.leetcode.hot100;

/*
给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。


示例 1：

输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
示例 2：

输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 没有交易完成, 所以最大利润为 0。

 */
public class LeetCode121 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{8, 7, 13, 1, 5, 3, 6, 4}));
        System.out.println(new Solution().maxProfit2(new int[]{8, 7, 13, 1, 5, 3, 6, 4}));
        System.out.println(new Solution().maxProfit3(new int[]{8, 7, 13, 1, 5, 3, 6, 4}));
    }

    static class Solution {
        /*
        暴力法
         */
        public int maxProfit(int[] prices) {
            int maxprofit = 0;
            //i取值到倒数第二个位置
            for (int i = 0; i <= prices.length - 2; i++) {
                for (int j = i + 1; j <= prices.length - 1; j++) {
                    int profit = prices[j] - prices[i];
                    if (profit > maxprofit) {
                        maxprofit = profit;
                    }
                }
            }
            return maxprofit;
        }

        /*
        一次遍历
        [7,12,1,5,3,6,4]
         */
        public int maxProfit2(int[] prices) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice) {
                    minprice = prices[i];
                } else if (prices[i] - minprice > maxprofit) {
                    maxprofit = prices[i] - minprice;
                }
            }
            return maxprofit;
        }

        /*
        动态规划
        一次遍历
        [7,12,1,5,3,6,4]
         */
        public int maxProfit3(int[] prices) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                int p = prices[i];
                //随着数组前移，如果当前价格，小于最小价格则采用最小的
                minprice = Math.min(minprice, p);
                //随着数组前移，如果有更大利润，则采用最大利润
                maxprofit = Math.max(maxprofit, p - minprice);
            }
            return maxprofit;
        }
    }
}
