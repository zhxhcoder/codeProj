package com.zhxh.codeproj.leetcode.array;

import java.util.Arrays;

/*
给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。


示例1:

给定数组 nums = [1,1,2],

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

你不需要考虑数组中超出新长度后面的元素。

示例2:

给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。


说明:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
  print(nums[i]);
}

 */
public class LeetCode26 {
    public static void main(String[] args) {
        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("长度：" + new Solution().removeDuplicates(nums1));
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("长度：" + new Solution2().removeDuplicates(nums2));
        System.out.println(Arrays.toString(nums2));
    }

    static class Solution {
        //while 循环 同向双指针
        public int removeDuplicates(int[] nums) {
            //异常处理
            if (nums == null || nums.length == 0) return 0;
            //初始化i 和j
            int i = 0, j = 1;
            while (j < nums.length) {
                //如果没有重复，保留，否则忽略
                if (nums[i] != nums[j]) {
                    nums[i + 1] = nums[j];
                    i++;
                }
                j++;
            }
            return i + 1;
        }
    }

    static class Solution2 {
        // for 循环 同向双指针
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0;
            int i = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    i++;
                    nums[i] = nums[j];
                }
            }
            return i + 1;
        }
    }
}
