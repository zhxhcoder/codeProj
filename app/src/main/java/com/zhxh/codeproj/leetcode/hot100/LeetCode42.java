package com.zhxh.codeproj.leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;

/*
给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：

输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
示例 2：

输入：height = [4,2,0,3,2,5]
输出：9

提示：

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class LeetCode42 {
    public static void main(String[] args) {
        System.out.println(new Solution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Solution().trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new Solution().trap3(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    static class Solution {
        /*
        暴力解法
        直接按问题描述进行。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。

         */
        public int trap(int[] height) {
            int ans = 0;
            int size = height.length;
            for (int i = 1; i < size - 1; i++) {
                int max_left = 0, max_right = 0;
                for (int j = i; j >= 0; j--) { //在左侧搜索最大条形尺寸
                    max_left = Math.max(max_left, height[j]);
                }
                for (int j = i; j < size; j++) { //在右侧部分搜索最大条形尺寸
                    max_right = Math.max(max_right, height[j]);
                }
                ans += Math.min(max_left, max_right) - height[i];
            }
            return ans;
        }

        /*
        双指针
         */
        public int trap2(int[] height) {
            int sum = 0;
            int max_left = 0;
            int max_right = 0;
            int left = 1;
            int right = height.length - 2; // 加右指针进去
            for (int i = 1; i < height.length - 1; i++) {
                //从左到右更
                if (height[left - 1] < height[right + 1]) {
                    max_left = Math.max(max_left, height[left - 1]);
                    int min = max_left;
                    if (min > height[left]) {
                        sum = sum + (min - height[left]);
                    }
                    left++;
                    //从右到左更
                } else {
                    max_right = Math.max(max_right, height[right + 1]);
                    int min = max_right;
                    if (min > height[right]) {
                        sum = sum + (min - height[right]);
                    }
                    right--;
                }
            }
            return sum;
        }

        /*
        栈的应用
        我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
        如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，因此我们可以弹出栈顶元素并且累加答案到
         */
        public int trap3(int[] height) {
            int ans = 0, current = 0;
            Deque<Integer> stack = new LinkedList<Integer>();
            while (current < height.length) {
                while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                    int top = stack.pop();
                    if (stack.isEmpty())
                        break;
                    int distance = current - stack.peek() - 1;
                    int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                    ans += distance * bounded_height;
                }
                stack.push(current++);
            }
            return ans;
        }

    }
}
