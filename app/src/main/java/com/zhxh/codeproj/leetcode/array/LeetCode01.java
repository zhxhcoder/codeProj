package com.zhxh.codeproj.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
  给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

  你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

   
  示例:

  给定 nums = [2, 7, 11, 15], target = 9

  因为 nums[0] + nums[1] = 2 + 7 = 9
  所以返回 [0, 1]

 */


public class LeetCode01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 8, 15};
        int target = 17;
        System.out.print(Arrays.toString(solution.twoSum3(nums, target)));
    }

    static class Solution {
        public int[] twoSum1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }

        public int[] twoSumHash(int[] nums, int target) {
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

        public int[] twoSum2(int[] nums, int target) {
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

        public int[] twoSum3(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int left = target - nums[i];
                if (map.containsKey(left)) {
                    return new int[]{map.get(left), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}
