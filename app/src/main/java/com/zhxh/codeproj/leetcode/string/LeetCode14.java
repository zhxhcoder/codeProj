package com.zhxh.codeproj.leetcode.string;

/*
编写一个函数来查找字符串数组中的最长公共前缀。

公共前缀 前缀

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。

 */

public class LeetCode14 {
    public static void main(String[] args) {
        String[] strs = {"flowsad", "flosad", "flisad"};
        System.out.print(new Solution().longestCommonPrefix(strs));
    }

    /*
    方法一是横向扫描，依次遍历每个字符串，更新最长公共前缀。另一种方法是纵向扫描。
    纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     */
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            int i = 0;
            StringBuilder res = new StringBuilder();
            while (i < strs[0].length()) {
                String pre = strs[0];
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].length() < i + 1 || pre.charAt(i) != strs[j].charAt(i)) {
                        return res.toString();
                    }
                    pre = strs[j];
                }
                res.append(pre.charAt(i));
                i++;
            }
            return res.toString();
        }
    }
}
