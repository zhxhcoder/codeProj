package com.zhxh.codeproj.leetcode;

public class LeetCode371 {

    public static void main(String[] args) {

        int a=32;
        int b=4;

        Solution solution=new Solution();

        System.out.println(solution.getSum(a,b));
    }

   static class Solution {
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
