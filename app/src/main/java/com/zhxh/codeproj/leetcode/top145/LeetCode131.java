package com.zhxh.codeproj.leetcode.top145;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/*
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入:"aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]

 */
class LeetCode131 {
    public static void main(String[] args) {
        //todo 回溯-backtrack
        System.out.println(new Solution1().partition("aab"));
        System.out.println(new Solution2().partition("aab"));
    }

    static class Solution1 {
        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
            // 注意：只使用 stack 相关的接口
            Deque<String> stack = new ArrayDeque<>();
            backtracking(s, 0, len, stack, res);
            return res;
        }

        /*
         * @param s
         * @param start 起始字符的索引
         * @param len   字符串 s 的长度，可以设置为全局变量
         * @param path  记录从根结点到叶子结点的路径
         * @param res   记录所有的结果
         */
        private void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {
            if (start == len) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < len; i++) {

                // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
                // 不是的话，剪枝
                if (!checkPalindrome(s, start, i)) {
                    continue;
                }

                path.addLast(s.substring(start, i + 1));
                backtracking(s, i + 1, len, path, res);
                path.removeLast();
            }
        }

        /*
         * 这一步的时间复杂度是 O(N)，因此，可以采用动态规划先把回文子串的结果记录在一个表格里
         *
         * @param str
         * @param left  子串的左边界，可以取到
         * @param right 子串的右边界，可以取到
         * @return
         */
        private boolean checkPalindrome(String str, int left, int right) {
            // 严格小于即可
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    static class Solution2 {
        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }

            // 预处理
            // 状态：dp[i][j] 表示 s[i][j] 是否是回文
            boolean[][] dp = new boolean[len][len];
            // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
            for (int right = 0; right < len; right++) {
                // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
                for (int left = 0; left <= right; left++) {
                    if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            Deque<String> stack = new ArrayDeque<>();
            backtracking(s, 0, len, dp, stack, res);
            return res;
        }

        private void backtracking(String s,
                                  int start,
                                  int len,
                                  boolean[][] dp,
                                  Deque<String> path,
                                  List<List<String>> res) {
            if (start == len) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < len; i++) {
                // 剪枝
                if (!dp[start][i]) {
                    continue;
                }
                path.addLast(s.substring(start, i + 1));
                backtracking(s, i + 1, len, dp, path, res);
                path.removeLast();
            }
        }
    }
}
