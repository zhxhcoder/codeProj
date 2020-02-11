package com.zhxh.codeproj.leetcode;

public class LeetCode371 {
    class Solution {
        public int getSum(int a, int b) {
            while (b != 0) {
                int temp = a ^ b;
                b = (a & b) << 1;
                a = temp;
            }
            return a;
        }
    }
}
