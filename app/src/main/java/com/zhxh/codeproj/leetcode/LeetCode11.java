package com.zhxh.codeproj.leetcode;

public class LeetCode11 {
    public static void main(String[] args) {

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        Solution solution = new Solution();

        int maxArea = solution.maxArea(height);

        System.out.println("maxArea:" + maxArea);
    }

    public static class Solution {
        public int maxArea(int[] height) {
            int maxarea = 0, l = 0, r = height.length - 1;
            while (l < r) {
                maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
                if (height[l] < height[r])
                    l++;
                else
                    r--;
            }
            return maxarea;
        }
    }
}
