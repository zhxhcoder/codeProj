package com.zhxh.codeproj.leetcode.hot100;

import java.util.Arrays;

/*
31. 下一个排列
整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。



示例 1：

输入：nums = [1,2,3]
输出：[1,3,2]
示例 2：

输入：nums = [3,2,1]
输出：[1,2,3]
示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]


提示：

1 <= nums.length <= 100
0 <= nums[i] <= 100

 */
public class LeetCode31 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().nextPermutation(new int[]{4, 5, 2, 6, 3, 1})));
        System.out.println(Arrays.toString(new Solution2().nextPermutation(new int[]{4, 5, 2, 6, 3, 1})));
    }

    static class Solution {
        /*
        两遍扫描
         */
        public int[] nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
            //返回数组
            return nums;
        }
        /*
        从start开始到末尾，全部置换
         */

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /*
    从后往前找，直到找到arr[i]>arr[i-1]的位置 ，令aid=i-1,如果没找到，说明数组是降序，直接返回倒序。
    否则将遍历过的部分那个比arr[aid]大的最小值和aid位置交换，然后将位置aid+1到最后进行一个倒序即可。
     */
    static class Solution2 {
        public int[] nextPermutation(int[] nums) {
            boolean flag = true;
            int aid = -1;
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i] > nums[i - 1]) {
                    aid = i - 1;
                    flag = false;
                    break;
                }
            }
            if (flag) {//flag为true的话，说明数组是降序排列，直接反转数组即可
                reverse(nums, 0, nums.length - 1);
                //返回数组
                return nums;
            }

            for (int i = nums.length - 1; i > aid; i--) {
                if (nums[i] > nums[aid]) {//将遍历过的部分那个比i-1大的最小值和i-1位置交换
                    int temp = nums[i];
                    nums[i] = nums[aid];
                    nums[aid] = temp;
                    break;
                }
            }
            reverse(nums, aid + 1, nums.length - 1);//将位置aid+1到最后进行一个倒序即可
            //返回数组
            return nums;
        }

        //反转数组
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
