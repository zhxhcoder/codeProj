package com.zhxh.codeproj.leetcode.offer75;

import java.util.*;

/*
剑指 Offer 03. 数组中重复的数字
找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3

限制：
2 <= n <= 100000
 */
public class Offer03 {
    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println(new Solution2().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
        System.out.println(new Solution3().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }

    /*
    方法1： 排序后，遍历
     */
    static class Solution {
        public int findRepeatNumber(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return nums[i];
                }
            }
            throw new IllegalArgumentException("无重复数据");
        }
    }

    /*
    方法2：HashSet求重复
     */
    static class Solution2 {
        public int findRepeatNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    return num;
                }
                set.add(num);
            }
            throw new IllegalArgumentException("no answer");
        }
    }

    /*
    方法3： 前两种方法都未用到所有数字都在0-n-1的范围内，这个信息
    用不可能出现的作标记
     */
    static class Solution3 {
        public int findRepeatNumber(int[] nums) {
            //同时n大于nums数组中任何数
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                int val;
                if (nums[i] < 0) {//-n标记过，就先还原
                    val = nums[i] + n;
                } else {
                    val = nums[i];
                }
                //已经出现过了
                if (nums[val] < 0) {
                    return val;
                }
                //标记为负数
                nums[val] -= n;
            }
            throw new IllegalArgumentException();
        }
    }
}
