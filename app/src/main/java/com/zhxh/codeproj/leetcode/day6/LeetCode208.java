package com.zhxh.codeproj.leetcode.day6;

/*
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。


示例：

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True


提示：

1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成
insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次

 */
public class LeetCode208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // 返回 True
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app");     // 返回 True

        Trie2 trie2 = new Trie2();
        trie2.insert("apple");
        trie2.search("apple");   // 返回 True
        trie2.search("app");     // 返回 False
        trie2.startsWith("app"); // 返回 True
        trie2.insert("app");
        trie2.search("app");     // 返回 True
    }

    static class Trie {
        private boolean is_string = false;
        private Trie next[] = new Trie[26];

        public Trie() {
        }

        public void insert(String word) {//插入单词
            Trie root = this;
            char w[] = word.toCharArray();
            for (int i = 0; i < w.length; ++i) {
                if (root.next[w[i] - 'a'] == null) root.next[w[i] - 'a'] = new Trie();
                root = root.next[w[i] - 'a'];
            }
            root.is_string = true;
        }

        public boolean search(String word) {//查找单词
            Trie root = this;
            char w[] = word.toCharArray();
            for (int i = 0; i < w.length; ++i) {
                if (root.next[w[i] - 'a'] == null) return false;
                root = root.next[w[i] - 'a'];
            }
            return root.is_string;
        }

        public boolean startsWith(String prefix) {//查找前缀
            Trie root = this;
            char p[] = prefix.toCharArray();
            for (int i = 0; i < p.length; ++i) {
                if (root.next[p[i] - 'a'] == null) return false;
                root = root.next[p[i] - 'a'];
            }
            return true;
        }
    }

    static class Trie2 {
        private Trie2[] children;
        private boolean isEnd;

        public Trie2() {
            children = new Trie2[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie2 node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie2();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie2 node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie2 searchPrefix(String prefix) {
            Trie2 node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
