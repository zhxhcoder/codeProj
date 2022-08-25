package com.zhxh.codeproj.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
30. 串联所有单词的子串
给定一个字符串s和一些长度相同的单词words。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。

注意子串要与words 中的单词完全匹配，中间不能有其他字符，但不需要考虑words中单词串联的顺序。

示例 1：

输入：
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
示例 2：

输入：
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
输出：[]

 */
class LeetCode30 {
    public static void main(String[] args) {
        System.out.println(new Solution().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(new Solution2().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    /*
    思路一：

因为单词长度固定的，我们可以计算出截取字符串的单词个数是否和 words 里相等，所以我们可以借用哈希表。

一个是哈希表是 words，一个哈希表是截取的字符串，比较两个哈希是否相等！

因为遍历和比较都是线性的，所以时间复杂度：O(n^2)O(n
2
 )

上面思路每次都要反复遍历 s；下面介绍滑动窗口。

思路二：

滑动窗口！

我们一直在 s 维护着所有单词长度总和的一个长度队列！

时间复杂度：O(n)O(n)

还可以再优化，只是加一些判断，详细看代码吧！

     */
    static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
            HashMap<String, Integer> map = new HashMap<>();
            int one_word = words[0].length();
            int word_num = words.length;
            int all_len = one_word * word_num;
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (int i = 0; i < s.length() - all_len + 1; i++) {
                String tmp = s.substring(i, i + all_len);
                HashMap<String, Integer> tmp_map = new HashMap<>();
                for (int j = 0; j < all_len; j += one_word) {
                    String w = tmp.substring(j, j + one_word);
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                }
                if (map.equals(tmp_map)) res.add(i);
            }
            return res;
        }
    }

    static class Solution2 {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
            HashMap<String, Integer> map = new HashMap<>();
            int one_word = words[0].length();
            int word_num = words.length;
            int all_len = one_word * word_num;
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (int i = 0; i < one_word; i++) {
                int left = i, right = i, count = 0;
                HashMap<String, Integer> tmp_map = new HashMap<>();
                while (right + one_word <= s.length()) {
                    String w = s.substring(right, right + one_word);
                    right += one_word;
                    if (!map.containsKey(w)) {
                        count = 0;
                        left = right;
                        tmp_map.clear();
                    } else {
                        tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                        count++;
                        while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                            String t_w = s.substring(left, left + one_word);
                            count--;
                            tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                            left += one_word;
                        }
                        if (count == word_num) res.add(left);
                    }
                }
            }
            return res;
        }
    }
}
