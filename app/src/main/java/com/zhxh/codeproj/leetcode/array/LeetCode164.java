package com.zhxh.codeproj.leetcode.array;

import java.util.Arrays;

/*
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例 1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
示例 2:

输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。
说明:

你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。

 */
class LeetCode164 {
    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};
        System.out.println(new Solution().maximumGap(nums));
    }

    static class Solution {
        public int maximumGap(int[] nums) {
            if (nums.length < 2) return 0;
            Arrays.sort(nums);
            int ans = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                ans = Math.max(ans, nums[i + 1] - nums[i]);
            }
            return ans;
        }

        /*
        思路二：非比较排序
想要时间复杂度稳定在线性，比较排序暂时是无能为力，所以就想到非比较排序的方法，例如 计数排序？先将所有元素统计，
不需要完成排序，直接在计数器计算相邻元素之间的差值。
         */
        public int maximumGap2(int[] nums) {
            //计数
            if (nums.length < 2) return 0;
            int max = nums[0], min = nums[0], bias = 0;
            for (int num : nums) {
                max = Math.max(max, num);
                min = Math.min(min, num);
            }
            bias = 0 - min;
            int[] counter = new int[max - min + 1];
            for (int num : nums) {
                counter[num + bias]++;
            }
            //求最大差值，pre记录前一个元素
            int ans = 0;
            int pre = -1;
            for (int i = 0; i < counter.length; i++) {
                if (counter[i] != 0) {
                    if (pre != -1) {
                        ans = Math.max(ans, i - pre);
                        pre = i;
                    } else {
                        pre = i;
                    }
                }
            }
            return ans;
        }

        //思路三：桶排序
        public int maximumGap3(int[] nums) {
            if (nums.length < 2) return 0;
            //找到最小值、最大值
            int max = 0, min = 0;
            for (int num : nums) {
                max = Math.max(max, num);
                min = Math.min(min, num);
            }
            //计算桶大小,桶数量至少一个
            int bucketSize = Math.max((max - min) / (nums.length - 1), 1);
            Bucket[] buckets = new Bucket[(max - min) / bucketSize + 1];
            //入桶,每个桶只关心桶内的最大值和最小值
            for (int i = 0; i < nums.length; i++) {
                int idx = (nums[i] - min) / bucketSize;
                if (buckets[idx] == null) buckets[idx] = new Bucket();
                buckets[idx].max = Math.max(nums[i], buckets[idx].max);
                buckets[idx].min = Math.min(nums[i], buckets[idx].min);
            }
            //前一个桶的 max
            int preMax = -1;
            //最大间隔
            int maxGap = 0;
            for (int i = 0; i < buckets.length; i++) {
                //桶不为空,并且不是第一个桶
                if (buckets[i] != null && preMax != -1) {
                    //桶之间的间隔
                    maxGap = Math.max(maxGap, buckets[i].min - preMax);
                }
                //记录前一个桶的 max
                if (buckets[i] != null) {
                    preMax = buckets[i].max;
                }
            }
            return maxGap;
        }
    }

    static class Bucket {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }
}
