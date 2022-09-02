package com.zhxh.codeproj.leetcode.top145;

import java.util.*;

/*
给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。

注意：词典中的同一个单词可能在分段中被重复使用多次。



示例 1：

输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
输出:["cats and dog","cat sand dog"]
示例 2：

输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
解释: 注意你可以重复使用字典中的单词。
示例 3：

输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
输出:[]


提示：

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s 和 wordDict[i] 仅有小写英文字母组成
wordDict 中所有字符串都 不同

 */
public class LeetCode140 {
    public static void main(String[] args) {
        //todo 回溯
        System.out.println(new Solution().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(new Solution2().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }

    /*
    方法一：记忆化搜索
     */
    static class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();
            List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);
            List<String> breakList = new LinkedList<String>();
            for (List<String> wordBreak : wordBreaks) {
                breakList.add(String.join(" ", wordBreak));
            }
            return breakList;
        }

        public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
            if (!map.containsKey(index)) {
                List<List<String>> wordBreaks = new LinkedList<List<String>>();
                if (index == length) {
                    wordBreaks.add(new LinkedList<String>());
                }
                for (int i = index + 1; i <= length; i++) {
                    String word = s.substring(index, i);
                    if (wordSet.contains(word)) {
                        List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                        for (List<String> nextWordBreak : nextWordBreaks) {
                            LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                            wordBreak.offerFirst(word);
                            wordBreaks.add(wordBreak);
                        }
                    }
                }
                map.put(index, wordBreaks);
            }
            return map.get(index);
        }
    }

    /*
    回溯
     */
    static class Solution2 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> set = new HashSet<>(wordDict);
            List<String> ans = new ArrayList<>();
            dfs(s, ans, 0, new LinkedList<>(), set);
            return ans;
        }

        public void dfs(String s, List<String> ans, int idx, Deque<String> path, Set<String> set) {
            if (idx == s.length()) {
                ans.add(String.join(" ", path));
                return;
            }
            for (int i = idx; i < s.length(); i++) {
                String str = s.substring(idx, i + 1);
                if (set.contains(str)) {
                    path.add(str);
                    dfs(s, ans, i + 1, path, set);
                    path.removeLast();
                }
            }
        }
    }
}
