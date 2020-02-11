package com.zhxh.codeproj.leetcode;

public class LeetCode28 {

    class Solution {
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
