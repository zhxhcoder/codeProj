package com.zhxh.codeproj.leetcode.hot100;

/*
给你一个整数数组 nums，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个32-位 整数。

子数组 是数组的连续子序列。



示例 1:

输入: nums = [2,3,-2,4]
输出: 6
解释:子数组 [2,3] 有最大乘积 6。
示例 2:

输入: nums = [-2,0,-1]
输出: 0
解释:结果不能为 2, 因为 [-2,-1] 不是子数组。


提示:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
nums 的任何前缀或后缀的乘积都 保证是一个 32-位 整数

 */
public class LeetCode152 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(new Solution2().maxProduct(new int[]{2, 3, -2, 4}));
    }

    /*
    暴力解法
     */
    static class Solution {
        public int maxProduct(int[] nums) {
            //思路：暴力穷举所有子数组，然后得出最大值
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            long max = Long.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                int numCount = 1;
                for (int j = i; j < len; j++) {
                    numCount *= nums[j];
                    max = Math.max(numCount, max);
                }
            }
            return (int) max;
        }
    }

    /*
    动态规划
     */
    static class Solution2 {
        public int maxProduct(int[] nums) {
            int[] maxF = new int[nums.length];
            int[] minF = new int[nums.length];
            maxF[0] = nums[0];
            minF[0] = nums[0];
            int ans = nums[0];
            for (int i = 1; i < nums.length; ++i) {
                maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
                minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
                ans = Math.max(ans, maxF[i]);
            }
            return ans;
        }
    }
}
