package com.zhxh.codeproj.leetcode;

public class LeetCode29 {
    class Solution {
        long divide(int dividend, int divisor) {
            if (dividend == 0) return 0;
            if (divisor == 1) return dividend;
            if (divisor == -1) {
                if (dividend > Integer.MIN_VALUE) return -dividend;// 只要不是最小的那个整数，都是直接返回相反数就好啦
                return Integer.MAX_VALUE;// 是最小的那个，那就返回最大的整数啦
            }
            long a = dividend;
            long b = divisor;
            int sign = 1;
            if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
                sign = -1;
            }
            a = a > 0 ? a : -a;
            b = b > 0 ? b : -b;
            long res = div(a, b);
            if (sign > 0) return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : res;
            return -res;
        }

        long div(long a, long b) {  // 似乎精髓和难点就在于下面这几句
            if (a < b) return 0;
            long count = 1;
            long tb = b; // 在后面的代码中不更新b
            while ((tb + tb) <= a) {
                count = count + count; // 最小解翻倍
                tb = tb + tb; // 当前测试的值也翻倍
            }
            return count + div(a - tb, b);
        }
    }
}
