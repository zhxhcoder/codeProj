package com.zhxh.codeproj.leetcode.hot100.day3;

/*
给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释:连续子数组[4,-1,2,1] 的和最大，为6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

 */
public class LeetCode53 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(new Solution2().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    static class Solution {
        /*
        动态规划
         */
        public int maxSubArray(int[] nums) {
            int n = nums.length;
            int currSum = nums[0], maxSum = nums[0];

            for (int i = 1; i < n; ++i) {
                currSum = Math.max(nums[i], currSum + nums[i]);
                maxSum = Math.max(maxSum, currSum);
            }
            return maxSum;
        }

        /*
        快慢指针法
         */
        public int maxSubArray2(int[] nums) {

            return 0;
        }
    }

    /*
    分治
     */
    static class Solution2 {
        public class Status {
            public int lSum, rSum, mSum, iSum;

            public Status(int lSum_, int rSum_, int mSum_, int iSum_) {
                lSum = lSum_;
                rSum = rSum_;
                mSum = mSum_;
                iSum = iSum_;
            }
        }

        public Status pushUp(Status l, Status r) {
            int iSum = l.iSum + r.iSum;
            int lSum = Math.max(l.lSum, l.iSum + r.lSum);
            int rSum = Math.max(r.rSum, r.iSum + l.rSum);
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }

        public Status getInfo(int[] a, int l, int r) {
            if (l == r) return new Status(a[l], a[l], a[l], a[l]);
            int m = (l + r) >> 1;
            Status lSub = getInfo(a, l, m);
            Status rSub = getInfo(a, m + 1, r);
            return pushUp(lSub, rSub);
        }

        public int maxSubArray(int[] nums) {
            return getInfo(nums, 0, nums.length - 1).mSum;
        }
    }
}

