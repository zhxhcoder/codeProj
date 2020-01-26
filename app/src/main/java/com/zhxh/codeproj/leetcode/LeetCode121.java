package com.zhxh.codeproj.leetcode;

public class LeetCode121 {

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();

        System.out.println(solution1.maxProfit(prices));
        System.out.println(solution2.maxProfit(prices));
    }

    static class Solution1 {
        public int maxProfit(int[] prices) {
            int maxprofit = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    int profit = prices[j] - prices[i];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            return maxprofit;
        }
    }

    static class Solution2 {
        public int maxProfit(int[] prices) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice)
                    minprice = prices[i];
                else if (prices[i] - minprice > maxprofit)
                    maxprofit = prices[i] - minprice;
            }
            return maxprofit;
        }
    }
}
