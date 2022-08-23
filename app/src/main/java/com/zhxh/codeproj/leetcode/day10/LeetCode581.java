package com.zhxh.codeproj.leetcode.day10;

import java.util.Arrays;
import java.util.Stack;

/*
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。



示例 1：

输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
示例 2：

输入：nums = [1,2,3,4]
输出：0
示例 3：

输入：nums = [1]
输出：0


提示：

1 <= nums.length <= 104
-105 <= nums[i] <= 105


进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？

 */
public class LeetCode581 {
    public static void main(String[] args) {
        System.out.println(new Solution().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Solution2().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Solution3().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Solution4().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    static class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i]) {
                        r = Math.max(r, j);
                        l = Math.min(l, i);
                    }
                }
            }
            return r - l < 0 ? 0 : r - l + 1;
        }
    }

    static class Solution2 {
        public int findUnsortedSubarray(int[] nums) {
            Stack<Integer> stack = new Stack<Integer>();
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                    l = Math.min(l, stack.pop());
                stack.push(i);
            }
            stack.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                    r = Math.max(r, stack.pop());
                stack.push(i);
            }
            return r - l > 0 ? r - l + 1 : 0;
        }
    }

    /*
    排序
    我们将给定的数组nums表示为三段子数组拼接的形式，分别做numsA,numsB,numsC。
    当我们对numsB进行排序，整个数组将变为有序。
    换言之，当我们对整个序列进行排序，numsA和numsC都不会改变。
     */
    static class Solution3 {
        public int findUnsortedSubarray(int[] nums) {
            if (isSorted(nums)) {
                return 0;
            }
            int[] numsSorted = new int[nums.length];
            System.arraycopy(nums, 0, numsSorted, 0, nums.length);
            Arrays.sort(numsSorted);
            int left = 0;
            while (nums[left] == numsSorted[left]) {
                left++;
            }
            int right = nums.length - 1;
            while (nums[right] == numsSorted[right]) {
                right--;
            }
            return right - left + 1;
        }

        public boolean isSorted(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    return false;
                }
            }
            return true;
        }
    }

    /*
    一次遍历
    假设numsB在nums中对应区间为[left,right]
    注意到numsB和numsC中任意一个数都大于等于numsA中任意一个树。因此numsA中每一个数nums[i]都满足：

     */
    static class Solution4 {
        public int findUnsortedSubarray(int[] nums) {
            int n = nums.length;
            int maxn = Integer.MIN_VALUE, right = -1;
            int minn = Integer.MAX_VALUE, left = -1;
            for (int i = 0; i < n; i++) {
                if (maxn > nums[i]) {//maxn:表示前一项;nums[i]:表示当前项
                    right = i;//可理解为:前一项比当前项大时,该数组不为升序数组,并记录当前项.  遍历一次后,right即为最后一个使之不为升序数组的数.  left同理
                } else {
                    maxn = nums[i];
                }
                if (minn < nums[n - i - 1]) {
                    left = n - i - 1;
                } else {
                    minn = nums[n - i - 1];
                }
            }
            return right == -1 ? 0 : right - left + 1;
        }
    }
}
