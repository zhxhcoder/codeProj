package com.zhxh.codeproj.leetcode.top145;

/*
给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。



示例 1：

输入：x = 4
输出：2
示例 2：

输入：x = 8
输出：2
解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。


提示：

0 <= x <= 231 - 1

 */
public class LeetCode69 {
    public static void main(String[] args) {
        System.out.println(new Solution().mySqrt(15));
        System.out.println(new Solution2().mySqrt(15));
        System.out.println(new Solution3().mySqrt(15));
        System.out.println(new Solution4().mySqrt(15));
    }

    /*
    方法一：袖珍计算器算法
     */
    static class Solution {
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            int ans = (int) Math.exp(0.5 * Math.log(x));
            return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
        }
    }

    /*
    方法二：二分查找
     */
    static class Solution2 {
        public int mySqrt(int x) {
            int l = 0, r = x, ans = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if ((long) mid * mid <= x) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
        }
    }

    /*
    方法三：牛顿迭代
     */
    static class Solution3 {
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            double C = x, x0 = x;
            while (true) {
                double xi = 0.5 * (x0 + C / x0);
                if (Math.abs(x0 - xi) < 1e-7) {
                    break;
                }
                x0 = xi;
            }
            return (int) x0;
        }
    }

    /*
    为什么二分查找的终止条件是left <= right，如果不放这个条件，while遍历就会遍历到之前已经遍历过的区域，
    为什么要加上=的判断条件，因为将左指针和右指针相同的时候，就是判断指针本身和目标值是否相等。
    每当这里想不清楚的时候，就拿最简单的例子：1，2，3来做推理。
    要想知道3在不在他们中间，先比较3和2，然后3比2大，left = mid + 1 = 3，right = right = 3;这时候left 和 right相等，
    他们的中间也就是3,3=3，于是3在他们中间，如果left < right 就返回，就会返回错误的答案。
     */
    static class Solution4 {
        public int mySqrt(int x) {
            int i = 1;
            int j = x;
            int ans = 0;
            while (i <= j) {
                int mid = i + (j - i) / 2;
                // upper bound的形式，因为我们要找的ans要是最接近于x的最大的数，利用upper bound
                if (mid <= x / mid) {
                    i = mid + 1;
                    ans = mid; // 只要mid <= x/mid，left左边界就会一直向右移动，ans就会一直更新（变大），直到不在满足mid <= x/mid的条件为止，ans就会停止更新，永远停在满足mid<=x/mid条件下面的最后一次更新，即满足ans * ans <= mid的最大值。
                } else
                    j = mid - 1;
            }

            return ans;
        }
    }
}
