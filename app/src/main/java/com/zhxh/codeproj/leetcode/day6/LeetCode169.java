package com.zhxh.codeproj.leetcode.day6;

import java.util.Arrays;

/*

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。



示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
 */
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

