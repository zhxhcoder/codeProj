package com.zhxh.codeproj.leetcode.hot100.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

示例1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。


提示:

1 <= s.length, p.length <= 3 * 104
s和p仅包含小写字母

 */
public class LeetCode438 {
    public static void main(String[] args) {
        System.out.println(new Solution().findAnagrams("cbaebabacd", "abc"));
    }

    /*
    滑动窗口
     */
    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            // 用于返回字母异位词的起始索引
            List<Integer> res = new ArrayList<>();
            // 用 map 存储目标值中各个单词出现的次数
            HashMap<Character, Integer> map = new HashMap<>();
            for (Character c : p.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
            // 用另外一个 map 存储滑动窗口中有效字符出现的次数
            HashMap<Character, Integer> window = new HashMap<>();
            int left = 0; // 左指针
            int right = 0; // 右指针
            int valid = p.length(); // 只有当 valid == 0 时，才说明 window 中包含了目标子串
            while (right < s.length()) {
                // 如果目标子串中包含了该字符，才存入 window 中
                if (map.containsKey(s.charAt(right))) {
                    window.put(s.charAt(right), window.getOrDefault(s.charAt(right), 0) + 1);
                    // 只有当 window 中该有效字符数量不大于map中该字符数量，才能算一次有效包含
                    if (window.get(s.charAt(right)) <= map.get(s.charAt(right))) {
                        valid--;
                    }
                }
                // 如果 window 符合要求，即两个 map 存储的有效字符相同，就可以移动左指针了
                // 但是只有二个map存储的数据完全相同，才可以记录当前的起始索引，也就是left指针所在位置
                while (valid == 0) {
                    if (right - left + 1 == p.length()) res.add(left);
                    // 如果左指针指的是有效字符,需要更改 window 中的 key 对应的 value
                    // 如果 有效字符对应的数量比目标子串少，说明无法匹配了
                    if (map.containsKey(s.charAt(left))) {
                        window.put(s.charAt(left), window.get(s.charAt(left)) - 1);
                        if (window.get(s.charAt(left)) < map.get(s.charAt(left))) {
                            valid++;
                        }
                    }
                    left++;
                }
                right++;
            }
            return res;
        }
    }
}
