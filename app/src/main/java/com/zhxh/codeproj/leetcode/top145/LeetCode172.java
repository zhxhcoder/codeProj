package com.zhxh.codeproj.leetcode.top145;

/*
给定一个整数 n ，返回 n! 结果中尾随零的数量。

提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1



示例 1：

输入：n = 3
输出：0
解释：3! = 6 ，不含尾随 0
示例 2：

输入：n = 5
输出：1
解释：5! = 120 ，有一个尾随 0
示例 3：

输入：n = 0
输出：0


提示：

0 <= n <= 104


进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？

 */
public class LeetCode172 {
    public static void main(String[] args) {
        System.out.println(new Solution().trailingZeroes(13));
        System.out.println(new Solution2().trailingZeroes(13));
    }

    /*
    方法一：数学
     */
    static class Solution {
        public int trailingZeroes(int n) {
            int ans = 0;
            for (int i = 5; i <= n; i += 5) {
                for (int x = i; x % 5 == 0; x /= 5) {
                    ++ans;
                }
            }
            return ans;
        }
    }

    /*
    方法二：优化计算
     */
    static class Solution2 {
        public int trailingZeroes(int n) {
            int ans = 0;
            while (n != 0) {
                n /= 5;
                ans += n;
            }
            return ans;
        }
    }
}
