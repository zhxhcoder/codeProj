package com.zhxh.codeproj.leetcode;

public class LeetCode198 {

    public static void main(String[] args) {
        int[] nums = {3, 3, 24, 5, 2, 1, 9};

        Solution solution = new Solution();

        System.out.println(solution.rob(nums));
    }

    static class Solution {
        public int rob(int[] nums) {
            int prevMax = 0;
            int currMax = 0;
            for (int x : nums) {
                int temp = currMax;
                currMax = Math.max(prevMax + x, currMax);
                prevMax = temp;
            }
            return currMax;
        }
    }
}
