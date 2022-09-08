package com.zhxh.codeproj.leetcode.ace100.slidewindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。



示例 1:

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
s 和 p 仅包含小写字母
 */
public class LeetCode438 {
    public static void main(String[] args) {
        System.out.println(new Solution().findAnagrams("cbaebabacd", "abc"));
        System.out.println(new Solution2().findAnagrams("cbaebabacd", "abc"));
        System.out.println(new Solution3().findAnagrams("cbaebabacd", "abc"));
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

    /*
    方法一：滑动窗口
     */
    static class Solution2 {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            if (sLen < pLen) {
                return new ArrayList<Integer>();
            }

            List<Integer> ans = new ArrayList<Integer>();
            int[] sCount = new int[26];
            int[] pCount = new int[26];
            for (int i = 0; i < pLen; ++i) {
                ++sCount[s.charAt(i) - 'a'];
                ++pCount[p.charAt(i) - 'a'];
            }

            if (Arrays.equals(sCount, pCount)) {
                ans.add(0);
            }

            for (int i = 0; i < sLen - pLen; ++i) {
                --sCount[s.charAt(i) - 'a'];
                ++sCount[s.charAt(i + pLen) - 'a'];

                if (Arrays.equals(sCount, pCount)) {
                    ans.add(i + 1);
                }
            }

            return ans;
        }
    }

    /*
    方法二：优化的滑动窗口
     */
    static class Solution3 {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            if (sLen < pLen) {
                return new ArrayList<Integer>();
            }

            List<Integer> ans = new ArrayList<Integer>();
            int[] count = new int[26];
            for (int i = 0; i < pLen; ++i) {
                ++count[s.charAt(i) - 'a'];
                --count[p.charAt(i) - 'a'];
            }

            int differ = 0;
            for (int j = 0; j < 26; ++j) {
                if (count[j] != 0) {
                    ++differ;
                }
            }

            if (differ == 0) {
                ans.add(0);
            }

            for (int i = 0; i < sLen - pLen; ++i) {
                if (count[s.charAt(i) - 'a'] == 1) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从不同变得相同
                    --differ;
                } else if (count[s.charAt(i) - 'a'] == 0) {  // 窗口中字母 s[i] 的数量与字符串 p 中的数量从相同变得不同
                    ++differ;
                }
                --count[s.charAt(i) - 'a'];

                if (count[s.charAt(i + pLen) - 'a'] == -1) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从不同变得相同
                    --differ;
                } else if (count[s.charAt(i + pLen) - 'a'] == 0) {  // 窗口中字母 s[i+pLen] 的数量与字符串 p 中的数量从相同变得不同
                    ++differ;
                }
                ++count[s.charAt(i + pLen) - 'a'];

                if (differ == 0) {
                    ans.add(i + 1);
                }
            }
            return ans;
        }
    }
}
