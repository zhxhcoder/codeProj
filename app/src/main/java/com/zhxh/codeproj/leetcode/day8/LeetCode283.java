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
        int[] nums1 = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {0, 1, 0, 3, 12};
        new Solution().moveZeroes2(nums2);
        System.out.println(Arrays.toString(nums2));
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


        /*
        双指针
        左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
        右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交互，同时左指针右移。
        注意以下性质；
        1，左指针左边均为非零数
        2，右指针左边直到左边指针均为0
        因此每次交换，都是将左指针的0与右指针的非零数交换，且非零数的相对顺序并未改变。

         */

        public void moveZeroes2(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        /*
         * 交换数组 arr 中下标为 i 和下标为 j 位置的元素
         */
        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
