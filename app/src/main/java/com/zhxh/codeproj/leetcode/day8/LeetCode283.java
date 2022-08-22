package com.zhxh.codeproj.leetcode.day8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意，必须在不复制数组的情况下原地对数组进行操作。


示例 1:

输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
示例 2:

输入: nums = [0]
输出: [0]


提示:

1 <= nums.length <= 104
-231<= nums[i] <= 231- 1

 */
public class LeetCode283 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        /*
        自己写的，利用队列，存储为0的下标
         */
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length < 2) {
                return;
            }
            Queue<Integer> zeroQueue = new LinkedList<Integer>();
            int i = 0;

            while (i < nums.length) {
                if (nums[i] == 0) {
                    zeroQueue.offer(i);
                } else {
                    if (!zeroQueue.isEmpty()) {
                        int j = zeroQueue.poll();
                        nums[j] = nums[i];
                        nums[i] = 0;
                        //这个不要忘记
                        zeroQueue.offer(i);
                    }
                }
                i++;
            }
        }
    }
}
