package com.zhxh.codeproj.leetcode.hot100;

import java.util.Arrays;

/*
34. 在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。



示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]


提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
 */
public class LeetCode34 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{2, 3, 3, 3, 5, 6, 8}, 3)));
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{2, 2}, 2)));
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{}, 2)));

        System.out.println("----------------");

        System.out.println(Arrays.toString(new Solution().searchRange2(new int[]{2, 3, 3, 3, 5, 6, 8}, 3)));
        System.out.println(Arrays.toString(new Solution().searchRange2(new int[]{2, 2}, 2)));
        System.out.println(Arrays.toString(new Solution().searchRange2(new int[]{}, 2)));
        System.out.println("----------------");

        System.out.println(Arrays.toString(new Solution2().searchRange(new int[]{2, 3, 3, 3, 5, 6, 8}, 3)));
        System.out.println(Arrays.toString(new Solution2().searchRange(new int[]{2, 2}, 2)));
        System.out.println(Arrays.toString(new Solution2().searchRange(new int[]{}, 2)));
    }

    /*
    二分用这个模板就不会出错了。
    满足条件的都写l = mid或者r = mid，mid首先写成l + r >> 1，
    如果满足条件选择的是l = mid，那么mid那里就加个1，写成l + r + 1 >> 1。
    然后就是else对应的写法l = mid对应r = mid - 1，r = mid对应l = mid + 1。
     */
    static class Solution {
        /*
        二分查找算法

        - 二分查找的基本思想：在一个区间范围里看处在中间位置的元素的值nums[mid]与目标元素target大小关系，进而决定目标值落在哪一部分里
        - 目标元素target在有序数组中很可能存在多个。
        - 使用二分查找方法看到处在中间位置的元素的值nums[mid]恰好等于目标元素target的时候，还需要继续查找下去。
        - 此时容易陷入误区的是线性查找，正确的做法是继续二分查找。
         */
        public int[] searchRange(int[] nums, int target) {
            //第一个位置
            int leftIdx = binarySearch(nums, target, true);
            //最后一个位置
            int rightIdx = binarySearch(nums, target, false) - 1;
            if (leftIdx <= rightIdx &&
                    rightIdx < nums.length &&
                    nums[leftIdx] == target &&
                    nums[rightIdx] == target) {
                return new int[]{leftIdx, rightIdx};
            }
            return new int[]{-1, -1};
        }

        public int binarySearch(int[] nums, int target, boolean lower) {
            int left = 0, right = nums.length - 1, ans = nums.length;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > target || (lower && nums[mid] >= target)) {
                    right = mid - 1;
                    ans = mid;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }

        /*
        更简洁版本
         */
        public int[] searchRange2(int[] nums, int target) {
            return new int[]{find(nums, target, true), find(nums, target, false)};
        }

        public int find(int[] nums, int target, boolean minType) {
            int left = 0, right = nums.length - 1;
            int ans = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    ans = mid;
                    if (minType) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else if (target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

    static class Solution2 {
        private int extremeInsertionIndex(int[] nums, int target, boolean left) {
            int lo = 0;
            int hi = nums.length;

            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] > target || (left && target == nums[mid])) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            return lo;
        }

        public int[] searchRange(int[] nums, int target) {
            int[] targetRange = {-1, -1};

            int leftIdx = extremeInsertionIndex(nums, target, true);

            // 断言 `leftIdx` 在数组边界内，并且 `target` 实际上在 `nums` 中。
            if (leftIdx == nums.length || nums[leftIdx] != target) {
                return targetRange;
            }

            targetRange[0] = leftIdx;
            targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

            return targetRange;
        }
    }
}
