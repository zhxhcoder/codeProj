package com.zhxh.codeproj.leetcode.hot100;

import java.util.*;

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
        System.out.println(new Solution3().isValid("([)]"));
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

    /*
    官网方法一：栈
    判断括号的有效性可以使用「栈」这一数据结构来解决。

我们遍历给定的字符串 s。当我们遇到一个左括号时，我们会期望在后续的遍历中，有一个相同类型的右括号将其闭合。由于后遇到的左括号要先闭合，因此我们可以将这个左括号放入栈顶。

当我们遇到一个右括号时，我们需要将一个相同类型的左括号闭合。此时，我们可以取出栈顶的左括号并判断它们是否是相同类型的括号。如果不是相同的类型，或者栈中并没有左括号，那么字符串 s 无效，返回False。为了快速判断括号的类型，我们可以使用哈希表存储每一种括号。哈希表的键为右括号，值为相同类型的左括号。

在遍历结束后，如果栈中没有左括号，说明我们将字符串 ss 中的所有左括号闭合，返回True，否则返回False。

注意到有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，我们可以直接返回False，省去后续的遍历判断过程。

     */
    static class Solution3 {
        public boolean isValid(String s) {
            int n = s.length();
            if (n % 2 == 1) {
                return false;
            }

            Map<Character, Character> pairs = new HashMap<Character, Character>() {{
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }};
            Deque<Character> stack = new LinkedList<Character>();
            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if (pairs.containsKey(ch)) {
                    if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                        return false;
                    }
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
            return stack.isEmpty();
        }
    }

}
