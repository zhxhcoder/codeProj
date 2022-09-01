package com.zhxh.codeproj.leetcode.top145;

import java.util.HashMap;
import java.util.Map;

/*
给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。

如果小数部分为循环小数，则将循环的部分括在括号内。

如果存在多个答案，只需返回 任意一个 。

对于所有给定的输入，保证 答案字符串的长度小于 104 。

 

示例 1：

输入：numerator = 1, denominator = 2
输出："0.5"
示例 2：

输入：numerator = 2, denominator = 1
输出："2"
示例 3：

输入：numerator = 4, denominator = 333
输出："0.(012)"
 

提示：

-231 <= numerator, denominator <= 231 - 1
denominator != 0

 */
public class LeetCode166 {
    public static void main(String[] args) {
        System.out.println(new Solution().fractionToDecimal(4, -333));
    }

    /*
    方法一：长除法
     */
    static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            long numeratorLong = numerator;
            long denominatorLong = denominator;
            if (numeratorLong % denominatorLong == 0) {
                return String.valueOf(numeratorLong / denominatorLong);
            }

            StringBuilder sb = new StringBuilder();
            // false^false-->false
            // true^true-->false
            // true^false-->true
            if (numeratorLong < 0 ^ denominatorLong < 0) {
                sb.append('-');
            }
            // 整数部分
            numeratorLong = Math.abs(numeratorLong);
            denominatorLong = Math.abs(denominatorLong);
            long integerPart = numeratorLong / denominatorLong;
            sb.append(integerPart);
            sb.append('.');

            // 小数部分
            StringBuilder fractionPart = new StringBuilder();
            Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
            long remainder = numeratorLong % denominatorLong;
            int index = 0;
            while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
                remainderIndexMap.put(remainder, index);
                remainder *= 10;
                fractionPart.append(remainder / denominatorLong);
                remainder %= denominatorLong;
                index++;
            }
            if (remainder != 0) { // 有循环节
                int insertIndex = remainderIndexMap.get(remainder);
                fractionPart.insert(insertIndex, '(');
                fractionPart.append(')');
            }
            sb.append(fractionPart.toString());
            return sb.toString();
        }
    }
}
