package com.zhxh.codeproj.leetcode;

/*
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器，且 n 的值至少为 2

 */
public class LeetCode11 {
    public static void main(String[] args) {
        int[] height = {8, 10, 2, 2, 2, 2, 2, 99, 9};
        Solution solution = new Solution();
        int maxArea = solution.maxArea(height);
        System.out.println("maxArea:" + maxArea);
    }

    public static class Solution {
        public int maxArea(int[] height) {
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
