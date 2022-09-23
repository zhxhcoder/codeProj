package com.zhxh.codeproj.leetcode.vip200;

import java.util.ArrayList;
import java.util.List;

/*
163.缺失的区间
给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。

示例：

输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
输出: ["2", "4->49", "51->74", "76->99"]
 */
public class LeetCode163 {
    public static void main(String[] args) {
        System.out.println(new Solution().findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
        System.out.println(new Solution2().findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
    }

    /*

    缺失的区间-双指针法
    - 使用双指针low、num，遍历nums添加对应范围即可；
    - 需要先向nums尾部条件upper+1.
     */
    static class Solution {
        public List<String> findMissingRanges(int[] nums, int lower, int upper) {
            List<String> res = new ArrayList<>();
            long pre = (long) lower - 1; // prevent 'int' overflow
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] - pre == 2) res.add(String.valueOf(pre + 1));
                else if (nums[i] - pre > 2) res.add((pre + 1) + "->" + (nums[i] - 1));
                pre = nums[i]; // 'int' to 'long'
            }
            if (upper - pre == 1) res.add(String.valueOf(pre + 1));
            else if (upper - pre > 1) res.add((pre + 1) + "->" + upper);
            return res;
        }
    }

    /*
    用一个左边界指针，主要考察数据溢出
    */
    static class Solution2 {
        public List<String> findMissingRanges(int[] nums, int lower, int upper) {
            List<String> res = new ArrayList<>();
            // 记录左边界指针
            long left = lower;
            for (int i = 0; i < nums.length; i++) {
                if (left + 1 == nums[i]) {
                    res.add(String.valueOf(left));
                } else if (left + 1 < nums[i]) {
                    res.add(left + "->" + (nums[i] - 1));
                }
                left = (long) nums[i] + 1;
            }
            // 最后与upper比较
            if (left == upper) {
                res.add(String.valueOf(left));
            } else if (left < upper) {
                res.add(left + "->" + upper);
            }
            return res;
        }
    }
}
