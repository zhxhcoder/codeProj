package com.zhxh.codeproj.leetcode.top145;

import java.util.HashMap;
import java.util.Map;

/*
字符串转换整数（atoi）

请你来实现一个atoi函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：

本题中的空白字符只包括空格字符 ' ' 。
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−231, 231− 1]。如果数值超过这个范围，请返回 INT_MAX (231− 1) 或INT_MIN (−231) 。


示例1:

输入: "42"
输出: 42
示例2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
    我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
    因此返回 INT_MIN (−231) 。

 */
class LeetCode08 {
    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("  4193 with words"));
        System.out.println(new Solution2().myAtoi("  4193 with words"));
        System.out.println(new Solution3().myAtoi("  4193 with words"));
        System.out.println(new Solution4().myAtoi("  4193 with words"));
    }

    /*
    这是确定有限状态机（deterministic finite automaton, DFA）。
    其实这题已经不算是容易写“出臃肿代码”的了。
    考虑清楚边界（主要是溢出处理）和输入种类（+, -, 0-9以及其他），大概在20行内完成代码不难。
    说实话LC官方题解里很少见给出标准DFA解法的，点个赞。
    给两个更加需要DFA的题目吧：utf-8-validation ( 附dfa解法 ) 以及 valid-number。
    顺便贴一下普通解法（那种臃肿的、易错的、可能会被test cases虐到骂娘的；但如果骂娘了，本质还是基本功不扎实）。
     */
    static class Solution {
        public int myAtoi(String str) {
            str = str.trim();
            if (str.length() == 0) return 0;
            if (!Character.isDigit(str.charAt(0))
                    && str.charAt(0) != '-' && str.charAt(0) != '+')
                return 0;
            long ans = 0L;
            boolean neg = str.charAt(0) == '-';
            int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                ans = ans * 10 + (str.charAt(i++) - '0');
                if (!neg && ans > Integer.MAX_VALUE) {
                    ans = Integer.MAX_VALUE;
                    break;
                }
                if (neg && ans > 1L + Integer.MAX_VALUE) {
                    ans = 1L + Integer.MAX_VALUE;
                    break;
                }
            }
            return neg ? (int) -ans : (int) ans;
        }
    }

    /*
    自动机
     */
    static class Solution2 {
        static class Automaton {
            final String START = "start";
            final String SIGNED = "signed";
            final String IN_NUM = "in_number";
            final String END = "end";
            String state = START;
            Map<String, String[]> map;
            public int sign = 1;
            public long ans = 0;

            public Automaton() {
                map = new HashMap<>();
                map.put(START, new String[]{START, SIGNED, IN_NUM, END});
                map.put(SIGNED, new String[]{END, END, IN_NUM, END});
                map.put(IN_NUM, new String[]{END, END, IN_NUM, END});
                map.put(END, new String[]{END, END, END, END});
            }

            public int get_col(char c) {
                if (c == ' ') return 0;
                if (c == '+' || c == '-') return 1;
                if (c >= '0' && c <= '9') return 2;
                return 3;
            }

            public void get(char c) {
                state = map.get(state)[get_col(c)];
                if (state.equals(IN_NUM)) {
                    ans = ans * 10 + c - '0';
                    if (sign == 1) {
                        ans = Math.min(ans, Integer.MAX_VALUE);
                    } else {
                        // -(long)Integer.MIN_VALUE，这个操作有点东西，不然越界
                        ans = Math.min(ans, -(long) Integer.MIN_VALUE);
                    }
                } else if (state.equals(SIGNED))
                    sign = c == '+' ? 1 : -1;
            }
        }

        public int myAtoi(String str) {
            Automaton automaton = new Automaton();
            char[] c = str.toCharArray();
            for (char ch : c) {
                automaton.get(ch);
            }
            return automaton.sign * ((int) automaton.ans);
        }
    }

    /*
    直接写
     */
    static class Solution3 {
        public int myAtoi(String s) {
            int i = 0;
            int len = s.length();
            int sign = 1;
            int res = 0;
            while (i < len && s.charAt(i) == ' ') { //如果字符串前导位置为空格，循环到有数据的那一个位置
                i++;
            }
            int start = i;  //记录一下当前之后所有数据开始的位置
            for (; i < len; i++) {
                int c = s.charAt(i);
                if (i == start && c == '+') {   //判断是否是+，并且+要在初始位置
                    sign = 1;
                } else if (i == start && c == '-') {    //判断是-
                    sign = -1;
                } else if (Character.isDigit(c)) {  //判断是数字
                    int num = c - '0';
                    //如果是数字，其他不用考虑，只需要考虑两种超限的情况，这里不细说，具体去"https://leetcode-cn.com/problems/reverse-integer/"看
                    if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                        return Integer.MAX_VALUE;
                    } else if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                        return Integer.MIN_VALUE;
                    }
                    res = res * 10 + sign * num;
                } else {    //如果有一次循环既不是数字，又不是'+'和'-'，那么立即退出循环，并返回当前res中已经储存的值
                    break;
                }
            }
            return res;
        }
    }

    /*
    官网提供的自动机
    这是确定有限状态机（deterministic finite automaton, DFA）
     */
    static class Solution4 {
        public int myAtoi(String str) {
            Automaton automaton = new Automaton();
            int length = str.length();
            for (int i = 0; i < length; ++i) {
                automaton.get(str.charAt(i));
            }
            return (int) (automaton.sign * automaton.ans);
        }
    }

    static class Automaton {
        public int sign = 1;
        public long ans = 0;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }
}
