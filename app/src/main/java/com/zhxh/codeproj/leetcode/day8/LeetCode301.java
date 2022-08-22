package com.zhxh.codeproj.leetcode.day8;

import java.util.ArrayList;
import java.util.List;

/*
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。



示例 1：

输入：s = "()())()"
输出：["(())()","()()()"]
示例 2：

输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
示例 3：

输入：s = ")("
输出：[""]


提示：

1 <= s.length <= 25
s 由小写英文字母以及括号 '(' 和 ')' 组成
s 中至多含 20 个括号

 */
public class LeetCode301 {
    public static void main(String[] args) {
        System.out.println(new Solution().removeInvalidParentheses("(a)())()"));
    }

    /*
    回溯+剪枝
     */
    static class Solution {
        private List<String> res = new ArrayList<String>();

        public List<String> removeInvalidParentheses(String s) {
            int lremove = 0;
            int rremove = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    lremove++;
                } else if (s.charAt(i) == ')') {
                    if (lremove == 0) {
                        rremove++;
                    } else {
                        lremove--;
                    }
                }
            }
            helper(s, 0, lremove, rremove);

            return res;
        }

        private void helper(String str, int start, int lremove, int rremove) {
            if (lremove == 0 && rremove == 0) {
                if (isValid(str)) {
                    res.add(str);
                }
                return;
            }

            for (int i = start; i < str.length(); i++) {
                if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                    continue;
                }
                // 如果剩余的字符无法满足去掉的数量要求，直接返回
                if (lremove + rremove > str.length() - i) {
                    return;
                }
                // 尝试去掉一个左括号
                if (lremove > 0 && str.charAt(i) == '(') {
                    helper(str.substring(0, i) + str.substring(i + 1), i, lremove - 1, rremove);
                }
                // 尝试去掉一个右括号
                if (rremove > 0 && str.charAt(i) == ')') {
                    helper(str.substring(0, i) + str.substring(i + 1), i, lremove, rremove - 1);
                }
            }
        }

        private boolean isValid(String str) {
            int cnt = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    cnt++;
                } else if (str.charAt(i) == ')') {
                    cnt--;
                    if (cnt < 0) {
                        return false;
                    }
                }
            }
            return cnt == 0;
        }
    }
}
