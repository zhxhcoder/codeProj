package com.zhxh.codeproj.leetcode.hot100.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。


示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：

输入：nums = []
输出：[]
示例 3：

输入：nums = [0]
输出：[]


提示：

0 <= nums.length <= 3000
-105 <= nums[i] <= 105


 */
public class LeetCode15 {
    public static void main(String[] args) {
        for (List<Integer> list : new Solution().threeSum(new int[]{0, 0, 0, 0})) {
            System.out.println(list);
        }

        System.out.println("---------------");

        for (List<Integer> list : new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4})) {
            System.out.println(list);
        }
    }

    /*
     双指针法
     -关键词：不可以包含重复（排序解决）
     -模式识别：利用排序避免重复答案
     -降低复杂度变成twoSum
     -利用双指针找到所有解
     */
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            int len = nums.length;
            //特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
            if (len < 3) return ans;
            //对数组进行排序。
            Arrays.sort(nums);
            //遍历排序后数组：
            for (int i = 0; i < len; i++) {
                //若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
                if (nums[i] > 0) break;
                //对于重复元素：跳过，避免出现重复解
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int L = i + 1;
                int R = len - 1;
                //令左指针 L=i+1，右指针 R=R=n−1，当 L<R时，执行循环：
                while (L < R) {
                    int sum = nums[i] + nums[L] + nums[R];
                    if (sum == 0) {//当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R移到下一位置，寻找新的解
                        ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) L++;
                        while (L < R && nums[R] == nums[R - 1]) R--;
                        L++;
                        R--;
                    } else if (sum < 0)//若和小于 0，说明 nums[L] 太小，L 右移
                        L++;
                    else if (sum > 0)//若和大于 0，说明 nums[R] 太大，R 左移
                        R--;
                }
            }
            return ans;
        }
    }
}
