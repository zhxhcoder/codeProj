package com.zhxh.codeproj.leetcode;

public class LeetCode344 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        char[] s = {'h', 'e', 'l', 'l', 'o'};

        for (char i : s) {
            System.out.print(i);
        }
        solution.reverseString(s);

        System.out.print("\n******\n");

        for (char i : s) {
            System.out.print(i);
        }
    }

    static class Solution {
        public void reverseString(char[] s) {
            int left = 0, right = s.length - 1;
            while (left < right) {
                char tmp = s[left];
                s[left++] = s[right];
                s[right--] = tmp;
            }
        }
    }
}
