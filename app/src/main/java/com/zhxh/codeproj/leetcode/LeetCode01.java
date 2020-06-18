package com.zhxh.codeproj.leetcode;

import java.util.Arrays;

//两个数之和
public class LeetCode01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 8, 15};
        int target = 17;
        System.out.print(Arrays.toString(solution.findIndex(target, nums)));
    }

    static class Solution {
        public int[] findIndex(int target, int[] nums) {
            int[] index = {-1, -1};
            for (int i = 0; i < nums.length; i++) {
                int left = target - nums[i];
                index[0] = i;
                for (int j = 0; j < nums.length; j++) {
                    if (left == nums[j] && i != j) {
                        index[1] = j;
                        return index;
                    }
                }
            }
            return index;
        }
    }
}
