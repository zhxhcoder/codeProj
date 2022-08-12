package com.zhxh.codeproj.leetcode;

import java.util.HashMap;

/*
给你一个整数数组 nums 和一个整数k ，请你统计并返回 该数组中和为k的连续子数组的个数。

示例 1：

输入：nums = [1,1,1], k = 2
输出：2
示例 2：

输入：nums = [1,2,3], k = 3
输出：2

提示：

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107

 */
class LeetCode560 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 0, 2, 1, 3, 2, 4};
        System.out.println(new Solution().subarraySum1(nums, 3));
        System.out.println(new Solution().subarraySum2(nums, 3));
        System.out.println(new Solution().subarraySum3(nums, 3));
    }

    static class Solution {

        /*
        暴力法
         */
        public int subarraySum1(int[] nums, int k) {
            int len = nums.length;
            int sum = 0;
            int count = 0;
            //双重循环
            for (int i = 0; i < len; ++i) {
                for (int j = i; j < len; ++j) {
                    sum += nums[j];
                    //发现符合条件的区间
                    if (sum == k) {
                        count++;
                    }
                }
                //记得归零，重新遍历
                sum = 0;
            }
            return count;
        }

        public int subarraySum2(int[] nums, int k) {
            //前缀和数组
            int[] presum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                //这里需要注意，我们的前缀和是presum[1]开始填充的
                presum[i + 1] = nums[i] + presum[i];
            }
            //统计个数
            int count = 0;
            for (int i = 0; i < nums.length; ++i) {
                for (int j = i; j < nums.length; ++j) {
                    //注意偏移，因为我们的nums[2]到nums[4]等于presum[5]-presum[2]
                    //所以这样就可以得到nums[i,j]区间内的和
                    if (presum[j + 1] - presum[i] == k) {
                        count++;
                    }
                }
            }
            return count;
        }

        public int subarraySum3(int[] nums, int k) {
            if (nums.length == 0) {
                return 0;
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            //细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
            //例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
            //输入：[3,1,1,0] k = 2时则不会漏掉
            //因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
            map.put(0, 1);
            int count = 0;
            int presum = 0;
            for (int x : nums) {
                presum += x;
                //当前前缀和已知，判断是否含有 presum - k的前缀和，那么我们就知道某一区间的和为 k 了。
                if (map.containsKey(presum - k)) {
                    count += map.get(presum - k);//获取次数
                }
                //更新
                map.put(presum, map.getOrDefault(presum, 0) + 1);
            }
            return count;
        }

    }
}
