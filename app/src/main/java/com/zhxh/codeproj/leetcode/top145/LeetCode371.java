package com.zhxh.codeproj.leetcode.top145;

/*

不使用运算符 + 和 - ，计算两整数 a 、b 之和。

示例 1:

输入: a = 1, b = 2
输出: 3
示例 2:

输入: a = -2, b = 3
输出: 1
 */
public class LeetCode371 {
    public static void main(String[] args) {
        System.out.println(new Solution().getSum(32, 4));
        System.out.println(new Solution2().getSum(32, 4));
    }

    /*
    位运算
     */
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

    /*
    位运算
    有符号整数通常用补码来表示和存储，补码具有如下特征：
    - 正整数的补码与原码相同；负整数的补码为其原码除符号位外的所有位取反后加1
    - 可以将减法运算转化为补码的加法运算来实现
    - 符号位于数值位可以一起参与运算
     */
    static class Solution2 {
        public int getSum(int a, int b) {
            while (b != 0) {
                int carry = (a & b) << 1;
                a = a ^ b;
                b = carry;
            }
            return a;
        }
    }
}
