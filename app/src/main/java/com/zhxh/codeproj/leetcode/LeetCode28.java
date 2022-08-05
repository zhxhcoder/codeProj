package com.zhxh.codeproj.leetcode;

/*
实现strStr()函数。

给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。

示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:

当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。

 */
public class LeetCode28 {
    public static void main(String[] args) {
        System.out.println(new Solution().strStr("hi|hello", "ll"));
    }

    /*
    这道题是要在 haystack 字符串中找到 needle 字符串。下面会给出的三种解法，这三种解法都基于滑动窗口。

子串逐一比较的解法最简单，将长度为 L 的滑动窗口沿着 haystack 字符串逐步移动，并将窗口内的子串与 needle 字符串相比较，时间复杂度为 O((N - L)L)O((N−L)L)

显示上面这个方法是可以优化的。双指针方法虽然也是线性时间复杂度，不过它可以避免比较所有的子串，因此最优情况下的时间复杂度为 O(N)O(N)，但最坏情况下的时间复杂度依然为 O((N - L)L)O((N−L)L)。

有 O(N)O(N) 复杂度的解法嘛？答案是有的，有两种方法可以实现：

Rabin-Karp，通过哈希算法实现常数时间窗口内字符串比较。

比特位操作，通过比特掩码来实现常数时间窗口内字符串比较。
     */
    static class Solution {

        public int strStr(String haystack, String needle) {
            if (needle.equals("")) {
                return 0;
            }
            if (haystack.equals("")) {
                return -1;
            }

            // 构造KMP中的dp矩阵
            int m = needle.length();
            // 各个状态(行)遇到下一个字符(列)跳转到哪个状态
            int[][] dp = new int[m][256];
            // 影子状态
            int X = 0;
            dp[0][needle.charAt(0)] = 1;
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < 256; j++) {
                    //假设下个字符不匹配，此时要回去看影子状态，从而得知跳转到哪个状态
                    dp[i][j] = dp[X][j];
                }
                // 只有pat上i的字符匹配，跳转到下个状态
                dp[i][needle.charAt(i)] = i + 1;
                // 更新影子状态
                X = dp[X][needle.charAt(i)];
            }

            // 构造dp完成后，开始search
            // 初始状态为0
            int s = 0;
            for (int i = 0; i < haystack.length(); i++) {
                s = dp[s][haystack.charAt(i)];
                if (s == m) {
                    return i - m + 1;
                }
            }
            // 匹配失败，返回-1
            return -1;
        }
    }
}
