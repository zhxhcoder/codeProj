package com.zhxh.codeproj.leetcode.day2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。


示例 1：

输入：s = "()"
输出：true
示例2：

输入：s = "()[]{}"
输出：true
示例3：

输入：s = "(]"
输出：false
示例4：

输入：s = "([)]"
输出：false
示例5：

输入：s = "{[]}"
输出：true


提示：

1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
 */
public class LeetCode20 {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("([)]"));
        System.out.println(new Solution2().isValid("([)]"));
    }

    static class Solution {
        private Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
            put('?', '?');
        }};

        public boolean isValid(String s) {
            if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
            LinkedList<Character> stack = new LinkedList<Character>() {{
                add('?');
            }};
            for (Character c : s.toCharArray()) {
                if (map.containsKey(c)) stack.addLast(c);
                else if (map.get(stack.removeLast()) != c) return false;
            }
            return stack.size() == 1;
        }

    }

    static class Solution2 {
        private HashMap<Character, Character> mappings;

        public Solution2() {
            this.mappings = new HashMap<Character, Character>();
            this.mappings.put(')', '(');
            this.mappings.put('}', '{');
            this.mappings.put(']', '[');
        }

        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (this.mappings.containsKey(c)) {
                    char topElement = stack.empty() ? '#' : stack.pop();
                    if (topElement != this.mappings.get(c)) {
                        return false;
                    }
                } else {
                    stack.push(c);
                }
            }
            return stack.isEmpty();
        }
    }
}
