package com.zhxh.codeproj.leetcode.day10;

/*
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。

回文字符串 是正着读和倒过来读一样的字符串。

子字符串 是字符串中的由连续字符组成的一个序列。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。



示例 1：

输入：s = "abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：

输入：s = "aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"


提示：

1 <= s.length <= 1000
s 由小写英文字母组成

 */
public class LeetCode647 {
    public static void main(String[] args) {
        System.out.println(new Solution().countSubstrings("aaa"));
        System.out.println(new Solution2().countSubstrings("aaa"));
    }

    /*
    中心拓展
     */
    static class Solution {
        public int countSubstrings(String S) {
            int N = S.length(), ans = 0;
            for (int center = 0; center <= 2 * N - 1; ++center) {
                int left = center / 2;
                int right = left + center % 2;
                while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                    ans++;
                    left--;
                    right++;
                }
            }
            return ans;
        }
    }

    /*
    Manacher 算法
     */
    static class Solution2 {
        public int countSubstrings(String s) {
            int n = s.length();
            StringBuffer t = new StringBuffer("$#");
            for (int i = 0; i < n; ++i) {
                t.append(s.charAt(i));
                t.append('#');
            }
            n = t.length();
            t.append('!');

            int[] f = new int[n];
            int iMax = 0, rMax = 0, ans = 0;
            for (int i = 1; i < n; ++i) {
                // 初始化 f[i]
                f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
                // 中心拓展
                while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                    ++f[i];
                }
                // 动态维护 iMax 和 rMax
                if (i + f[i] - 1 > rMax) {
                    iMax = i;
                    rMax = i + f[i] - 1;
                }
                // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
                ans += f[i] / 2;
            }

            return ans;
        }
    }
}
