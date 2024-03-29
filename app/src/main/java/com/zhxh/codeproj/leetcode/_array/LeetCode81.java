package com.zhxh.codeproj.leetcode._array;

/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组[0,0,1,2,2,5,6]可能变为[2,5,6,0,0,1,2])。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回true，否则返回false。

示例1:

输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true
示例2:

输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false
进阶:

这是 搜索旋转排序数组的延伸题目，本题中的nums 可能包含重复元素。
这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

 */
class LeetCode81 {
    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
    }

    static class Solution {
        public boolean search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return false;
            }
            int start = 0;
            int end = nums.length - 1;
            int mid;
            while (start <= end) {
                mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[start] == nums[mid]) {
                    start++;
                    continue;
                }
                //前半部分有序
                if (nums[start] < nums[mid]) {
                    //target在前半部分
                    if (nums[mid] > target && nums[start] <= target) {
                        end = mid - 1;
                    } else {  //否则，去后半部分找
                        start = mid + 1;
                    }
                } else {
                    //后半部分有序
                    //target在后半部分
                    if (nums[mid] < target && nums[end] >= target) {
                        start = mid + 1;
                    } else {  //否则，去后半部分找
                        end = mid - 1;
                    }
                }
            }
            //一直没找到，返回false
            return false;
        }
    }
}
