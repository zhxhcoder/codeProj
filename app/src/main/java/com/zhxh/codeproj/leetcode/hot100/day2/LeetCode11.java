package com.zhxh.codeproj.leetcode.hot100.day2;

/*
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0)。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器，且n的值至少为 2
示例 1：



输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
示例 2：

输入：height = [1,1]
输出：1


提示：

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

 */
public class LeetCode11 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[]{8, 10, 2, 2, 2, 2, 2, 99, 9}));
        System.out.println(new Solution().maxArea1(new int[]{8, 10, 2, 2, 2, 2, 2, 99, 9}));
    }


    public static class Solution {
        /*
        暴力解法
         */
        public int maxArea(int[] height) {
            int maxarea = 0;
            for (int i = 0; i < height.length; i++) {
                for (int j = i + 1; j < height.length; j++) {
                    maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
                }
            }
            return maxarea;
        }


        /*
        双指针
        关键词：左右两边
        模式识别：需要移动左右两头的问题，可以考虑双指针
        难点：如何移动指针
            相同情况下两边距离越远越好
            区域受限于较短边
         */
        public int maxArea1(int[] height) {
            int maxarea = 0, l = 0, r = height.length - 1;
            while (l < r) {
                maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
                //最初我们考虑由最外面两条线段构成的区域。现在，为是使面积最大化，我们需要考虑更长的两条线段之间的区域。
                //如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。但是，在同样的条件下，移动
                //较短的指针尽管造成了矩形宽度的减小，但却可能有助于面积的增大。因为移动较短的指针会得到相对较长的线段，这可以克服由宽度减小而引起的面积减少。
                if (height[l] < height[r])
                    l++;
                else
                    r--;
            }
            return maxarea;
        }
    }
}
