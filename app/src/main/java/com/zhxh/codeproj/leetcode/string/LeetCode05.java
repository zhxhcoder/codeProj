package com.zhxh.codeproj.leetcode.string;

/*
给你一个字符串 s，找到 s 中最长的回文子串。
示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：
输入：s = "cbbd"
输出："bb"
提示：
1 <= s.length <= 1000
s 仅由数字和英文字母组成
 */
public class LeetCode05 {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("babcbad"));
        System.out.println(new Solution().longestPalindrome2("babcbad"));
    }

    static class Solution {
        /*
        暴力解法
         */
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len < 2) {
                return s;
            }
            int maxLen = 1;
            int begin = 0;
            //s.charAt(i)每次都会检查数组下标越界，因此先转换成字符数组，这一步非必需
            char[] charArray = s.toCharArray();

            //枚举所有长度严格大约1的子串 charArray[i..j]
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }

            return s.substring(begin, begin + maxLen);


        }

        private boolean validPalindromic(char[] charArray, int left, int right) {
            while (left < right) {
                if (charArray[left] != charArray[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        /*
        中心扩散法
         - 枚举所有可能的回文子串的中心位置；
         - 中心位置可能是一个字符，也有可能是两个相邻的字符
         - 记录最长回文子串的相关变量
         */
        public String longestPalindrome2(String s) {
            if (s == null || s.length() < 1) return "";
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int oddLen = expandAroundCenter(s, i, i);
                int evenLen = expandAroundCenter(s, i, i + 1);
                int len = Math.max(oddLen, evenLen);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }
    }
}
