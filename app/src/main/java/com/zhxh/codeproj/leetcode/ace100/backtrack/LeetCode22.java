package com.zhxh.codeproj.leetcode.ace100.backtrack;

import java.util.ArrayList;
import java.util.List;

/*
22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。



示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 1
输出：["()"]


提示：

1 <= n <= 8

 */
public class LeetCode22 {
    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
        System.out.println(new Solution2().generateParenthesis(3));
        System.out.println(new Solution3().generateParenthesis(3));
        System.out.println(new Solution4().generateParenthesis(3));
    }

    /*
    方法一：暴力法
     */
    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> combinations = new ArrayList<String>();
            generateAll(new char[2 * n], 0, combinations);
            return combinations;
        }

        public void generateAll(char[] current, int pos, List<String> result) {
            if (pos == current.length) {
                if (valid(current)) {
                    result.add(new String(current));
                }
            } else {
                current[pos] = '(';
                generateAll(current, pos + 1, result);
                current[pos] = ')';
                generateAll(current, pos + 1, result);
            }
        }

        public boolean valid(char[] current) {
            int balance = 0;
            for (char c : current) {
                if (c == '(') {
                    ++balance;
                } else {
                    --balance;
                }
                if (balance < 0) {
                    return false;
                }
            }
            return balance == 0;
        }
    }

    /*
    方法二：回溯法
     */
    static class Solution2 {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList<String>();
            backtrack(ans, new StringBuilder(), 0, 0, n);
            return ans;
        }

        public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
            if (cur.length() == max * 2) {
                ans.add(cur.toString());
                return;
            }
            if (open < max) {
                cur.append('(');
                backtrack(ans, cur, open + 1, close, max);
                cur.deleteCharAt(cur.length() - 1);
            }
            if (close < open) {
                cur.append(')');
                backtrack(ans, cur, open, close + 1, max);
                cur.deleteCharAt(cur.length() - 1);
            }
        }
    }

    /*
    回溯
     */
    static class Solution3 {
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

    /*
    递归
    规律:剩余左括号总数要小于等于右括号。 递归把所有符合要求的加上去就行了：
     */
    static class Solution4 {
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            if (n <= 0) {
                return res;
            }
            getParenthesis("", n, n);
            return res;
        }

        private void getParenthesis(String str, int left, int right) {
            if (left == 0 && right == 0) {
                res.add(str);
                return;
            }
            if (left == right) {
                //剩余左右括号数相等，下一个只能用左括号
                getParenthesis(str + "(", left - 1, right);
            } else if (left < right) {
                //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
                if (left > 0) {
                    getParenthesis(str + "(", left - 1, right);
                }
                getParenthesis(str + ")", left, right - 1);
            }
        }
    }
}
