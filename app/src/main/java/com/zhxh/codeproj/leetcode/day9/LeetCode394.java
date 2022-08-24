package com.zhxh.codeproj.leetcode.day9;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/*
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。



示例 1：

输入：s = "3[a]2[bc]"
输出："aaabcbc"
示例 2：

输入：s = "3[a2[c]]"
输出："accaccacc"
示例 3：

输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
示例 4：

输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"


提示：

1 <= s.length <= 30
s由小写英文字母、数字和方括号'[]' 组成
s保证是一个有效的输入。
s中所有整数的取值范围为[1, 300]

 */
public class LeetCode394 {
    public static void main(String[] args) {
        System.out.println(new Solution0().decodeString("3[a2[c]]"));
        System.out.println(new Solution1().decodeString("3[a2[c]]"));
        System.out.println(new Solution2().decodeString("3[a2[c]]"));
        System.out.println(new Solution3().decodeString("3[a2[c]]"));
        System.out.println(new Solution4().decodeString("3[a2[c]]"));
    }


    /*
      辅助栈
      数字存放在数字栈，字符串放在字符串栈，遇到右括号时候弹出一个数字栈，字母栈弹到左括号为止。
      就是逆波兰那种题
     */
    static class Solution0 {
        public String decodeString(String s) {
            StringBuilder ans = new StringBuilder();
            int multi = 0;
            Stack<StringBuilder> ansStack = new Stack<>();
            Stack<Integer> multiStack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    //ASCII码 字符可以直接相减 累计乘数
                    multi = multi * 10 + c - '0';
                } else if (c == '[') {
                    //两个数据入栈
                    ansStack.add(ans);
                    multiStack.add(multi);
                    //入栈之后重新初始化
                    ans = new StringBuilder();
                    multi = 0;
                } else if (c == ']') {
                    //两个数据出栈
                    StringBuilder ansTmp = ansStack.pop();
                    int tmpMulti = multiStack.pop();
                    //遍历乘数
                    for (int i = 0; i < tmpMulti; i++) {
                        ansTmp.append(ans);
                    }
                    //赋值给ans
                    ans = ansTmp;
                } else {
                    //累计字符串
                    ans.append(c);
                }
            }
            return ans.toString();
        }
    }

    /*
    栈操作
    - 如果说数字：将数字字符转化为整数，用于后续倍数计算
    - 如果说字符：延长当前字符串
    - 如果说左括号：当前状态入栈
    - 如果说有括号：弹出状态，组合字符串
     */
    static class Solution1 {
        int ptr;

        public String decodeString(String s) {
            LinkedList<String> stk = new LinkedList<String>();
            ptr = 0;

            while (ptr < s.length()) {
                char cur = s.charAt(ptr);
                if (Character.isDigit(cur)) {
                    // 获取一个数字并进栈
                    String digits = getDigits(s);
                    stk.addLast(digits);
                } else if (Character.isLetter(cur) || cur == '[') {
                    // 获取一个字母并进栈
                    stk.addLast(String.valueOf(s.charAt(ptr++)));
                } else {
                    ++ptr;
                    LinkedList<String> sub = new LinkedList<String>();
                    while (!"[".equals(stk.peekLast())) {
                        sub.addLast(stk.removeLast());
                    }
                    Collections.reverse(sub);
                    // 左括号出栈
                    stk.removeLast();
                    // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                    int repTime = Integer.parseInt(stk.removeLast());
                    StringBuilder t = new StringBuilder();
                    String o = getString(sub);
                    // 构造字符串
                    while (repTime-- > 0) {
                        t.append(o);
                    }
                    // 将构造好的字符串入栈
                    stk.addLast(t.toString());
                }
            }

            return getString(stk);
        }

        public String getDigits(String s) {
            StringBuilder ret = new StringBuilder();
            while (Character.isDigit(s.charAt(ptr))) {
                ret.append(s.charAt(ptr++));
            }
            return ret.toString();
        }

        public String getString(LinkedList<String> v) {
            StringBuilder ret = new StringBuilder();
            for (String s : v) {
                ret.append(s);
            }
            return ret.toString();
        }
    }

    /*
    递归
    - 每个左括号对应一个子问题
    - 模式识别：一旦碰到结构和原问题一致的子问题，可以递归调用函数解决
     */
    static class Solution2 {
        String src;
        int ptr;

        public String decodeString(String s) {
            src = s;
            ptr = 0;
            return getString();
        }

        public String getString() {
            if (ptr == src.length() || src.charAt(ptr) == ']') {
                // String -> EPS
                return "";
            }
            char cur = src.charAt(ptr);
            int repTime = 1;
            String ret = "";
            if (Character.isDigit(cur)) {
                // String -> Digits [ String ] String
                // 解析 Digits
                repTime = getDigits();
                // 过滤左括号
                ++ptr;
                // 解析 String
                String str = getString();
                // 过滤右括号
                ++ptr;
                // 构造字符串
                while (repTime-- > 0) {
                    ret += str;
                }
            } else if (Character.isLetter(cur)) {
                // String -> Char String
                // 解析 Char
                ret = String.valueOf(src.charAt(ptr++));
            }
            return ret + getString();
        }

        public int getDigits() {
            int ret = 0;
            while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
                ret = ret * 10 + src.charAt(ptr++) - '0';
            }
            return ret;
        }
    }

    /*
    递归
    https://leetcode.cn/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
    总体思路与辅助栈法一致，不同点在于[ 和 ] 分别作为递归的开启和终止条件
     */
    static class Solution3 {
        public String decodeString(String s) {
            return dfs(s, 0)[0];
        }

        private String[] dfs(String s, int i) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            while (i < s.length()) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                    multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                else if (s.charAt(i) == '[') {
                    String[] tmp = dfs(s, i + 1);
                    i = Integer.parseInt(tmp[0]);
                    while (multi > 0) {
                        res.append(tmp[1]);
                        multi--;
                    }
                } else if (s.charAt(i) == ']')
                    return new String[]{String.valueOf(i), res.toString()};
                else
                    res.append(s.charAt(i));
                i++;
            }
            return new String[]{res.toString()};
        }
    }

    /*
    利用栈
     */
    static class Solution4 {
        public String decodeString(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c != ']') {
                    stack.push(c); // 把所有的字母push进去，除了]
                } else {
                    //step 1: 取出[] 内的字符串
                    StringBuilder sb = new StringBuilder();
                    while (!stack.isEmpty() && Character.isLetter(stack.peek()))
                        sb.insert(0, stack.pop());

                    String sub = sb.toString(); //[ ]内的字符串
                    stack.pop(); // 去除[

                    //step 2: 获取倍数数字
                    sb = new StringBuilder();
                    while (!stack.isEmpty() && Character.isDigit(stack.peek()))
                        sb.insert(0, stack.pop());

                    int count = Integer.valueOf(sb.toString()); //倍数

                    //step 3: 根据倍数把字母再push回去
                    while (count > 0) {
                        for (char ch : sub.toCharArray())
                            stack.push(ch);
                        count--;
                    }
                }
            }
            //把栈里面所有的字母取出来，完事L('ω')┘三└('ω')｣
            StringBuilder retv = new StringBuilder();
            while (!stack.isEmpty())
                retv.insert(0, stack.pop());

            return retv.toString();
        }
    }
}
