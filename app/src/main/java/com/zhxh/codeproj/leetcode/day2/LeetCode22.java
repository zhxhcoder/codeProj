package com.zhxh.codeproj.leetcode.day2;

import java.util.ArrayList;
import java.util.List;

/*
数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例：

输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]

 */
public class LeetCode22 {
    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
    }

    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList<>();
            backtrack(ans, "", 0, 0, n);
            return ans;
        }

        private void backtrack(List<String> ans, String cur, int open, int close, int max) {
            if (cur.length() == max * 2) {
                ans.add(cur);
                return;
            }
            if (open < max) {
                backtrack(ans, cur + "(", open + 1, close, max);
            }
            if (close < open) {
                backtrack(ans, cur + ")", open, close + 1, max);
            }
        }
    }
}
