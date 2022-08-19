package com.zhxh.codeproj.leetcode.day5;

import java.util.HashMap;
import java.util.Map;

/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例2:

输入: [4,1,2,1,2]
输出: 4

 */
public class LeetCode136 {
    public static void main(String[] args) {
        System.out.println(new Solution().singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println(new Solution().singleNumber2(new int[]{4, 1, 2, 1, 2}));
    }

    static class Solution {
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer i : nums) {
                Integer count = map.get(i);
                count = count == null ? 1 : ++count;
                map.put(i, count);
            }
            for (Integer i : map.keySet()) {
                Integer count = map.get(i);
                if (count == 1) {
                    return i;
                }
            }
            return -1; //找不到
        }

        /*
        位运算
         */
        public int singleNumber2(int[] nums) {
            int ans = nums[0];
            if (nums.length > 1) {
                for (int i = 1; i < nums.length; i++) {
                    ans = ans ^ nums[i];
                }
            }
            return ans;
        }
    }
}
