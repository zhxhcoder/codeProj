package com.zhxh.codeproj.leetcode.hot100;

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
        System.out.println(new Solution().longestPalindrome("babbad"));
        System.out.println(new Solution2().longestPalindrome("babbad"));
        System.out.println(new Solution3().longestPalindrome("babbad"));
    }

    /*
    暴力解法
     */
    static class Solution {
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
    }


    /*
    中心扩散法
     - 枚举所有可能的回文子串的中心位置；
     - 中心位置可能是一个字符，也有可能是两个相邻的字符
     - 记录最长回文子串的相关变量
     */
    static class Solution2 {
        public String longestPalindrome(String s) {
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


    /*
    动态规划(回文的子串也是回文)
    状态dp[i][j]表示子串s[i..j]是否为回文子串
    得到状态转移方程：dp[i][j]=(s[i] == s[j]) and dp[i+1][j-1]
    边界条件：j-1-(i+1)+1<2,整理得j-i<3 即 j-i+1<4  即s[i..j]长度为2或者3时，不用检查子串是否回文

    动态规划，利用到了之前计算的值，所以相对快

    初始化：dp[i][i] =true
    输出：在得到一个状态的值为true的时候，记录起始位置和长度，填表完成以后再截取

    状态规划就是填表，对角线肯定是true
    状态转移方程：dp[i][j] = (s[i] == s[j]) and (j-i<3 or dp[i+1][j-1])
    由于 dp[i][j]参考它左下方的值：
    1，先升序填列
    2，再升序填行

     */
    static class Solution3 {
        public String longestPalindrome(String s) {
            int len = s.length();
            if (len < 2) {
                return s;
            }
            int maxLen = 1;
            int begin = 0;
            //dp[i][j] 表示s[i..j]是否是回文串
            boolean[][] dp = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                dp[i][i] = true;
            }

            char[] charArray = s.toCharArray();
            //注意：左下角先填
            for (int j = 1; j < len; j++) {
                for (int i = 0; i < j; i++) {
                    if (charArray[i] != charArray[j]) {
                        dp[i][j] = false;
                    } else {
                        if (j - i < 3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    //只要dp[i][j]==true成立，就表示子串s[i..j]是回文，此时记录回文长度和起始位置
                    if (dp[i][j] && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin + maxLen);
        }
    }
}
