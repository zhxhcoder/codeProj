package com.zhxh.codeproj.leetcode.array;

/*
寻找峰值
峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞。

示例 1:

输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。
示例 2:

输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
说明:

你的解法应该是 O(logN) 时间复杂度的。

 */
class LeetCode162 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(new Solution2().findPeakElement(nums));
        System.out.println(new Solution().findPeakElement(nums));
        System.out.println(new Solution3().findPeakElement(nums));
    }

    //方法一: 线性扫描
    static class Solution {
        public int findPeakElement(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1])
                    return i;
            }
            return nums.length - 1;
        }
    }

    //方法二：递归二分查找
    static class Solution2 {
        public int findPeakElement(int[] nums) {
            return search(nums, 0, nums.length - 1);
        }

        public int search(int[] nums, int l, int r) {
            if (l == r)
                return l;
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                return search(nums, l, mid);
            return search(nums, mid + 1, r);
        }
    }

    //方法三：迭代二分查找
    static class Solution3 {
        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] > nums[mid + 1])
                    r = mid;
                else
                    l = mid + 1;
            }
            return l;
        }
    }

}
