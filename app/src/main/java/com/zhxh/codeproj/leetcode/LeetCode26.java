package com.zhxh.codeproj.leetcode;

public class LeetCode26 {

    int[] nums = {5, 4, 4, 6, 1, 2, 2, 2};

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }
}
