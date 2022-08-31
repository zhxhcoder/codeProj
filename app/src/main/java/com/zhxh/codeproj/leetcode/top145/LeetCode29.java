package com.zhxh.codeproj.leetcode.top145;

/*
给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数dividend除以除数divisor得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2


示例1:

输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
示例2:

输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2


提示：

被除数和除数均为 32 位有符号整数。
除数不为0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31− 1]。本题中，如果除法结果溢出，则返回 2^31− 1。

 */
public class LeetCode29 {
    public static void main(String[] args) {
        System.out.println(new Solution().divide(10, 3));
    }

    static class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            // 判断结果是不是负数
            boolean isNegative = (dividend ^ divisor) < 0 ? true : false;
            // 将除数和被除数都转为负数
            dividend = dividend < 0 ? dividend : -dividend;
            divisor = divisor < 0 ? divisor : -divisor;
            int res = helper(dividend, divisor);
            return isNegative ? -res : res;
        }

        private int helper(int dividend, int divisor) {
            if (dividend > divisor) {
                return 0;
            }
            int tmp = divisor;
            int count = 1;
            // 条件tmp + tmp < 0防止溢出
            while (tmp + tmp < 0 && dividend <= tmp + tmp) {
                count += count;
                tmp += tmp;
            }
            return count + helper(dividend - tmp, divisor);
        }
    }
}
