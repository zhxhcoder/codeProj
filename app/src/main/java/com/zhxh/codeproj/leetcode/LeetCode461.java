package com.zhxh.codeproj.leetcode;

public class LeetCode461 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.print(solution.hammingDistance(1, 4));
    }

    static class Solution {
        public int hammingDistance(int x, int y) {
            int i = x ^ y;
            int count = 0;
            while (i != 0) {
                if ((i & 1) == 1) {
                    count++;
                }
                i = i >> 1;
            }
            return count;
        }
    }
}
