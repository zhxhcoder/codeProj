package com.zhxh.codeproj.leetcode.day3;

/*
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

示例 1：

输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例2：

输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：

输入：nums = [1], target = 0
输出：-1


提示：

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums 中的每个值都 独一无二
题目数据保证 nums 在预先未知的某个下标上进行了旋转
-104 <= target <= 104
 */
public class LeetCode33 {
    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 1));
    }

    static class Solution {
        //递归求解
        public int search(int[] nums, int target) {
            //对非法输入或不满足helper的情况提前处理
            if (nums == null || nums.length == 0) return -1;
            if (nums.length == 1) return target == nums[0] ? 0 : -1;

            return helper(nums, target, 0, nums.length - 1);
        }

        //递归辅助函数
        public int helper(int[] nums, int target, int left, int right) {
            //确定中间指针
            int mid = (left + right) >>> 1;

            //递归退出条件
            //找到了
            if (nums[mid] == target) return mid;
            //没找到
            if (left == right && nums[left] != target) return -1;

            //前一半有序
            if (nums[mid] >= nums[left]) {
                //答案在前一半,因为前面退出条件对=target已经做了判定，这里就不存在相等了(target = nums[mid])
                if (target >= nums[left] && target < nums[mid]) {
                    //同样此处的=mid已经被排除了(right = mid)
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            //递归的求解
            return helper(nums, target, left, right);
        }
    }
}

