package com.zhxh.codeproj.leetcode.ace100.slidewindow;

/*
395. 至少有 K 个重复字符的最长子串
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。



示例 1：

输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2：

输入：s = "ababbc", k = 2
输出：5
解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。


提示：

1 <= s.length <= 104
s 仅由小写英文字母组成
1 <= k <= 105
 */
public class LeetCode395 {
    public static void main(String[] args) {
        //todo 滑动窗口
        System.out.println(new Solution().longestSubstring("ababbc", 2));
        System.out.println(new Solution2().longestSubstring("ababbc", 2));
        System.out.println(new Solution3().longestSubstring("ababbc", 2));
    }

    /*
    方法一：分治
     */
    static class Solution {
        public int longestSubstring(String s, int k) {
            int n = s.length();
            return dfs(s, 0, n - 1, k);
        }

        public int dfs(String s, int l, int r, int k) {
            int[] cnt = new int[26];
            for (int i = l; i <= r; i++) {
                cnt[s.charAt(i) - 'a']++;
            }

            char split = 0;
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0 && cnt[i] < k) {
                    split = (char) (i + 'a');
                    break;
                }
            }
            if (split == 0) {
                return r - l + 1;
            }

            int i = l;
            int ret = 0;
            while (i <= r) {
                while (i <= r && s.charAt(i) == split) {
                    i++;
                }
                if (i > r) {
                    break;
                }
                int start = i;
                while (i <= r && s.charAt(i) != split) {
                    i++;
                }

                int length = dfs(s, start, i - 1, k);
                ret = Math.max(ret, length);
            }
            return ret;
        }
    }

    /*
    滑动窗口
     */
    static class Solution2 {
        public int longestSubstring(String s, int k) {
            int ret = 0;
            int n = s.length();
            for (int t = 1; t <= 26; t++) {
                int l = 0, r = 0;
                int[] cnt = new int[26];
                int tot = 0;
                int less = 0;
                while (r < n) {
                    cnt[s.charAt(r) - 'a']++;
                    if (cnt[s.charAt(r) - 'a'] == 1) {
                        tot++;
                        less++;
                    }
                    if (cnt[s.charAt(r) - 'a'] == k) {
                        less--;
                    }

                    while (tot > t) {
                        cnt[s.charAt(l) - 'a']--;
                        if (cnt[s.charAt(l) - 'a'] == k - 1) {
                            less++;
                        }
                        if (cnt[s.charAt(l) - 'a'] == 0) {
                            tot--;
                            less--;
                        }
                        l++;
                    }
                    if (less == 0) {
                        ret = Math.max(ret, r - l + 1);
                    }
                    r++;
                }
            }
            return ret;
        }
    }

    static class Solution3 {
        public int longestSubstring(String s, int k) {
            return dfs(s, k);
        }

        private int dfs(String s, int k) {
            //统计每个字符出现的次数
            int[] charCount = new int[26];
            for (int i = 0; i < s.length(); i++) {
                charCount[s.charAt(i) - 'a']++;
            }

            //统计<k的字符
            String split = "";
            for (int i = 0; i < charCount.length; i++) {
                int count = charCount[i];
                if (count > 0 && count < k) {
                    split = String.valueOf((char) (i + 'a'));
                    break;
                }
            }

            if (split.equals("")) {
                //全部都>k
                return s.length();
            }

            final String[] split1 = s.split(split);
            int max = 0;
            for (final String s1 : split1) {
                final int dfs = dfs(s1, k);
                max = Math.max(max, dfs);
            }

            return max;
        }
    }
}
