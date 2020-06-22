package com.zhxh.codeproj.leetcode.array;

/*

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
 */
public class LeetCode283 {

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] nums = {3, 4, 2, 0, 0, 3, 1, 2, 6, 0};

        solution.moveZeroes(nums);

        for (int item : nums) {
            System.out.println(item);
        }
    }

    static class Solution {
        public void moveZeroes(int[] nums) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[index] = nums[i];
                    index++;
                }
            }
            for (; index < nums.length; index++) {
                nums[index] = 0;
            }
        }
    }
}
