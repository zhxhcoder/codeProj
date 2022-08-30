package com.zhxh.codeproj.leetcode.hot100.day4;

import java.util.Arrays;

/*
给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？

 */
class LeetCode75 {
    public static void main(String[] args) {
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        new Solution().sortColors(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {2, 0, 2, 1, 1, 0};
        new Solution().sortColors2(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    /*
    本问题被称为 荷兰国旗问题，最初由 Edsger W. Dijkstra提出。
    其主要思想是给每个数字设定一种颜色，并按照荷兰国旗颜色的顺序进行调整。
    我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素。
     */
    static class Solution {
        /*
        荷兰三色旗问题解
        双指针算法
        */
        public void sortColors(int[] nums) {
            // 对于所有 idx < i : nums[idx < i] = 0
            // j是当前考虑元素的下标
            int p0 = 0, curr = 0;
            // 对于所有 idx > k : nums[idx > k] = 2
            int p2 = nums.length - 1;

            int tmp;
            while (curr <= p2) {
                if (nums[curr] == 0) {
                    // 交换第 p0个和第curr个元素
                    // i++，j++
                    tmp = nums[p0];
                    nums[p0++] = nums[curr];
                    nums[curr++] = tmp;
                } else if (nums[curr] == 2) {
                    // 交换第k个和第curr个元素
                    // p2--
                    tmp = nums[curr];
                    nums[curr] = nums[p2];
                    nums[p2--] = tmp;
                } else curr++;
            }
        }

        /*
        快速排序算法
        思路：借助快速排序partition过程的一趟扫描法
        -回顾快速排序partition过程：随机选择一个元素作为切分元素（pivot），然后经过一次扫描，通过交换不同位置的元素使得数组按照数值大小分成以下3个部分：
         <pivot  ==pivot  >pivot

         循环不变量 分区定义：
         [0,p0) ==0
         [p0,i) ==1
         (p2,len01] ==2

         */
        public void sortColors2(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return;
            }

            int p0 = 0;
            int i = 0;
            int p2 = len - 1;
            while (i <= p2) {//(p2,len01] ==2
                if (nums[i] == 0) {
                    swap(nums, i, p0);
                    p0++;
                    i++;
                } else if (nums[i] == 1) {
                    i++;
                } else {
                    //nums[i] ==2
                    swap(nums, i, p2);
                    p2--;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
