package com.zhxh.codeproj.leetcode;

public class LeetCode283 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] nums = {3, 4, 2, 0, 0, 3, 1, 2, 6, 0};

        solution.moveZeroes(nums);

        for (int item : nums) {
            System.out.println(item);
        }
    }

    static class Solution {
        public void moveZeroes(int[] nums) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[index] = nums[i];
                    index++;
                }
            }
            for (; index < nums.length; index++) {
                nums[index] = 0;
            }
        }
    }
}