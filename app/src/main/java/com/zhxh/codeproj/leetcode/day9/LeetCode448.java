package com.zhxh.codeproj.leetcode.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。



示例 1：

输入：nums = [4,3,2,7,8,2,3,1]
输出：[5,6]
示例 2：

输入：nums = [1,1]
输出：[2]


提示：

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。

 */
public class LeetCode448 {
    public static void main(String[] args) {
        System.out.println(new Solution().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println(new Solution2().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println(new Solution3().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    static class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            HashMap<Integer, Boolean> hashTable = new HashMap<Integer, Boolean>();

            for (int i = 0; i < nums.length; i++) {
                hashTable.put(nums[i], true);
            }

            List<Integer> result = new LinkedList<Integer>();

            for (int i = 1; i <= nums.length; i++) {
                if (!hashTable.containsKey(i)) {
                    result.add(i);
                }
            }

            return result;
        }
    }

    static class Solution2 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            // 遍历原始数组中的每个元素
            for (int i = 0; i < nums.length; i++) {
                // 将值视为新索引
                int newIndex = Math.abs(nums[i]) - 1;
                // 检查这个新索引处值的大小如果大小为正，则将其设为负，从而表明数字 nums[i] 已出现或已被访问。
                if (nums[newIndex] > 0) {
                    nums[newIndex] *= -1;
                }
            }
            // 包含缺失数字的响应数组
            List<Integer> result = new LinkedList<Integer>();
            // 遍历从 1 到 N 的数字，并将所有具有正大小的数字添加到数组中
            for (int i = 1; i <= nums.length; i++) {

                if (nums[i - 1] > 0) {
                    result.add(i);
                }
            }
            return result;
        }
    }

    /*
    原地修改
     */
    static class Solution3 {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;
            for (int num : nums) {
                int x = (num - 1) % n;
                nums[x] += n;
            }
            List<Integer> ret = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (nums[i] <= n) {
                    ret.add(i + 1);
                }
            }
            return ret;
        }
    }
}
