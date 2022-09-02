package com.zhxh.codeproj.leetcode.top145;

import java.util.HashMap;
import java.util.Map;

/*
454. 四数相加 II
给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0


示例 1：

输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
示例 2：

输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
输出：1


  提示：

n == nums1.length
n == nums2.length
n == nums3.length
n == nums4.length
1 <= n <= 200
-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228

 */
public class LeetCode454 {
    public static void main(String[] args) {
        System.out.println(new Solution().fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }

    /*
    方法一：分组 + 哈希表
    我们可以将四个数组分成两部分，AA 和 BB 为一组，CC 和 DD 为另外一组。
     */
    static class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
            for (int u : A) {
                for (int v : B) {
                    countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
                }
            }
            int ans = 0;
            for (int u : C) {
                for (int v : D) {
                    if (countAB.containsKey(-u - v)) {
                        ans += countAB.get(-u - v);
                    }
                }
            }
            return ans;
        }
    }
}
