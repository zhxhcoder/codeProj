package com.zhxh.codeproj.leetcode.top145;

/*

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
示例 1:

输入: "A"
输出: 1
示例 2:

输入: "AB"
输出: 28
示例 3:

输入: "ZY"
输出: 701

 */
public class LeetCode171 {
    public static void main(String[] args) {
        System.out.println(new Solution().titleToNumber("AB"));
        System.out.println(new Solution2().titleToNumber("AB"));
    }

    static class Solution {
        public int titleToNumber(String s) {
            int ans = 0;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - 'A' + 1;
                ans = ans * 26 + num;
            }
            return ans;
        }
    }

    /*
    方法一：进制转换
     */
    static class Solution2 {
        public int titleToNumber(String columnTitle) {
            int number = 0;
            int multiple = 1;
            for (int i = columnTitle.length() - 1; i >= 0; i--) {
                int k = columnTitle.charAt(i) - 'A' + 1;
                number += k * multiple;
                multiple *= 26;
            }
            return number;
        }
    }
}
