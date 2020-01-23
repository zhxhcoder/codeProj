package com.zhxh.codeproj.leetcode;

public class LeetCode53 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(nums));
    }

    static class Solution {
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            int currSum = nums[0], maxSum = nums[0];

            for (int i = 1; i < n; ++i) {
                currSum = Math.max(nums[i], currSum + nums[i]);
                maxSum = Math.max(maxSum, currSum);
            }
            return maxSum;
        }
    }
}

