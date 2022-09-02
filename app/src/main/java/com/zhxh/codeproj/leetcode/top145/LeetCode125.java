package com.zhxh.codeproj.leetcode.top145;

/*
如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个回文串。

字母和数字都属于字母数字字符。

给你一个字符串 s，如果它是回文串，返回 true ；否则，返回 false 。



示例 1：

输入: "A man, a plan, a canal: Panama"
输出：true
解释："amanaplanacanalpanama" 是回文串。
示例 2：

输入："race a car"
输出：false
解释："raceacar" 不是回文串。
示例 3：

输入：s = " "
输出：true
解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
由于空字符串正着反着读都一样，所以是回文串。

 */
public class LeetCode125 {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new Solution2().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new Solution3().isPalindrome("A man, a plan, a canal: Panama"));
    }

    /*
    方法一：筛选 + 判断
     */
    static class Solution {
        public boolean isPalindrome(String s) {
            StringBuffer sgood = new StringBuffer();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sgood.append(Character.toLowerCase(ch));
                }
            }
            StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
            return sgood.toString().equals(sgood_rev.toString());
        }
    }

    /*
      方法一：筛选 + 判断
       双指针方法
       */
    static class Solution2 {
        public boolean isPalindrome(String s) {
            StringBuffer sgood = new StringBuffer();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sgood.append(Character.toLowerCase(ch));
                }
            }
            int n = sgood.length();
            int left = 0, right = n - 1;
            while (left < right) {
                if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
            return true;
        }
    }

    /*
    方法二：在原字符串上直接判断
     */
    static class Solution3 {
        public boolean isPalindrome(String s) {
            int n = s.length();
            int left = 0, right = n - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    ++left;
                }
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    --right;
                }
                if (left < right) {
                    if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                        return false;
                    }
                    ++left;
                    --right;
                }
            }
            return true;
        }
    }

}
