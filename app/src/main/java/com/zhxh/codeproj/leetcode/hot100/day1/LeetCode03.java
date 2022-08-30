package com.zhxh.codeproj.leetcode.hot100.day1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
## LeetCode003 无重复字符的最长子串
##### 题目详情
给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
**示例1:**
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
**示例 2:**
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
**示例 3:**
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
    请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
**提示：**
0 <= s.length <= 5 * 104
s由英文字母、数字、符号和空格组成

 */
public class LeetCode03 {
    public static void main(String[] args) {
        String s = "abcccabkabcd";
        System.out.println(new Solution().lengthOfLongestSubstring(s));
        System.out.println(new Solution().lengthOfLongestSubstring2(s));
    }

    /*
      我们使用两个指针表示字符串的某个子串的左右边界，其中左指针代表着上文中"枚举子串的起始位置"，而右指针
      在每一步的操作中，我们会将左指针向右移动一格，表示我们开始枚举下一个字符的起始位置，，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。
      在移动结束后，这个子串就对应着 以左指针开始，不包含重复字符的最长子串。我们记录下这个子串的长度。
      在枚举结束后，我们找到最长的子串的长度即为答案。
     */
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> set = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int right = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    set.remove(s.charAt(i - 1));
                }
                while (right + 1 < n && !set.contains(s.charAt(right + 1))) {
                    // 不断地移动右指针
                    set.add(s.charAt(right + 1));
                    ++right;
                }
                // 第 i 到 right 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, right - i + 1);
            }
            return ans;
        }

        public int lengthOfLongestSubstring2(String s) {
            int n = s.length(), ans = 0;
            //创建map窗口，i为左区间 j为右区间 ，右边界移动
            Map<Character, Integer> map = new HashMap<>();
            for (int j = 0, i = 0; j < n; j++) {
                //如果窗口中包含当前字符
                if (map.containsKey(s.charAt(j))) {
                    //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置
                    i = Math.max(map.get(s.charAt(j)) + 1, i);
                }
                //比对当前无重复字段长度和存储的长度，选最大值并替换
                //j-i+1是因为此时i，j索引扔处于不重复的位置，j还没有向后移动
                ans = Math.max(ans, j - i + 1);
                //这个字符位置
                map.put(s.charAt(j), j);
            }
            return ans;
        }

        /*
        根据set长度来计算
         */
        public int lengthOfLongestSubstring3(String s) {
            int n = s.length(), ans = 0;
            //创建map窗口，i为左区间 j为右区间 ，右边界移动
            Map<Character, Integer> map = new HashMap<>();
            for (int j = 0, i = 0; j < n; j++) {
                //如果窗口中包含当前字符
                if (map.containsKey(s.charAt(j))) {
                    //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置
                    i = Math.max(map.get(s.charAt(j)) + 1, i);
                }
                //比对当前无重复字段长度和存储的长度，选最大值并替换
                //j-i+1是因为此时i，j索引扔处于不重复的位置，j还没有向后移动
                ans = Math.max(ans, j - i + 1);
                //这个字符位置
                map.put(s.charAt(j), j);
            }
            return ans;
        }
    }
}
