package com.zhxh.codeproj.leetcode.top145;

import java.util.Arrays;

/*
给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

 

示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
 

提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
 

进阶：

尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？

 */
public class LeetCode189 {
    public static void main(String[] args) {
        new Solution().rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        new Solution2().rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        new Solution3().rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        new Solution4().rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }

    /*
    方法一：使用额外的数组
     */
    static class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int[] newArr = new int[n];
            for (int i = 0; i < n; ++i) {
                newArr[(i + k) % n] = nums[i];
            }
            System.arraycopy(newArr, 0, nums, 0, n);
            System.out.println(Arrays.toString(nums));
        }
    }

    /*
    方法二：环状替换
     */
    static class Solution2 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            int count = gcd(k, n);
            for (int start = 0; start < count; ++start) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % n;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                } while (start != current);
            }
            System.out.println(Arrays.toString(nums));
        }

        public int gcd(int x, int y) {
            return y > 0 ? gcd(y, x % y) : x;
        }
    }

    /*
    方法三：数组翻转
     */
    static class Solution3 {
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);

            System.out.println(Arrays.toString(nums));
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start += 1;
                end -= 1;
            }
        }
    }

    /*
    暴力解法
     */
    static class Solution4 {
        public void rotate(int[] nums, int k) {
            int temp, previous;
            for (int i = 0; i < k; i++) {
                previous = nums[nums.length - 1];
                for (int j = 0; j < nums.length; j++) {
                    temp = nums[j];
                    nums[j] = previous;
                    previous = temp;
                }
            }
            System.out.println(Arrays.toString(nums));
        }
    }
}
