package com.zhxh.codeproj.leetcode.day2;

/*
给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。


示例 1：

输入：s = "aa", p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。
示例 2:

输入：s = "aa", p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例3：

输入：s = "ab", p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。


提示：

1 <= s.length<= 20
1 <= p.length<= 30
s只包含从a-z的小写字母。
p只包含从a-z的小写字母，以及字符.和*。
保证每次出现字符* 时，前面都匹配到有效的字符

 */
public class LeetCode10 {
    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aaabaaacc", "a*ba*.."));
        System.out.println(new Solution().isMatch1("aaabaaacc", "a*ba*.."));
        System.out.println(new Solution().isMatch2("aaabaaacc", "a*ba*.."));
    }

    static class Solution {

        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            boolean[][] f = new boolean[m + 1][n + 1];
            f[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (p.charAt(j - 1) == '*') {
                        f[i][j] = f[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            f[i][j] = f[i][j] || f[i - 1][j];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    }
                }
            }
            return f[m][n];
        }

        public boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }


        /*
        动态规划
         */
        public boolean isMatch1(String text, String pattern) {
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            dp[text.length()][pattern.length()] = true;

            for (int i = text.length(); i >= 0; i--) {
                for (int j = pattern.length() - 1; j >= 0; j--) {
                    boolean first_match = (i < text.length() &&
                            (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));
                    if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                        dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                    } else {
                        dp[i][j] = first_match && dp[i + 1][j + 1];
                    }
                }
            }
            return dp[0][0];
        }


        /*
        动态规划
         */
        public boolean isMatch2(String s, String p) {
            //此处为length+1的目的是放入一个额外的为空的字符情况，以便于判断当*时，添加的字符情况
            boolean table[][] = new boolean[s.length() + 1][p.length() + 1];
            table[0][0] = true;
            for (int i = 1; i < table[0].length; i++) {
                char ch = p.charAt(i - 1);
                if (i > 1) {
                    //若ch=='*'，则看同一行内回退两格的boolean值：
                    //（因为相当于若回退两格为true，即在选择添加该字符时可以选择数量为0（因为是'*'））
                    if (ch == '*') {
                        table[0][i] = table[0][i - 2];
                    }
                    //因为第0行的s字符为空，所以和除了*以外的都不匹配，直接false
                    else table[0][i] = false;
                } else {
                    //如果填第一个空格，且字符为*，则赋值为true（因为*的匹配可以选择0个字符）
                    if (ch == '*') table[0][i] = true;
                }
            }
            //接下来按照行优先的顺序填充表格
            for (int j = 1; j < table.length; j++) {
                char ch01 = s.charAt(j - 1);
                for (int h = 1; h < table[j].length; h++) {
                    char ch02 = p.charAt(h - 1);
                    //如果行和列对应的字符相等 || 列的字符为'.'，则当前位置的值由左斜上方的值确定
                    if (ch02 == ch01 || ch02 == '.') {
                        table[j][h] = table[j - 1][h - 1];
                    }
                    //如果列的字符为'*'
                    else if (ch02 == '*') {
                        if (h > 1) {
                            //按照规则，先在同一行回退两格，若该值为true则直接赋值true
                            if (table[j][h - 2] == true) table[j][h] = true;
                            else {
                                //若不为true，则比较当前行的字符（s里的）与当前列-1的字符（p里的）是否相等
                                char prev = p.charAt(h - 2);
                                //若相等 || 当前列-1的字符（p里的）为'.'，将当前位置上方的值赋给当前位置
                                if (ch01 == prev || prev == '.') table[j][h] = table[j - 1][h];
                            }
                        }

                    }
                }
            }
            //返回table表的最右下方元素，即为整个字符串的匹配结果
            return table[s.length()][p.length()];
        }
    }
}
