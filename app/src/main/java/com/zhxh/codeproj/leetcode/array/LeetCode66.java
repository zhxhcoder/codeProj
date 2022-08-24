package com.zhxh.codeproj.leetcode.array;

import java.util.Arrays;

/*
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例1:

输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
示例2:

输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。

 */
public class LeetCode66 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{9, 9, 9})));
        System.out.println(Arrays.toString(new Solution2().plusOne(new int[]{9, 9, 9})));
        System.out.println(Arrays.toString(new Solution3().plusOne(new int[]{9, 9, 9})));
    }

    static class Solution {
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i]++;
                digits[i] = digits[i] % 10;
                if (digits[i] != 0) return digits;
            }
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
    }

    /*
    官网答案
     */
    static class Solution2 {
        public int[] plusOne(int[] digits) {
            int n = digits.length;
            for (int i = n - 1; i >= 0; --i) {
                if (digits[i] != 9) {
                    ++digits[i];
                    for (int j = i + 1; j < n; ++j) {
                        digits[j] = 0;
                    }
                    return digits;
                }
            }

            // digits 中所有的元素均为 9
            int[] ans = new int[n + 1];
            ans[0] = 1;
            return ans;
        }
    }

    /*
    不用纠结某一位是不是9，而应该去判断加1之后是不是0：
    */
    static class Solution3 {
        public int[] plusOne(int[] digits) {
            int len = digits.length;
            for (int i = len - 1; i >= 0; i--) {
                digits[i] = (digits[i] + 1) % 10;
                if (digits[i] != 0) {
                    return digits;
                }
            }
            digits = new int[len + 1];
            digits[0] = 1;
            return digits;
        }
    }
}
