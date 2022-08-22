package com.zhxh.codeproj.leetcode.day8;

import java.util.Stack;

/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4
解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为O(n2) 。
进阶: 你能将算法的时间复杂度降低到O(n log n) 吗?

 */
class LeetCode300 {
    public static void main(String[] args) {
        System.out.println(new Solution0().lengthOfLIS(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12}));
        System.out.println(new Solution1().lengthOfLIS(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12}));
        System.out.println(new Solution2().lengthOfLIS(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12}));
    }
    /*
     TODO 自己的解法，暴力解法
     这个问题就是求，每个元素为root的决策树的最大深度
     比root大，才能入树
     */

    static class Solution0 {
        public int lengthOfLIS(int[] nums) {
            if (nums == null) {
                return -1;
            }
            int max = 1;
            for (int i = 0; i < nums.length; i++) {
                //辅助栈
                Stack<Integer> maxStack = new Stack<>();
                maxStack.push(nums[i]);

                for (int j = i + 1; j < nums.length; j++) {
                    int a = nums[j];
                    int innerStart = maxStack.peek();
                    //TODO 如果大于则自增
                    if (a > innerStart) {

                    }
                }
                max = Math.max(max, maxStack.size());
            }
            return max;
        }
    }

    /*
    方法一：动态规划
     */
    static class Solution1 {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxans = 1;
            for (int i = 1; i < dp.length; i++) {
                int maxval = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        maxval = Math.max(maxval, dp[j]);
                    }
                }
                dp[i] = maxval + 1;
                maxans = Math.max(maxans, dp[i]);
            }
            return maxans;
        }
    }

    /*
    二分查找
     */
    static class Solution2 {
        public int lengthOfLIS(int[] nums) {
            int len = nums.length;
            if (len <= 1) {
                return len;
            }
            // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
            int[] tail = new int[len];
            // 遍历第 1 个数，直接放在有序数组 tail 的开头
            tail[0] = nums[0];
            // end 表示有序数组 tail 的最后一个已经赋值元素的索引
            int end = 0;
            for (int i = 1; i < len; i++) {
                // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
                if (nums[i] > tail[end]) {
                    // 直接添加在那个元素的后面，所以 end 先加 1
                    end++;
                    tail[end] = nums[i];
                } else {
                    // 使用二分查找法，在有序数组 tail 中
                    // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                    int left = 0;
                    int right = end;
                    while (left < right) {
                        // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                        // int mid = left + (right - left) / 2;
                        int mid = left + ((right - left) >>> 1);
                        if (tail[mid] < nums[i]) {
                            // 中位数肯定不是要找的数，把它写在分支的前面
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                    // 因此，无需再单独判断
                    tail[left] = nums[i];
                }
                // 调试方法
                // printArray(nums[i], tail);
            }
            // 此时 end 是有序数组 tail 最后一个元素的索引
            // 题目要求返回的是长度，因此 +1 后返回
            end++;
            return end;
        }

        // 调试方法，以观察是否运行正确
        private void printArray(int num, int[] tail) {
            System.out.print("当前数字：" + num);
            System.out.print("\t当前 tail 数组：");
            int len = tail.length;
            for (int i = 0; i < len; i++) {
                if (tail[i] == 0) {
                    break;
                }
                System.out.print(tail[i] + ", ");
            }
            System.out.println();
        }
    }
}
