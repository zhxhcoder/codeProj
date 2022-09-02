package com.zhxh.codeproj.leetcode.top145;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 

示例 1：

输入：nums = [1,2,3,1]
输出：true
示例 2：

输入：nums = [1,2,3,4]
输出：false
示例 3：

输入：nums = [1,1,1,3,3,4,3,2,4,2]
输出：true
 

提示：

1 <= nums.length <= 105
-109 <= nums[i] <= 109

 */
public class LeetCode217 {
    public static void main(String[] args) {
        System.out.println(new Solution().containsDuplicate(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution2().containsDuplicate(new int[]{1, 2, 3, 1}));
    }

    /*
    方法一：排序
     */
    static class Solution {
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    return true;
                }
            }
            return false;
        }
    }

    /*
    方法二：哈希表
     */
    static class Solution2 {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            for (int x : nums) {
                if (!set.add(x)) {
                    return true;
                }
            }
            return false;
        }
    }
}
