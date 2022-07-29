package com.zhxh.codeproj.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

示例：

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 */
public class LeetCode15 {
    public static void main(String[] args) {
        int[] height = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> ans = Solution.threeSum(height);

        for (List<Integer> list : ans) {
            System.out.println("------begin------");

            for (Integer item : list) {
                System.out.println(item);
            }
        }
    }

    /*
     算法流程：
     特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
     对数组进行排序。
     遍历排序后数组：
     若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
     对于重复元素：跳过，避免出现重复解
     令左指针 L=i+1，右指针 R=R=n−1，当 L<R时，执行循环：

     当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R移到下一位置，寻找新的解
     若和大于 00，说明 nums[R] 太大，RR 左移
     若和小于 00，说明 nums[L] 太小，LL 右移
     */
    public static class Solution {
        public static List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            int len = nums.length;
            if (len < 3) return ans;
            Arrays.sort(nums);

            for (int i = 0; i < len; i++) {
                if (nums[i] > 0) break;
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int L = i + 1;
                int R = len - 1;
                while (L < R) {
                    int sum = nums[i] + nums[L] + nums[R];
                    if (sum == 0) {
                        ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) L++;
                        while (L < R && nums[R] == nums[R - 1]) R--;
                        L++;
                        R--;
                    } else if (sum < 0) L++;
                    else if (sum > 0) R--;
                }
            }

            return ans;
        }
    }
}
