package com.zhxh.codeproj.leetcode.hot100;

import java.util.HashSet;
import java.util.Set;

/*
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为O(n) 的算法解决此问题。

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9


提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109

 */
public class LeetCode128 {
    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    static class Solution {
        /*
        哈希表
         */
        public int longestConsecutive(int[] nums) {
            Set<Integer> num_set = new HashSet<Integer>();
            for (int num : nums) {
                num_set.add(num);
            }
            int longestStreak = 0;
            for (int num : num_set) {
                //我们考虑枚举数组中的每个数 x，考虑以其为起点，不断尝试匹配 x+1, x+2,⋯ 是否存在由于我们要枚举的数x一定是在数组中不存在前驱数x-1的，
                if (!num_set.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;

                    while (num_set.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }
            return longestStreak;
        }
    }
}
