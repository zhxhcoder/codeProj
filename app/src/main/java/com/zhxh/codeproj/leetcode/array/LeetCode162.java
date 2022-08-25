package com.zhxh.codeproj.leetcode.array;

/*
寻找峰值
峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

你可以假设nums[-1] = nums[n] = -∞。

示例 1:

输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。
示例2:

输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5
解释: 你的函数可以返回索引 1，其峰值元素为 2；
    或者返回索引 5， 其峰值元素为 6。
说明:

你的解法应该是O(logN)时间复杂度的。

 */
class LeetCode162 {

    public static void main(String[] args) {
        System.out.println(new Solution().findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(new Solution2().findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        System.out.println(new Solution3().findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
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

    /*
    为什么二分查找大的那一半一定会有峰值呢？（即nums[mid]<nums[mid+1]时，mid+1~N一定存在峰值）
    我的理解是，首先已知 nums[mid+1]>nums[mid]，那么mid+2只有两种可能，一个是大于mid+1，一个是小于mid+1，小于mid+1的情况，那么mid+1就是峰值，大于mid+1的情况，继续向右推，
    如果一直到数组的末尾都是大于的，那么可以肯定最后一个元素是峰值，因为nums[nums.length]=负无穷
    即，上坡必有坡顶
     */

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
