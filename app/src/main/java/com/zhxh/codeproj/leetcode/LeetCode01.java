package com.zhxh.codeproj.leetcode;

import java.util.Arrays;

//两个数之和
public class LeetCode01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 8, 15};
        int target = 17;
        System.out.print(Arrays.toString(solution.twoSum(target, nums)));
    }

    static class Solution {
        public int[] twoSum(int target, int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}
