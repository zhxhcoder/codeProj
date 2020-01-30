package com.zhxh.codeproj.leetcode;

import java.util.Arrays;

public class LeetCode169 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {3, 4, 2, 4, 2, 2, 2, 2, 2, 4, 3, 22, 2};

        System.out.print(solution.majorityElement(nums));

    }

    static class Solution {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }
}

