package com.zhxh.codeproj.algorithm;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by zhxh on 2019/4/14
 */
public class Palindrome {


    /*
    双指针i、j,一个从前往后遍历，一个从后往前遍历，用a、b记录i、j所指字符的ASCII值，当所指字符为大写字母时，将其转为小写字母的ASCII值（加32），
判断a、b是否是在小写字母或数字的ASCII值范围内，是则判断a是否等于b，不等于返回false,等于时i指针加一，j指针减一,
接着判断a是否超出范围，是则i指针加一；判断b是否超出范围，是则j指针减一,
直到i不小于j跳出循环，返回true。
     */
    @Test
    public void test1() {
        Assert.assertTrue(isPaindrome("a b dd b a"));
    }

    public boolean isPaindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            int a = (int) s.charAt(i);
            int b = (int) s.charAt(j);
            if (65 <= (int) s.charAt(i) && s.charAt(i) <= 90) {
                a += 32;
            }
            if (65 <= (int) s.charAt(j) && (int) s.charAt(j) <= 90) {
                b += 32;

            }

            if (((48 <= a && a <= 57) || (97 <= a && a <= 122)) && ((48 <= b && b <= 57) || (97 <= b && b <= 122))) {
                if (a != b) {
                    return false;
                } else {
                    i++;
                    j--;
                }
            }
            if (48 > a || (57 < a && a < 97) || a > 122) {
                i++;
            }
            if (48 > b || (57 < b && b < 97) || b > 122) {
                j--;
            }

        }

        return true;
    }
}
