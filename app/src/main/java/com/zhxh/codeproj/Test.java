package com.zhxh.codeproj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
不用系统函数，实现contains函数
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(solve("abc", new String[]{"abc", "abc", "abc", "bc", "c", "abcd", "dabc"}));
    }


    private static List<String> solve(String a, String[] list) {
        List<String> res = new LinkedList<String>();
        for (String str : list) {
            if (contains(str, a)) {
                res.add(str);
            }
        }
        return res;
    }

    private static boolean contains(String str, String a) {
        if (a == null || a.isEmpty() || str == null || str.isEmpty()) {
            return false;
        }

        if (str.length() < a.length()) {
            return false;
        }

        char[] strArr = str.toCharArray();
        char[] aArr = str.toCharArray();


        for (int i = 0; i < strArr.length; i++) {
            //去掉首个元素
            char[] newstrArr = new char[strArr.length - i];

            int index = 0;
            for (int j = i; j < strArr.length; j++) {
                newstrArr[index] = strArr[j];
                index++;
            }

            System.out.println(newstrArr);

            if (preContains(newstrArr, aArr)) {
                return true;
            }
        }
        return false;
    }


    private static boolean preContains(char[] strArr, char[] aArr) {
        if (aArr == null || strArr == null) {
            return false;
        }

        if (strArr.length < aArr.length) {
            return false;
        }

        for (int i = 0; i < aArr.length; i++) {
            if (aArr[i] != strArr[i]) {
                return false;
            }
        }
        return true;
    }
}
