package com.zhxh.codeproj.leetcode.hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
  1. 两数之和
  给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。

  你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

  
  示例:

  给定 nums = [2, 7, 11, 15], target = 9

  因为 nums[0] + nums[1] = 2 + 7 = 9
  所以返回 [0, 1]

 */


public class LeetCode01 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{7, 2, 12, 8, 15}, 14)));
        System.out.println(Arrays.toString(new Solution2().twoSum(new int[]{7, 2, 12, 8, 15}, 14)));
        System.out.println(Arrays.toString(new Solution3().twoSum(new int[]{7, 2, 12, 8, 15}, 14)));
        System.out.println(Arrays.toString(new Solution4().twoSum(new int[]{7, 2, 12, 8, 15}, 14)));
    }

    /*
    暴力解法
     */
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
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

    static class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            Iterator it = map.keySet().iterator();
            int key, value;
            while (it.hasNext()) {
                key = (int) it.next();
                value = map.get(key);
                int left = target - key;
                if (map.containsKey(left) && value != map.get(left)) {
                    return new int[]{value, map.get(left)};
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }

    static class Solution3 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int left = target - nums[i];
                if (map.containsKey(left) && map.get(left) != i) {
                    return new int[]{i, map.get(left)};
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }

    static class Solution4 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int left = target - nums[i];
                if (map.containsKey(left)) {
                    return new int[]{map.get(left), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("未找到");
        }
    }
}
