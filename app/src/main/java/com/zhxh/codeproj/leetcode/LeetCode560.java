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
        System.out.println(new Solution().subarraySum(nums, 3));
        System.out.println(new Solution().subarraySum1(nums, 3));
        System.out.println(new Solution().subarraySum2(nums, 3));
        System.out.println(new Solution().subarraySum3(nums, 3));
    }

    static class Solution {
        /*
        暴力法
         */
        public int subarraySum(int[] nums, int k) {
            // 初始化，使用count储存结果
            int count = 0;
            //遍历nums考虑每个位置start作为子数组结尾的情况
            for (int start = 0; start < nums.length; ++start) {
                //记录当前子数组的和
                int sum = 0;
                for (int end = start; end >= 0; --end) {
                    sum += nums[end];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }

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
            // hash
            // 记录合适的连续字符串数量
            int count = 0;
            // 记录前面数字相加之和
            int pre = 0;
            // map记录前几个数字之和为K出现相同和的次数为V
            HashMap<Integer, Integer> map = new HashMap<>();
            // 初始化
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                // 前缀和
                // 设
                // pre[i]=pre[i−1]+nums[i]
                // 由于补上了0，1这个情况 问题由多少个连续数字之和等于k 转为
                // pre[i]−pre[j−1]==k （前缀和之差为k，代表这两个前缀和中间的数字相加就是K）
                // 如果前面某些数字之和加上这个数字正好等于K（存在一个数字加上nums[i]结果为K
                // 说明找到了
                if (map.containsKey(pre - k)) {
                    // 累计
                    count += map.get(pre - k);
                }
                // 计算新的和放入map
                map.put(pre, map.getOrDefault(pre, 0) + 1);
            }
            return count;
        }

    }
}
