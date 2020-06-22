package com.zhxh.codeproj.leetcode;

/*

不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。

示例 1:

输入: a = 1, b = 2
输出: 3
示例 2:

输入: a = -2, b = 3
输出: 1
 */
public class LeetCode371 {

    public static void main(String[] args) {

        int a = 32;
        int b = 4;

        Solution solution = new Solution();

        System.out.println(solution.getSum(a, b));
    }

    static class Solution {
        public int getSum(int a, int b) {
            while (b != 0) {
                int temp = a ^ b;
                b = (a & b) << 1;
                a = temp;
            }
            return a;
        }
    }
}
